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
package net.renfei.common.core.service.aliyun;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import net.renfei.common.api.exception.BusinessException;
import net.renfei.common.core.config.SystemConfig;
import net.renfei.common.core.service.RedisService;
import net.renfei.common.core.service.SMSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static net.renfei.common.core.config.RedisConfig.REDIS_KEY_DATABASE;

/**
 * 阿里云短信服务实现
 *
 * @author renfei
 */
@Service
public class AliyunSMSServiceImpl implements SMSService {
    private final static Logger logger = LoggerFactory.getLogger(AliyunSMSServiceImpl.class);
    private static final String REDIS_RECORD_KEY = REDIS_KEY_DATABASE + ":sms:record:";
    private static final long ONE_MINUTE_TIME = (long) 60 * 1000;
    private static final long ONE_HOUR_TIME = (long) 60 * 60 * 1000;
    private static final long ONE_DAY_TIME = (long) 24 * 60 * 60 * 1000;
    private static final int ONE_MINUTE_SMS_MAX = 1;
    private static final int ONE_HOUR_SMS_MAX = 5;
    private static final int ONE_DAY_SMS_MAX = 10;
    private final Client client;
    private final SystemConfig systemConfig;
    private final RedisService redisService;

    public AliyunSMSServiceImpl(SystemConfig systemConfig,
                                RedisService redisService) throws Exception {
        this.systemConfig = systemConfig;
        this.redisService = redisService;
        Config config = new Config()
                .setAccessKeyId(systemConfig.getAliyun().getAccessKeyId())
                .setAccessKeySecret(systemConfig.getAliyun().getAccessKeySecret());
        // 访问的域名
        config.endpoint = systemConfig.getAliyun().getSms().getEndpoint();
        this.client = new com.aliyun.dysmsapi20170525.Client(config);
    }

    @Override
    public void send(String phoneNumber, String content) {
        this.send(systemConfig.getAliyun().getSms().getTemplateCode(), phoneNumber, "{\"code\":\"" + content + "\"}");
    }

    @Override
    public void send(String templateCode, String phoneNumber, String content) {
        // 检查发送频率，1条/分钟，5条/小时，10条/天
        if (!checkAndRecord(phoneNumber, ONE_MINUTE_TIME, ONE_MINUTE_SMS_MAX)) {
            throw new BusinessException("您的发送请求频率过快，请稍后再试。");
        }
        if (!checkAndRecord(phoneNumber, ONE_HOUR_TIME, ONE_HOUR_SMS_MAX)) {
            throw new BusinessException("您的发送请求频率超过系统阈值，建议1小时后再试。");
        }
        if (!checkAndRecord(phoneNumber, ONE_DAY_TIME, ONE_DAY_SMS_MAX)) {
            throw new BusinessException("您的发送请求频率超过系统阈值，建议12小时后再试。");
        }
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setPhoneNumbers(phoneNumber)
                .setSignName(systemConfig.getAliyun().getSms().getSignName())
                .setTemplateCode(templateCode)
                .setTemplateParam(content);
        RuntimeOptions runtime = new RuntimeOptions();
        try {
            // 复制代码运行请自行打印 API 的返回值
            client.sendSmsWithOptions(sendSmsRequest, runtime);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
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
