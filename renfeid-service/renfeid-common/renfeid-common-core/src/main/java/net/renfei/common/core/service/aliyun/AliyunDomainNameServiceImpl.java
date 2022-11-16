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

import com.aliyun.alidns20150109.Client;
import com.aliyun.alidns20150109.models.*;
import com.aliyun.tea.TeaException;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import net.renfei.common.core.config.SystemConfig;
import net.renfei.common.core.service.DomainNameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 阿里云解析
 *
 * @author renfei
 */
@Service
public class AliyunDomainNameServiceImpl implements DomainNameService {
    private final static Logger logger = LoggerFactory.getLogger(AliyunDomainNameServiceImpl.class);
    private final static String ENDPOINT = "alidns.cn-hangzhou.aliyuncs.com";
    private final Client client;

    public AliyunDomainNameServiceImpl(SystemConfig systemConfig) throws Exception {
        Config config = new Config()
                .setAccessKeyId(systemConfig.getAliyun().getAccessKeyId())
                .setAccessKeySecret(systemConfig.getAliyun().getAccessKeySecret());
        config.endpoint = ENDPOINT;
        client = new Client(config);
    }

    @Override
    public String addDomainRecord(String host, String domain, String type, String value, long ttl, Long priority) {
        AddDomainRecordRequest addDomainRecordRequest = new AddDomainRecordRequest()
                .setRR(host)
                .setDomainName(domain)
                .setType(type)
                .setValue(value)
                .setTTL(ttl);
        if ("MX".equals(type) && priority != null) {
            addDomainRecordRequest.setPriority(priority);
        }
        RuntimeOptions runtime = new RuntimeOptions();
        try {
            AddDomainRecordResponse addDomainRecordResponse =
                    client.addDomainRecordWithOptions(addDomainRecordRequest, runtime);
            if (addDomainRecordResponse.statusCode == 200) {
                return addDomainRecordResponse.getBody().getRecordId();
            } else {
                logger.error(addDomainRecordResponse.getHeaders().toString());
            }
        } catch (TeaException error) {
            logger.error(error.message, error);
        } catch (Exception error) {
            logger.error(error.getMessage(), error);
        }
        return null;
    }

    @Override
    public boolean deleteDomainRecord(String recordId) {
        DeleteDomainRecordRequest deleteDomainRecordRequest = new DeleteDomainRecordRequest()
                .setRecordId(recordId);
        RuntimeOptions runtime = new RuntimeOptions();
        try {
            DeleteDomainRecordResponse deleteDomainRecordResponse =
                    client.deleteDomainRecordWithOptions(deleteDomainRecordRequest, runtime);
            return deleteDomainRecordResponse.statusCode == 200;
        } catch (TeaException error) {
            logger.error(error.message, error);
        } catch (Exception error) {
            logger.error(error.getMessage(), error);
        }
        return false;
    }

    @Override
    public boolean deleteDomainRecord(String host, String domain, String type) {
        DeleteSubDomainRecordsRequest deleteSubDomainRecordsRequest = new DeleteSubDomainRecordsRequest();
        RuntimeOptions runtime = new RuntimeOptions();
        try {
            DeleteSubDomainRecordsResponse deleteSubDomainRecordsResponse =
                    client.deleteSubDomainRecordsWithOptions(deleteSubDomainRecordsRequest, runtime);
            return deleteSubDomainRecordsResponse.statusCode == 200;
        } catch (TeaException error) {
            logger.error(error.message, error);
        } catch (Exception error) {
            logger.error(error.getMessage(), error);
        }
        return false;
    }

    @Override
    public String updateDomainRecord(String recordId, String host, String type, String value, long ttl, Long priority) {
        UpdateDomainRecordRequest updateDomainRecordRequest = new UpdateDomainRecordRequest()
                .setRecordId(recordId)
                .setRR(host)
                .setType(type)
                .setValue(value)
                .setTTL(ttl);
        if ("MX".equals(type) && priority != null) {
            updateDomainRecordRequest.setPriority(priority);
        }
        RuntimeOptions runtime = new RuntimeOptions();
        try {
            UpdateDomainRecordResponse updateDomainRecordResponse =
                    client.updateDomainRecordWithOptions(updateDomainRecordRequest, runtime);
            if (updateDomainRecordResponse.statusCode == 200) {
                return updateDomainRecordResponse.getBody().getRecordId();
            } else {
                logger.error(updateDomainRecordResponse.getHeaders().toString());
            }
        } catch (TeaException error) {
            logger.error(error.message, error);
        } catch (Exception error) {
            logger.error(error.getMessage(), error);
        }
        return null;
    }
}
