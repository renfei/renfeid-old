package net.renfei.services.aliyun;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import net.renfei.config.SystemConfig;
import net.renfei.exception.BusinessException;
import net.renfei.model.system.Sms;
import net.renfei.services.RedisService;
import net.renfei.services.SmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author renfei
 */
@Service
public class AliyunSmsImpl extends AbstractAliyunService implements SmsService {
    private static final String REDIS_RECORD_KEY = "renfeid:sms:record:";
    private static final long ONE_MINUTE_TIME = (long) 60 * 1000;
    private static final long ONE_HOUR_TIME = (long) 60 * 60 * 1000;
    private static final long ONE_DAY_TIME = (long) 24 * 60 * 60 * 1000;
    private static final int ONE_MINUTE_SMS_MAX = 1;
    private static final int ONE_HOUR_SMS_MAX = 5;
    private static final int ONE_DAY_SMS_MAX = 10;
    private final RedisService redisService;
    private static final Logger logger = LoggerFactory.getLogger(AliyunSmsImpl.class);

    public AliyunSmsImpl(RedisService redisService, SystemConfig systemConfig) {
        super(systemConfig);
        this.redisService = redisService;
    }

    @Override
    public String sendSms(Sms sms) throws BusinessException {
        // 检查发送频率，1条/分钟，5条/小时，10条/天
        if (!checkAndRecord(sms.getPhoneNumbers(), ONE_MINUTE_TIME, ONE_MINUTE_SMS_MAX)) {
            throw new BusinessException("您的发送请求频率过快，请稍后再试。");
        }
        if (!checkAndRecord(sms.getPhoneNumbers(), ONE_HOUR_TIME, ONE_HOUR_SMS_MAX)) {
            throw new BusinessException("您的发送请求频率超过系统阈值，建议1小时后再试。");
        }
        if (!checkAndRecord(sms.getPhoneNumbers(), ONE_DAY_TIME, ONE_DAY_SMS_MAX)) {
            throw new BusinessException("您的发送请求频率超过系统阈值，建议12小时后再试。");
        }
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", SYSTEM_CONFIG.getAliyun().getSms().getRegionId());
        request.putQueryParameter("PhoneNumbers", sms.getPhoneNumbers());
        request.putQueryParameter("SignName", sms.getSignName());
        request.putQueryParameter("TemplateCode", sms.getTemplateCode());
        request.putQueryParameter("TemplateParam", sms.getTemplateParam());
        request.putQueryParameter("OutId", sms.getOutId());
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            logger.error(e.getMessage(), e);
        } catch (ClientException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 检查短信发送记录
     *
     * @param phone          手机号
     * @param longTimeMillis 检查的时间窗口
     * @param maxSize        允许的最大发送条数
     * @return
     */
    private boolean checkAndRecord(String phone, Long longTimeMillis, int maxSize) {
        String key = REDIS_RECORD_KEY + longTimeMillis + ":" + phone;
        if (redisService.hasKey(key)) {
            long size = redisService.lSize(key);
            if (size <= maxSize) {
                redisService.lPush(key, System.currentTimeMillis());
                return true;
            } else {
                List<Object> t = redisService.lRange(key, 0, size);
                long now = System.currentTimeMillis();
                if (now - Long.parseLong(t.get(0).toString()) > longTimeMillis) {
                    // 最开始的一条距现在超过指定时间窗口就移除左边的
                    redisService.lPop(key);
                    // 并添加一条
                    redisService.lPush(key, System.currentTimeMillis());
                    return true;
                } else {
                    //最左的一条也在指定时间内，不能发送短信
                    return false;
                }
            }
        } else {
            redisService.lPush(key, System.currentTimeMillis());
            return true;
        }
    }
}
