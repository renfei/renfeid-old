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
package net.renfei.common.core.service;

import java.io.File;
import java.io.InputStream;

/**
 * 对象存储服务
 *
 * @author renfei
 */
public interface ObjectStorageService {
    /**
     * 存入一个对象
     *
     * @param objectKey   存入对象路径
     * @param inputStream 对象流
     * @return
     */
    boolean putObject(String objectKey, InputStream inputStream, long contentLength);

    /**
     * 存入一个对象
     *
     * @param objectKey  存入对象路径
     * @param objectPath 文件所在路径
     * @return
     */
    boolean putObject(String objectKey, String objectPath);

    /**
     * 存入一个对象
     *
     * @param objectKey 存入对象路径
     * @param file      文件
     * @return
     */
    boolean putObject(String objectKey, File file);

    /**
     * 存入一个对象
     *
     * @param objectKey 存入对象路径
     * @param bytes     对象二进制
     * @return
     */
    boolean putObject(String objectKey, byte[] bytes);

    /**
     * 获取一个文件对象
     *
     * @param objectKey 对象路径
     * @param filePath  写入对象路径
     * @return
     */
    File getObject(String objectKey, String filePath);

    /**
     * 获取一个对象的二进制
     *
     * @param objectKey 对象路径
     * @return
     */
    byte[] getObject(String objectKey);

    /**
     * 删除一个对象
     *
     * @param objectKey 对象路径
     * @return
     */
    boolean deleteObject(String objectKey);
}
