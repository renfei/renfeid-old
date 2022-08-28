/*
 *   Copyright 2022 RenFei(i@renfei.net)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.renfei.common.core.service.impl;

import net.renfei.uaa.api.entity.UserDetail;
import net.renfei.common.api.exception.BusinessException;
import net.renfei.common.api.utils.StringUtils;
import net.renfei.common.core.service.EmailService;
import net.renfei.common.core.service.RedisService;
import net.renfei.common.core.service.SMSService;
import net.renfei.common.core.service.VerificationCodeService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static net.renfei.common.core.config.RedisConfig.REDIS_KEY_DATABASE;

/**
 * @author renfei
 */
@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {
    private static final String REDIS_KEY_VERIFICATION_CODE = REDIS_KEY_DATABASE + ":VerificationCode:";
    private final SMSService smsService;
    private final RedisService redisService;
    private final EmailService emailService;

    public VerificationCodeServiceImpl(SMSService smsService,
                                       RedisService redisService,
                                       EmailService emailService) {
        this.smsService = smsService;
        this.redisService = redisService;
        this.emailService = emailService;
    }

    @Override
    public void sendVerificationCode(boolean uuidVerificationCode, Date expires, String addressee,
                                     String authType, UserDetail account, String callBack) {
        String verificationCodeString;
        if (uuidVerificationCode) {
            verificationCodeString = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        } else {
            verificationCodeString = StringUtils.getRandomNumber(6);
        }
        boolean isEmail = false, isPhone = false;
        if (StringUtils.isEmail(addressee)) {
            isEmail = true;
        } else if (StringUtils.isChinaPhone(addressee)) {
            isPhone = true;
        } else {
            throw new BusinessException("验证码发送失败发送地址不正确");
        }
        // 存redis
        String key = REDIS_KEY_VERIFICATION_CODE + authType + ":" + addressee;
        redisService.set(key, verificationCodeString, (expires.getTime() - System.currentTimeMillis()) / 1000);
        if (isEmail) {
            List<String> contents = new ArrayList<>();
            String userName = "先生/女士";
            if (account != null) {
                userName = account.getUsername();
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
        } else if (isPhone) {
            smsService.send(addressee, verificationCodeString);
        } else {
            throw new BusinessException("验证码发送失败发送地址不正确");
        }
    }

    @Override
    public boolean verificationCode(String code, String addressee, String authType) {
        String key = REDIS_KEY_VERIFICATION_CODE + authType + ":" + addressee;
        if (redisService.hasKey(key)) {
            Object object = redisService.get(key);
            return object.toString().equals(code);
        } else {
            return false;
        }
    }
}
