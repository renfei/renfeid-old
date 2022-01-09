package net.renfei.services.aliyun;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import net.renfei.exception.BusinessException;
import net.renfei.model.system.Sms;
import net.renfei.services.SmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author renfei
 */
@Service
public class AliyunSms extends AliyunService implements SmsService {
    private final static Logger logger = LoggerFactory.getLogger(AliyunSms.class);

    @Override
    public String sendSms(Sms sms) throws BusinessException {
        // TODO 检查发送频率，1条/分钟，5条/小时，10条/天
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
}
