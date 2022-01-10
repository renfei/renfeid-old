package net.renfei.services.account;

import net.renfei.discuz.repositories.DiscuzUcenterMembersDOMapper;
import net.renfei.discuz.repositories.entity.DiscuzUcenterMembersDO;
import net.renfei.discuz.repositories.entity.DiscuzUcenterMembersDOExample;
import net.renfei.discuz.ucenter.client.Client;
import net.renfei.domain.user.User;
import net.renfei.exception.BusinessException;
import net.renfei.exception.NeedU2FException;
import net.renfei.model.SecretLevelEnum;
import net.renfei.model.auth.SignInVO;
import net.renfei.repositories.SysAccountMapper;
import net.renfei.repositories.model.SysAccount;
import net.renfei.repositories.model.SysAccountExample;
import net.renfei.repositories.model.SysVerificationCode;
import net.renfei.services.AccountService;
import net.renfei.services.BaseService;
import net.renfei.services.VerificationCodeService;
import net.renfei.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 账号服务
 *
 * @author renfei
 */
@Service
public class AccountServiceImpl extends BaseService implements AccountService {
    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
    private final SysAccountMapper accountMapper;
    private final VerificationCodeService verificationCodeService;
    private final DiscuzUcenterMembersDOMapper discuzUcenterMembersMapper;

    public AccountServiceImpl(SysAccountMapper accountMapper,
                              VerificationCodeService verificationCodeService,
                              DiscuzUcenterMembersDOMapper discuzUcenterMembersMapper) {
        this.accountMapper = accountMapper;
        this.verificationCodeService = verificationCodeService;
        this.discuzUcenterMembersMapper = discuzUcenterMembersMapper;
    }

