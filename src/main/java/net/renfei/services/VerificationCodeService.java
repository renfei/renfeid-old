package net.renfei.services;

import net.renfei.repositories.model.SysAccount;
import net.renfei.repositories.model.SysVerificationCode;

import java.util.Date;

/**
 * 验证码服务
 *
 * @author renfei
 */
public interface VerificationCodeService {
    void sendVerificationCode(boolean numberVerificationCode, Date expires,
                              String addressee, String authType, SysAccount account, String callBack);
    SysVerificationCode verificationCode(String code, String addressee, String authType);
}
