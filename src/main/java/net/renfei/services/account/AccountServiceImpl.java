package net.renfei.services.account;

import com.aliyun.oss.ServiceException;
import net.renfei.discuz.repositories.*;
import net.renfei.discuz.repositories.entity.*;
import net.renfei.discuz.ucenter.client.Client;
import net.renfei.domain.user.User;
import net.renfei.exception.BusinessException;
import net.renfei.exception.NeedU2FException;
import net.renfei.model.SecretLevelEnum;
import net.renfei.model.auth.SignInVO;
import net.renfei.model.auth.SignUpActivationVO;
import net.renfei.model.auth.SignUpVO;
import net.renfei.repositories.SysAccountKeepNameMapper;
import net.renfei.repositories.SysAccountMapper;
import net.renfei.repositories.model.*;
import net.renfei.services.AccountService;
import net.renfei.services.BaseService;
import net.renfei.services.LeafService;
import net.renfei.services.VerificationCodeService;
import net.renfei.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * 账号服务
 *
 * @author renfei
 */
@Service
public class AccountServiceImpl extends BaseService implements AccountService {
    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
    private final LeafService leafService;
    private final SysAccountMapper accountMapper;
    private final VerificationCodeService verificationCodeService;
    private final SysAccountKeepNameMapper sysAccountKeepNameMapper;
    private final DiscuzCommonMemberDOMapper discuzCommonMemberMapper;
    private final DiscuzUcenterMembersDOMapper discuzUcenterMembersMapper;
    private final DiscuzCommonMemberCountDOMapper discuzCommonMemberCountMapper;
    private final DiscuzCommonMemberStatusDOMapper discuzCommonMemberStatusMapper;
    private final DiscuzCommonMemberProfileDOMapper discuzCommonMemberProfileMapper;
    private final DiscuzCommonMemberFieldHomeDOMapper discuzCommonMemberFieldHomeMapper;
    private final DiscuzCommonMemberFieldForumDOMapper discuzCommonMemberFieldForumMapper;
    private static final Pattern SPECIAL_PATTERN = Pattern.compile("[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t");