    /**
     * 登陆
     *
     * @param signInVO 登陆请求对象
     * @param request  请求对象
     * @return
     */
    @Override
    public User signIn(SignInVO signInVO, HttpServletRequest request) throws BusinessException, NeedU2FException {
        SysAccountExample example = new SysAccountExample();
        SysAccountExample.Criteria criteria = example.createCriteria();
        if (ObjectUtils.isEmpty(signInVO.getUserName())) {
            throw new BusinessException("用户名或密码错误");
        }
        if (StringUtils.isChinaPhone(signInVO.getUserName())) {
            criteria.andPhoneEqualTo(signInVO.getUserName());
        } else if (StringUtils.isEmail(signInVO.getUserName())) {
            // 邮件登陆，那状态码必须大于等于1，1邮箱验证；2手机验证；3邮箱和手机都验证
            criteria.andEmailEqualTo(signInVO.getUserName());
        } else {
            criteria.andUserNameEqualTo(signInVO.getUserName());
        }
        SysAccount account = ListUtils.getOne(accountMapper.selectByExample(example));
        if (account == null) {
            throw new BusinessException("用户未注册，请先注册");
        }
        if (account.getStateCode() < 0) {
            throw new BusinessException("当前账户已被冻结，请联系我们解冻");
        }
        if (account.getStateCode() < 1) {
            // 发送激活邮件
            assert SYSTEM_CONFIG != null;
            verificationCodeService.sendVerificationCode(true, DateUtils.nextHours(2),
                    account.getEmail(), "SIGN_UP", account, SYSTEM_CONFIG.getSiteDomainName() + "/auth/signUp/activation");
            throw new BusinessException("当前账户邮箱未激活，我们已经为您发送了一封激活邮件");
        } else if (StringUtils.isChinaPhone(signInVO.getUserName()) && account.getStateCode() < 2) {
            // 邮件登陆，那状态码必须大于等于1，1邮箱验证；2手机验证；3邮箱和手机都验证
            throw new BusinessException("当前账户手机号码未通过验证，不能使用手机号码登录");
        }
        if (account.getLockTime() != null) {
            // 判断锁定时间
            if (new Date().before(account.getLockTime())) {
                String lockDate = DateUtils.getDate(account.getLockTime(), "yyyy-MM-dd hh:mm:ss");
                throw new BusinessException("当前账户已被锁定至[" + lockDate + "]，请稍后再试");
            }
        }
        if (!ObjectUtils.isEmpty(account.getTotp()) && ObjectUtils.isEmpty(signInVO.gettOtp())) {
            throw new NeedU2FException("当前账户开启了两步认证(U2F)，需要提供两步认证码");
        }
        User user = new User();
        if (signInVO.getUseVerCode()) {
            // 使用验证码验证
            SysVerificationCode verificationCode = verificationCodeService.verificationCode(signInVO.getPassword(), signInVO.getUserName(), "SIGN_IN");
            if (verificationCode == null) {
                throw new BusinessException("验证码错误或已过期");
            }
        } else {
            // 使用密码登陆
            if (!PasswordUtils.verifyPassword(signInVO.getPassword(), account.getPassword())) {
                // 记录错误，如果错误超过6次，锁定时间为 (N-6)*1分钟
                account.setTrialErrorTimes(account.getTrialErrorTimes() + 1);
                if (account.getTrialErrorTimes() > 6) {
                    // 锁定时间
                    account.setLockTime(DateUtils.nextMinutes(account.getTrialErrorTimes() - 6));
                }
                accountMapper.updateByPrimaryKeySelective(account);
                throw new BusinessException("用户名或密码错误");
            }
        }
        // 两步认证
        if (!ObjectUtils.isEmpty(account.getTotp())) {
            if (!GoogleAuthenticator.authcode(signInVO.gettOtp(), account.getTotp())) {
                throw new BusinessException("两步认证(U2F)失败，请重试");
            }
        }
        BeanUtils.copyProperties(account, user);
        user.setSecretLevelEnum(SecretLevelEnum.valueOf(account.getSecretLevel()));
        // 登陆论坛
        DiscuzUcenterMembersDOExample discuzUcenterMembersExample = new DiscuzUcenterMembersDOExample();
        discuzUcenterMembersExample.createCriteria().andUsernameEqualTo(account.getUserName());
        DiscuzUcenterMembersDO discuzUcenterMembers = ListUtils.getOne(discuzUcenterMembersMapper.selectByExample(discuzUcenterMembersExample));
        if (discuzUcenterMembers != null) {
            try {
                assert SYSTEM_CONFIG != null;
                Client client =
                        new Client(SYSTEM_CONFIG.getUCenter().getApi(),
                                null,
                                SYSTEM_CONFIG.getUCenter().getKey(),
                                SYSTEM_CONFIG.getUCenter().getAppId(),
                                SYSTEM_CONFIG.getUCenter().getConnect());
                String script = client.ucUserSynlogin(discuzUcenterMembers.getUid());
                logger.info("uc script:{}", script);
                if (!ObjectUtils.isEmpty(script)) {
                    String[] strings = script.split("src=\"");
                    String script2 = "";
                    if (strings.length == 3) {
                        script2 += strings[1].replace("\" reload=\"1\"></script><script type=\"text/javascript\" ", "");
                        script2 += "|";
                        script2 += strings[2].replace("\" reload=\"1\"></script>", "");
                    } else if (strings.length == 2) {
                        script2 += strings[1].replace("\" reload=\"1\"></script>", "");
                    } else {
                        logger.warn("strings.length != 3,script:{}", script);
                    }
                    // 将http转为https
                    script = script2.replace("http://", "https://");
                } else {
                    logger.warn("根据UserName：{}，论坛登录脚本为空。", account.getUserName());
                }
                user.setUcScript(script);
            } catch (Exception exception) {
                logger.error(exception.getMessage(), exception);
            }
        } else {
            logger.warn("根据UserName：{}，未找到论坛用户，所以没有论坛登录脚本。", account.getUserName());
        }
        return user;
    }

    /**
     * 登出，返回Discuz的登出代码
     *
     * @param user
     * @return
     */
    @Override
    public String signOut(User user) {
        if (user != null) {
            DiscuzUcenterMembersDOExample discuzUcenterMembersExample = new DiscuzUcenterMembersDOExample();
            discuzUcenterMembersExample.createCriteria().andUsernameEqualTo(user.getUserName());
            DiscuzUcenterMembersDO discuzUcenterMembers = ListUtils.getOne(discuzUcenterMembersMapper.selectByExample(discuzUcenterMembersExample));
            assert SYSTEM_CONFIG != null;
            if (discuzUcenterMembers != null) {
                try {
                    Client client =
                            new Client(SYSTEM_CONFIG.getUCenter().getApi(),
                                    null,
                                    SYSTEM_CONFIG.getUCenter().getKey(),
                                    SYSTEM_CONFIG.getUCenter().getAppId(),
                                    SYSTEM_CONFIG.getUCenter().getConnect());
                    return client.ucUserSynlogout();
                } catch (Exception ignored) {
                }
            }
        }
        return "";
    }
}
