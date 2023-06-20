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

import jakarta.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;

/**
 * 请求对象文件服务，注意需要提前进行鉴权，此服务不会进行鉴权
 *
 * @author renfei
 */
public interface RequestObjectService {
    /**
     * 请求对象文件
     *
     * @param filePath 文件路径
     * @param response 响应对象
     * @param inline   是否浏览器预览还是下载
     * @throws FileNotFoundException 文件不存在
     */
    void requestObject(String filePath, HttpServletResponse response, boolean inline) throws FileNotFoundException;
}
