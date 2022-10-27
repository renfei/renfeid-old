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

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.cdn.model.v20180510.BatchSetCdnDomainConfigRequest;
import com.aliyuncs.cdn.model.v20180510.DescribeCdnDomainConfigsRequest;
import com.aliyuncs.cdn.model.v20180510.DescribeCdnDomainConfigsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.green.model.v20180509.TextScanRequest;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.http.HttpResponse;
import com.aliyuncs.profile.DefaultProfile;
import net.renfei.common.api.utils.JacksonUtil;
import net.renfei.common.core.config.SystemConfig;
import net.renfei.common.core.service.aliyun.model.AliyunGreenAO;
import net.renfei.common.core.service.aliyun.model.AliyunGreenVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 阿里云服务
 *
 * @author renfei
 */
@Service
public class AliyunService {
    private static final Logger logger = LoggerFactory.getLogger(AliyunService.class);
    private final IAcsClient client;
    private final SystemConfig systemConfig;

    public AliyunService(SystemConfig systemConfig) {
        this.client = new DefaultAcsClient(DefaultProfile.getProfile(
                systemConfig.getAliyun().getGreen().getRegionId(),
                systemConfig.getAliyun().getAccessKeyId(),
                systemConfig.getAliyun().getAccessKeySecret()));
        this.systemConfig = systemConfig;
    }

    public boolean textScan(String text) {
        TextScanRequest textScanRequest = new TextScanRequest();
        // 指定api返回格式
        textScanRequest.setAcceptFormat(FormatType.JSON);
        textScanRequest.setHttpContentType(FormatType.JSON);
        // 指定请求方法
        textScanRequest.setMethod(com.aliyuncs.http.MethodType.POST);
        textScanRequest.setEncoding("UTF-8");
        textScanRequest.setRegionId(systemConfig.getAliyun().getGreen().getRegionId());

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
            textScanRequest.setConnectTimeout(3000);
            textScanRequest.setReadTimeout(6000);
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

    /**
     * 获取CDN黑名单
     *
     * @param domainName 域名
     * @return 黑名单
     */
    public List<String> getCdnDomainBlackList(String domainName) {
        List<String> blackList = new ArrayList<>();
        DescribeCdnDomainConfigsRequest request = new DescribeCdnDomainConfigsRequest();
        request.setDomainName(domainName);
        request.setFunctionNames("ip_black_list_set");
        try {
            DescribeCdnDomainConfigsResponse response = client.getAcsResponse(request);
            for (DescribeCdnDomainConfigsResponse.DomainConfig config : response.getDomainConfigs()
            ) {
                if ("ip_black_list_set".equals(config.getFunctionName())) {
                    for (DescribeCdnDomainConfigsResponse.DomainConfig.FunctionArg arg : config.getFunctionArgs()
                    ) {
                        blackList.add(arg.getArgValue());
                    }
                    break;
                }
            }

        } catch (ClientException e) {
            logger.error(e.getMessage(), e);
        }
        return blackList;
    }

    /**
     * 设置CDN黑名单
     *
     * @param domainName 域名
     * @param blackList  黑名单
     */
    public void setCdnDomainBlackList(String domainName, List<String> blackList) {
        BatchSetCdnDomainConfigRequest request = new BatchSetCdnDomainConfigRequest();
        request.setDomainNames(domainName);
        StringBuilder functions = new StringBuilder("[{\"functionName\":\"ip_black_list_set\",\"functionArgs\": [")
                .append("{\"argName\": \"ip_list\",\"argValue\": \"");
        for (String blackIp : blackList
        ) {
            functions
                    .append(blackIp)
                    .append(",");
        }
        if (functions.toString().endsWith(",")) {
            String functionsString = functions.toString();
            functions = new StringBuilder(functionsString.substring(0, functionsString.length() - 1));
        }
        functions.append("\"}]}]");
        System.out.println(functions);
        request.setFunctions(functions.toString());
        try {
            client.getAcsResponse(request);
        } catch (ClientException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
