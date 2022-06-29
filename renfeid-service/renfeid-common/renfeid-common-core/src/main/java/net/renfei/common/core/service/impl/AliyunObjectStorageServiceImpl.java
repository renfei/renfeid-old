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

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;
import net.renfei.common.core.config.SystemConfig;
import net.renfei.common.core.service.ObjectStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * 阿里云OSS实现
 *
 * @author renfei
 */
@Service
public class AliyunObjectStorageServiceImpl implements ObjectStorageService {
    private static final Logger logger = LoggerFactory.getLogger(AliyunObjectStorageServiceImpl.class);
    private final SystemConfig systemConfig;

    public AliyunObjectStorageServiceImpl(SystemConfig systemConfig) {
        this.systemConfig = systemConfig;
    }

    @Override
    public boolean putObject(String objectKey, InputStream inputStream, long contentLength) {
        OSS ossClient = null;
        try {
            // 创建OSSClient实例。
            ossClient = new OSSClientBuilder()
                    .build(systemConfig.getAliyun().getOss().getEndpoint(),
                            systemConfig.getAliyun().getAccessKeyId(),
                            systemConfig.getAliyun().getAccessKeySecret());
            ossClient.putObject(systemConfig.getAliyun().getOss().getBucketName(), objectKey, inputStream);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    @Override
    public boolean putObject(String objectKey, String objectPath) {
        return this.putObject(objectKey, new File(objectPath));
    }

    @Override
    public boolean putObject(String objectKey, File file) {
        if (!file.exists()) {
            logger.error("file not exists.");
            return false;
        }
        try {
            return this.putObject(objectKey, new FileInputStream(file), file.length());
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean putObject(String objectKey, byte[] bytes) {
        return this.putObject(objectKey, new ByteArrayInputStream(bytes), bytes.length);
    }

    @Override
    public File getObject(String objectKey, String filePath) {
        final File file = new File(filePath);
        try {
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    logger.error("File creation failed.");
                    return null;
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
        //buff用于存放循环读取的临时数据
        byte[] buff = new byte[1024];
        try (final InputStream inputStream = this.getObjectInputStream(objectKey);
             final FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            if (inputStream == null) {
                return null;
            }
            while (inputStream.read(buff) > 0) {
                fileOutputStream.write(buff);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
        return null;
    }

    @Override
    public byte[] getObject(String objectKey) {
        try (final InputStream inputStream = this.getObjectInputStream(objectKey);
             ByteArrayOutputStream swapStream = new ByteArrayOutputStream()) {
            if (inputStream == null) {
                return null;
            }
            //buff用于存放循环读取的临时数据
            byte[] buff = new byte[1024];
            while (inputStream.read(buff) > 0) {
                swapStream.write(buff);
            }
            return swapStream.toByteArray();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public boolean deleteObject(String objectKey) {
        OSS ossClient = null;
        try {
            // 创建OSSClient实例。
            ossClient = new OSSClientBuilder()
                    .build(systemConfig.getAliyun().getOss().getEndpoint(),
                            systemConfig.getAliyun().getAccessKeyId(),
                            systemConfig.getAliyun().getAccessKeySecret());
            ossClient.deleteObject(systemConfig.getAliyun().getOss().getBucketName(), objectKey);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    private InputStream getObjectInputStream(String objectKey) {
        OSS ossClient = null;
        try {
            // 创建OSSClient实例。
            ossClient = new OSSClientBuilder()
                    .build(systemConfig.getAliyun().getOss().getEndpoint(),
                            systemConfig.getAliyun().getAccessKeyId(),
                            systemConfig.getAliyun().getAccessKeySecret());
            OSSObject ossObject = ossClient.getObject(systemConfig.getAliyun().getOss().getBucketName(), objectKey);
            if (ossObject == null) {
                return null;
            }
            return ossObject.getObjectContent();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}
