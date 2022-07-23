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

import net.renfei.common.core.service.ObjectStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * 本地磁盘实现的对象存储服务
 *
 * @author renfei
 */
@Service
public class LocalObjectStorageServiceImpl implements ObjectStorageService {
    private final static Logger logger = LoggerFactory.getLogger(LocalObjectStorageServiceImpl.class);

    @Override
    public boolean putObject(String objectKey, InputStream inputStream, long contentLength) {
        File file = new File(objectKey);
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    logger.error("文件创建失败：{}", objectKey);
                    return false;
                }
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
                return false;
            }
        }
        try {
            FileOutputStream fos = new FileOutputStream(objectKey);
            byte[] b = new byte[1024];
            while ((inputStream.read(b)) != -1) {
                fos.write(b);
            }
            inputStream.close();
            fos.close();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    @Override
    public boolean putObject(String objectKey, String objectPath) {
        return false;
    }

    @Override
    public boolean putObject(String objectKey, File file) {
        return false;
    }

    @Override
    public boolean putObject(String objectKey, byte[] bytes) {
        return false;
    }

    @Override
    public File getObject(String objectKey, String filePath) {
        return null;
    }

    @Override
    public byte[] getObject(String objectKey) {
        File file = new File(objectKey);
        if (file.exists()) {
            byte[] data = new byte[(int) file.length()];
            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                fileInputStream.read(data);
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
                return null;
            }
            return data;
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteObject(String objectKey) {
        File file = new File(objectKey);
        if (file.exists()) {
            return file.delete();
        } else {
            return false;
        }
    }
}
