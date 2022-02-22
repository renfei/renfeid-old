package net.renfei.services.system;

import com.aliyun.oss.ServiceException;
import net.renfei.model.system.Sms;
import net.renfei.repositories.SysVerificationCodeMapper;
import net.renfei.repositories.model.SysAccount;
import net.renfei.repositories.model.SysVerificationCode;
import net.renfei.repositories.model.SysVerificationCodeExample;
import net.renfei.services.*;
import net.renfei.utils.ListUtils;
import net.renfei.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 验证码服务
 *
 * @author renfei
 */
@Service
public class VerificationCodeServiceImpl extends BaseService implements VerificationCodeService {
    private static final String REDIS_KEY_VERIFICATION_CODE = REDIS_KEY + "VerificationCode:";
    private final SmsService smsService;
    private final RedisService redisService;
    private final EmailService emailService;
    private final SysVerificationCodeMapper verificationCodeMapper;

    public VerificationCodeServiceImpl(SmsService smsService,
                                       RedisService redisService,
                                       EmailService emailService,
                                       SysVerificationCodeMapper verificationCodeMapper) {
        this.smsService = smsService;
        this.redisService = redisService;
        this.emailService = emailService;
        this.verificationCodeMapper = verificationCodeMapper;
    }

    @Override
    public void sendVerificationCode(boolean numberVerificationCode, Date expires,
                                     String addressee, String authType, SysAccount account, String callBack) {
        String verificationCodeString;
        if (numberVerificationCode) {
            verificationCodeString = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        } else {
            verificationCodeString = StringUtils.getRandomNumber(6);
        }
        SysVerificationCode verificationCode = new SysVerificationCode();
        verificationCode.setUuid(UUID.randomUUID().toString().toUpperCase());
        verificationCode.setCode(verificationCodeString);
        verificationCode.setExpires(expires);
        verificationCode.setAddressee(addressee);
        verificationCode.setAuthType(authType);
        if (account != null) {
            verificationCode.setAccountUuid(account.getUuid());
        }
        verificationCode.setBeUsed(false);
        boolean isEmail = false;
        if (StringUtils.isEmail(addressee)) {
            verificationCode.setChannel("EMAIL");
            isEmail = true;
        } else if (StringUtils.isChinaPhone(addressee)) {
            verificationCode.setChannel("SMS");
        } else {
            throw new ServiceException("验证码发送失败发送地址不正确");
        }
        verificationCode.setSendTime(new Date());
        assert systemConfig != null;
        if (systemConfig.isEnableRedis()) {
            // 存redis
            String key = REDIS_KEY_VERIFICATION_CODE + authType + ":" + addressee;
            redisService.set(key, verificationCode, (expires.getTime() - System.currentTimeMillis()) / 1000);
        } else {
            // 存数据库
            verificationCodeMapper.insertSelective(verificationCode);
        }
        if (isEmail) {
            List<String> contents = new ArrayList<>();
            String userName = "先生/女士";
            if (account != null) {
                userName = account.getUserName();
            }
            contents.add("尊敬的 " + userName + " :");
            contents.add("这封信是由[RENFEI.NET]系统自动发送的。");
            contents.add("您收到这封邮件，是由于在[RENFEI.NET]创建了新的账户或修改 Email 使用了这个邮箱地址，又或者您正在进行敏感操作需要验证您的身份。如果您并没有访问过[RENFEI.NET]或没有进行上述操作，请忽略这封邮件。您不需要退订或进行其他进一步的操作。");
            contents.add("----------------------------------------------------------------------");
            contents.add("帐号验证说明");
            contents.add("----------------------------------------------------------------------");
            contents.add("如果您是[RENFEI.NET]的新用户，或在修改您的注册 Email 时使用了此邮箱地址，又或者您正在进行敏感操作，我们需要对您的操作有效性进行验证以避免非本人操作或服务滥用。");
            if (ObjectUtils.isEmpty(callBack)) {
                contents.add("您的验证码是：");
                contents.add("<span style=\"color:red;font-size:18px;font-weight:800;\">" + verificationCodeString + "</span>");
            } else {
                contents.add("您只需点击下面的链接即可：");
                contents.add("<a href=\"" + callBack + "?code=" + verificationCodeString + "\">" + callBack + "?code=" + verificationCodeString + "</a>");
                contents.add("(如果上面不是链接形式，请将该地址手工粘贴到浏览器地址栏再访问)");
            }
            contents.add("----");
            contents.add("感谢您的访问，祝您使用愉快！");
            emailService.send(addressee, userName, "验证邮件：验证您在[RENFEI.NET]的账户", contents);
        } else {
            Sms sms = new Sms();
            sms.setPhoneNumbers(addressee);
            sms.setSignName(systemConfig.getAliyun().getSms().getSignName());
            sms.setTemplateCode(systemConfig.getAliyun().getSms().getTemplateCode());
            sms.setTemplateParam("{\"code\":\"" + verificationCodeString + "\"}");
            sms.setOutId(verificationCode.getUuid());
            smsService.sendSms(sms);
        }
    }

    @Override
    public SysVerificationCode verificationCode(String code, String addressee, String authType) {
        SysVerificationCode verificationCode;
        assert systemConfig != null;
        if (systemConfig.isEnableRedis()) {
            // 从 Redis 里取
            String key = REDIS_KEY_VERIFICATION_CODE + authType + ":" + addressee;
            if (redisService.hasKey(key)) {
                Object object = redisService.get(key);
                if (object instanceof SysVerificationCode) {
                    redisService.del(key);
                    return (SysVerificationCode) object;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } else {
            // 从数据库里取
            SysVerificationCodeExample example = new SysVerificationCodeExample();
            example.createCriteria()
                    .andCodeEqualTo(code)
                    .andAddresseeEqualTo(addressee)
                    .andAuthTypeEqualTo(authType)
                    .andExpiresGreaterThanOrEqualTo(new Date())
                    .andBeUsedEqualTo(false);
            verificationCode = ListUtils.getOne(verificationCodeMapper.selectByExample(example));
            if (verificationCode != null) {
                verificationCode.setBeUsed(true);
                verificationCodeMapper.updateByPrimaryKeySelective(verificationCode);
            }
            return verificationCode;
        }
    }
}
