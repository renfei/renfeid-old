package net.renfei.services.aliyun;

import com.aliyuncs.green.model.v20180509.TextScanRequest;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.http.HttpResponse;
import net.renfei.services.aliyun.model.AliyunGreenAO;
import net.renfei.services.aliyun.model.AliyunGreenVO;
import net.renfei.utils.JacksonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 阿里云绿网
 *
 * @author renfei
 */
@Service
public class AliyunGreen extends AbstractAliyunService {
    private static final Logger logger = LoggerFactory.getLogger(AliyunGreen.class);

    /**
     * @param text 待检测的文本，长度不超过10000个字符
     */
    public boolean textScan(String text) {
        TextScanRequest textScanRequest = new TextScanRequest();
        // 指定api返回格式
        textScanRequest.setSysAcceptFormat(FormatType.JSON);
        textScanRequest.setHttpContentType(FormatType.JSON);
        // 指定请求方法
        textScanRequest.setSysMethod(com.aliyuncs.http.MethodType.POST);
        textScanRequest.setSysEncoding("UTF-8");
        textScanRequest.setSysRegionId(SYSTEM_CONFIG.getAliyun().getGreen().getRegionId());

        List<AliyunGreenAO.Task> tasks = new ArrayList<>();
        AliyunGreenAO.Task task = new AliyunGreenAO.Task();
        task.setDataId(UUID.randomUUID().toString());
        /**
         * 待检测的文本，长度不超过10000个字符
         */
        task.setContent(text);
        tasks.add(task);
        AliyunGreenAO data = new AliyunGreenAO();

        /**
         * 检测场景，文本垃圾检测传递：antispam
         **/
        data.setScenes(Arrays.asList("antispam"));
        data.setTasks(tasks);
        try {
            textScanRequest.setHttpContent(JacksonUtil.obj2String(data).getBytes(StandardCharsets.UTF_8), "UTF-8", FormatType.JSON);
            // 请务必设置超时时间
            textScanRequest.setSysConnectTimeout(3000);
            textScanRequest.setSysReadTimeout(6000);
            HttpResponse httpResponse = client.doAction(textScanRequest);
            if (httpResponse.isSuccess()) {
                AliyunGreenVO aliyunGreenVO = JacksonUtil.string2Obj(new String(httpResponse.getHttpContent(), StandardCharsets.UTF_8), AliyunGreenVO.class);
                if (200 == aliyunGreenVO.getCode()) {
                    for (AliyunGreenVO.Data taskResult : aliyunGreenVO.getData()) {
                        if (200 == taskResult.getCode()) {
                            List<AliyunGreenVO.Data.Result> results = taskResult.getResults();
                            for (AliyunGreenVO.Data.Result sceneResult : results) {
                                String scene = sceneResult.getScene();
                                String suggestion = sceneResult.getSuggestion();
                                String label = sceneResult.getLabel();
                                Double rate = sceneResult.getRate();
                                //根据scene和suggetion做相关处理
                                //suggestion == pass 未命中垃圾  suggestion == block 命中了垃圾，可以通过label字段查看命中的垃圾分类
                                logger.info("scene = [" + scene + "]");
                                logger.info("suggestion = [" + suggestion + "]");
                                logger.info("label = [" + label + "]");
                                logger.info("rate = [" + rate + "]");
                                return "pass".equals(suggestion);
                            }
                        } else {
                            logger.info("task process fail:" + taskResult.getCode());
                            logger.info("task process Msg:" + taskResult.getMsg());
                            return false;
                        }
                    }
                } else {
                    logger.info("detect not success. code:" + aliyunGreenVO.getCode());
                    logger.info("detect not success. Msg:" + aliyunGreenVO.getMsg());
                    return false;
                }
            } else {
                logger.info("response not success. status:" + httpResponse.getStatus());
                return false;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return true;
        }
        return false;
    }
}
