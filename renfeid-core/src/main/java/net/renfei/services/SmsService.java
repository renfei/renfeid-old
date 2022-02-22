package net.renfei.services;

import net.renfei.exception.BusinessException;
import net.renfei.model.system.Sms;

/**
 * 短信服务
 *
 * @author renfei
 */
public interface SmsService {
    String sendSms(Sms sms) throws BusinessException;
}
