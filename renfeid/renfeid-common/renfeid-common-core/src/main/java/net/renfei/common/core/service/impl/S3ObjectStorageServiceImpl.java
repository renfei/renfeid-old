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

import net.renfei.common.core.config.SystemConfig;
import net.renfei.common.core.service.ObjectStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.*;

/**
 * AWS S3(Simple Storage Service) 实现的对象存储服务
 *
 * @author renfei
 */
@Service
public class S3ObjectStorageServiceImpl implements ObjectStorageService {
    private final static Logger logger = LoggerFactory.getLogger(S3ObjectStorageServiceImpl.class);
    private final SystemConfig systemConfig;
    private final S3Client s3Client;

    public S3ObjectStorageServiceImpl(SystemConfig systemConfig) {
        this.systemConfig = systemConfig;
        ProfileCredentialsProvider credentialsProvider = ProfileCredentialsProvider.create();
        Region region = Region.of(systemConfig.getAws().getRegion());
        this.s3Client = S3Client.builder()
                .region(region)
                .credentialsProvider(credentialsProvider)
                .build();
    }

    @Override
    public boolean putObject(String objectKey, String objectPath) {
        return putObject(objectKey, new File(objectPath));
    }

    @Override
    public boolean putObject(String objectKey, File file) {
        return putObject(objectKey, getObjectFile(file));
    }

    @Override
    public boolean putObject(String objectKey, byte[] bytes) {
        try {
            PutObjectRequest putOb = PutObjectRequest.builder()
                    .bucket(systemConfig.getAws().getBucketName())
                    .key(objectKey)
                    .build();
            s3Client.putObject(putOb, RequestBody.fromBytes(bytes));
            return true;
        } catch (S3Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public File getObject(String objectKey, String filePath) {
        byte[] data = getObject(objectKey);
        BufferedInputStream bufferedInputStream = null;
        FileOutputStream fileOutputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            File file = new File(filePath);
            // 获取文件的父路径字符串
            File path = file.getParentFile();
            if (!path.exists()) {
                if (!path.mkdirs()) {
                    logger.error("create file fail");
                    return null;
                }
            }
            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(data);
            bufferedInputStream = new BufferedInputStream(byteInputStream);
            fileOutputStream = new FileOutputStream(file);
            // 实例化OutputString 对象
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            byte[] buffer = new byte[1024];
            int length = bufferedInputStream.read(buffer);
            while (length != -1) {
                bufferedOutputStream.write(buffer, 0, length);
                length = bufferedInputStream.read(buffer);
            }
            bufferedOutputStream.flush();
            return file;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        } finally {
            try {
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
            } catch (IOException e0) {
                logger.error(e0.getMessage(), e0);
            }
        }
    }

    @Override
    public byte[] getObject(String objectKey) {
        try {
            GetObjectRequest objectRequest = GetObjectRequest
                    .builder()
                    .key(objectKey)
                    .bucket(systemConfig.getAws().getBucketName())
                    .build();

            ResponseBytes<GetObjectResponse> objectBytes = s3Client.getObjectAsBytes(objectRequest);
            return objectBytes.asByteArray();
        } catch (S3Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public boolean deleteObject(String objectKey) {
        ObjectIdentifier objectId = ObjectIdentifier
                .builder()
                .key(objectKey)
                .build();

        Delete del = Delete.builder()
                .objects(objectId)
                .build();

        try {
            DeleteObjectsRequest multiObjectDeleteRequest = DeleteObjectsRequest.builder()
                    .bucket(systemConfig.getAws().getBucketName())
                    .delete(del)
                    .build();

            s3Client.deleteObjects(multiObjectDeleteRequest);
            return true;
        } catch (S3Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    private byte[] getObjectFile(File file) {
        FileInputStream fileInputStream = null;
        byte[] bytesArray = null;
        try {
            bytesArray = new byte[(int) file.length()];
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytesArray);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bytesArray;
    }
}