    public AccountServiceImpl(LeafService leafService,
                              SysAccountMapper accountMapper,
                              VerificationCodeService verificationCodeService,
                              SysAccountKeepNameMapper sysAccountKeepNameMapper,
                              DiscuzCommonMemberDOMapper discuzCommonMemberMapper,
                              DiscuzUcenterMembersDOMapper discuzUcenterMembersMapper,
                              DiscuzCommonMemberCountDOMapper discuzCommonMemberCountMapper,
                              DiscuzCommonMemberStatusDOMapper discuzCommonMemberStatusMapper,
                              DiscuzCommonMemberProfileDOMapper discuzCommonMemberProfileMapper,
                              DiscuzCommonMemberFieldHomeDOMapper discuzCommonMemberFieldHomeMapper,
                              DiscuzCommonMemberFieldForumDOMapper discuzCommonMemberFieldForumMapper) {
        this.leafService = leafService;
        this.accountMapper = accountMapper;
        this.verificationCodeService = verificationCodeService;
        this.sysAccountKeepNameMapper = sysAccountKeepNameMapper;
        this.discuzCommonMemberMapper = discuzCommonMemberMapper;
        this.discuzUcenterMembersMapper = discuzUcenterMembersMapper;
        this.discuzCommonMemberCountMapper = discuzCommonMemberCountMapper;
        this.discuzCommonMemberStatusMapper = discuzCommonMemberStatusMapper;
        this.discuzCommonMemberProfileMapper = discuzCommonMemberProfileMapper;
        this.discuzCommonMemberFieldHomeMapper = discuzCommonMemberFieldHomeMapper;
        this.discuzCommonMemberFieldForumMapper = discuzCommonMemberFieldForumMapper;
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
     * 注册
     *
     * @param signUpVO 注册请求对象
     * @param request  请求对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void signUp(SignUpVO signUpVO, HttpServletRequest request) throws PasswordUtils.CannotPerformOperationException {
        if (ObjectUtils.isEmpty(signUpVO.getUserName().trim())) {
            throw new ServiceException("用户名不能为空。");
        }
        if (signUpVO.getUserName().trim().getBytes().length < 4) {
            throw new ServiceException("用户名长度过短，请重起一个名字吧。");
        }
        if (ObjectUtils.isEmpty(signUpVO.getEmail().trim())) {
            throw new ServiceException("电子邮箱不能为空。");
        }
        if (StringUtils.isEmail(signUpVO.getUserName().trim())) {
            throw new ServiceException("您不能使用电子邮件地址作为您的用户名。");
        }
        if (StringUtils.isChinaPhone(signUpVO.getUserName().trim())) {
            throw new ServiceException("您不能使用手机号码作为您的用户名，注册成功后您可以绑定您的手机号码。");
        }
        if (StringUtils.isDomain(signUpVO.getUserName().trim())) {
            throw new ServiceException("用户名格式不正确，请换个用户名试试。");
        }
        if (SPECIAL_PATTERN.matcher(signUpVO.getUserName().trim()).find()) {
            throw new ServiceException("用户名包含非法字符，请重起一个名字吧。");
        }
        if (ObjectUtils.isEmpty(signUpVO.getPassword())) {
            throw new ServiceException("密码不能为空。");
        }
        if (!StringUtils.isEmail(signUpVO.getEmail().trim())) {
            throw new ServiceException("您填写的电子邮箱地址格式不正确。");
        }
        // 检查保留用户名
        SysAccountKeepNameExample keepNameExample = new SysAccountKeepNameExample();
        keepNameExample.createCriteria().andUserNameEqualTo(signUpVO.getUserName());
        List<SysAccountKeepName> keepNameDOS = sysAccountKeepNameMapper.selectByExample(keepNameExample);
        if (keepNameDOS != null && keepNameDOS.size() > 0) {
            throw new ServiceException("用户名已经被占用，请换个用户名试试。");
        }
        // 检查用户名重复
        SysAccountExample example = new SysAccountExample();
        example.createCriteria().andUserNameEqualTo(signUpVO.getUserName().trim().toLowerCase());
        SysAccount account = ListUtils.getOne(accountMapper.selectByExample(example));
        if (account != null) {
            throw new ServiceException("用户名已经被占用，请换个用户名试试。");
        }
        // 检查Email重复
        example = new SysAccountExample();
        example.createCriteria().andEmailEqualTo(signUpVO.getEmail().trim().toLowerCase());
        account = ListUtils.getOne(accountMapper.selectByExample(example));
        if (account != null) {
            throw new ServiceException("电子邮箱地址已经被注册，您不妨直接登陆试试。");
        }
        account = new SysAccount();
        account.setId(leafService.getId().getId());
        account.setUuid(UUID.randomUUID().toString().replace("-", "").toUpperCase());
        account.setUserName(signUpVO.getUserName().trim().toLowerCase());
        account.setEmail(signUpVO.getEmail().trim().toLowerCase());
        account.setPassword(PasswordUtils.createHash(signUpVO.getPassword()));
        account.setRegistrationDate(new Date());
        account.setRegistrationIp(IpUtils.getIpAddress(request));
        account.setStateCode(0);
        accountMapper.insertSelective(account);
        try {
            assert SYSTEM_CONFIG != null;
            Client client =
                    new Client(SYSTEM_CONFIG.getUCenter().getApi(),
                            null,
                            SYSTEM_CONFIG.getUCenter().getKey(),
                            SYSTEM_CONFIG.getUCenter().getAppId(),
                            SYSTEM_CONFIG.getUCenter().getConnect());
            client.ucUserRegister(account.getUserName(), UUID.randomUUID().toString(), account.getEmail());
            // 向Discuz表里插入用户
            DiscuzUcenterMembersDOExample discuzUcenterMembersExample = new DiscuzUcenterMembersDOExample();
            discuzUcenterMembersExample.createCriteria().andUsernameEqualTo(account.getUserName());
            DiscuzUcenterMembersDO discuzUcenterMembers = ListUtils.getOne(discuzUcenterMembersMapper.selectByExample(discuzUcenterMembersExample));
            if (discuzUcenterMembers != null) {
                DiscuzCommonMemberDO commonMemberDO = new DiscuzCommonMemberDO();
                commonMemberDO.setUid(discuzUcenterMembers.getUid());
                commonMemberDO.setEmail(signUpVO.getEmail().trim().toLowerCase());
                commonMemberDO.setUsername(signUpVO.getUserName().trim().toLowerCase());
                commonMemberDO.setGroupid((short) 10);
                commonMemberDO.setRegdate((int) DateUtils.getUnixTimestamp());
                commonMemberDO.setTimeoffset("9999");
                commonMemberDO.setEmailstatus(1);
                discuzCommonMemberMapper.insertSelective(commonMemberDO);
                DiscuzCommonMemberCountDO commonMemberCountDO = new DiscuzCommonMemberCountDO();
                commonMemberCountDO.setUid(discuzUcenterMembers.getUid());
                discuzCommonMemberCountMapper.insertSelective(commonMemberCountDO);
                DiscuzCommonMemberFieldForumDOWithBLOBs commonMemberFieldForumDO = new DiscuzCommonMemberFieldForumDOWithBLOBs();
                commonMemberFieldForumDO.setUid(discuzUcenterMembers.getUid());
                commonMemberFieldForumDO.setMedals("");
                commonMemberFieldForumDO.setSightml("");
                commonMemberFieldForumDO.setGroupterms("");
                commonMemberFieldForumDO.setGroups("");
                discuzCommonMemberFieldForumMapper.insertSelective(commonMemberFieldForumDO);
                DiscuzCommonMemberFieldHomeDOWithBLOBs commonMemberFieldHomeDO = new DiscuzCommonMemberFieldHomeDOWithBLOBs();
                commonMemberFieldHomeDO.setUid(discuzUcenterMembers.getUid());
                commonMemberFieldHomeDO.setSpacecss("");
                commonMemberFieldHomeDO.setBlockposition("");
                commonMemberFieldHomeDO.setRecentnote("");
                commonMemberFieldHomeDO.setSpacenote("");
                commonMemberFieldHomeDO.setPrivacy("");
                commonMemberFieldHomeDO.setFeedfriend("");
                commonMemberFieldHomeDO.setAcceptemail("");
                commonMemberFieldHomeDO.setMagicgift("");
                commonMemberFieldHomeDO.setStickblogs("");
                discuzCommonMemberFieldHomeMapper.insertSelective(commonMemberFieldHomeDO);
                DiscuzCommonMemberProfileDOWithBLOBs commonMemberProfileDO = new DiscuzCommonMemberProfileDOWithBLOBs();
                commonMemberProfileDO.setUid(discuzUcenterMembers.getUid());
                commonMemberProfileDO.setBio("");
                commonMemberProfileDO.setInterest("");
                commonMemberProfileDO.setField1("");
                commonMemberProfileDO.setField2("");
                commonMemberProfileDO.setField3("");
                commonMemberProfileDO.setField4("");
                commonMemberProfileDO.setField5("");
                commonMemberProfileDO.setField6("");
                commonMemberProfileDO.setField7("");
                commonMemberProfileDO.setField8("");
                discuzCommonMemberProfileMapper.insertSelective(commonMemberProfileDO);
                DiscuzCommonMemberStatusDO commonMemberStatusDO = new DiscuzCommonMemberStatusDO();
                commonMemberStatusDO.setUid(discuzUcenterMembers.getUid());
                commonMemberStatusDO.setRegip(IpUtils.getIpAddress(request));
                commonMemberStatusDO.setLastip(IpUtils.getIpAddress(request));
                commonMemberStatusDO.setLastvisit((int) DateUtils.getUnixTimestamp());
                commonMemberStatusDO.setLastactivity((int) DateUtils.getUnixTimestamp());
                commonMemberStatusDO.setLastsendmail(0);
                commonMemberStatusDO.setInvisible(0);
                commonMemberStatusDO.setBuyercredit((short) 0);
                commonMemberStatusDO.setSellercredit((short) 0);
                commonMemberStatusDO.setFavtimes(0);
                commonMemberStatusDO.setSharetimes(0);
                commonMemberStatusDO.setProfileprogress((byte) 0);
                discuzCommonMemberStatusMapper.insertSelective(commonMemberStatusDO);
            }
        } catch (Exception exception) {
            logger.error(exception.getMessage(), exception);
        }
        // 发送激活邮件
        verificationCodeService.sendVerificationCode(true, DateUtils.nextHours(2),
                account.getEmail(), "SIGN_UP", account, SYSTEM_CONFIG.getSiteDomainName() + "/auth/signUp/activation");
    }

    /**
     * 账户激活
     *
     * @param signUpActivationVO
     */
    @Override
    public void activation(SignUpActivationVO signUpActivationVO) {
        if (!StringUtils.isEmail(signUpActivationVO.getEmailOrPhone())
                && !StringUtils.isChinaPhone(signUpActivationVO.getEmailOrPhone())) {
            // 验证的地址既不是手机也不是邮箱，直接拒绝
            throw new BusinessException("请填写正确的邮箱或手机号");
        }
        SysVerificationCode verificationCode = verificationCodeService.verificationCode(signUpActivationVO.getCode(), signUpActivationVO.getEmailOrPhone(), "SIGN_UP");
        if (verificationCode == null) {
            // 验证码找不到，拒绝
            throw new BusinessException("验证码错误或已过期");
        }
        if (!verificationCode.getCode().equals(signUpActivationVO.getCode())) {
            // 验证码对不上，直接拒绝
            throw new BusinessException("验证码错误或已过期");
        }
        // 查找对应的账户
        if (ObjectUtils.isEmpty(verificationCode.getAccountUuid())) {
            logger.error("验证码不包含账户UUID，无法激活账户。验证码查询结果：{}", verificationCode);
            throw new BusinessException("验证码错误或已过期");
        }
        SysAccountExample example = new SysAccountExample();
        example.createCriteria()
                .andUuidEqualTo(verificationCode.getAccountUuid())
                .andStateCodeEqualTo(0);
        SysAccount account = ListUtils.getOne(accountMapper.selectByExample(example));
        if (account == null) {
            logger.warn("无法激活账户,UUID:{}不存在或账户状态不正确", verificationCode.getAccountUuid());
            throw new BusinessException("验证码错误或已过期");
        }
        if (StringUtils.isEmail(signUpActivationVO.getEmailOrPhone())) {
            account.setStateCode(1);
        } else if (StringUtils.isChinaPhone(signUpActivationVO.getEmailOrPhone())) {
            account.setStateCode(2);
        } else {
            throw new BusinessException("验证码错误或已过期");
        }
        accountMapper.updateByPrimaryKeySelective(account);
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
