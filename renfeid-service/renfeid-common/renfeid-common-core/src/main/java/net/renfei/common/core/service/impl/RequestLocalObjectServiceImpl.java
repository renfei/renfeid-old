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

import net.renfei.common.api.constant.enums.ContentTypeEnum;
import net.renfei.common.core.service.RequestObjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * 请求对象文件服务，注意需要提前进行鉴权，此服务不会进行鉴权
 *
 * @author renfei
 */
@Service
public class RequestLocalObjectServiceImpl implements RequestObjectService {
    private final static Logger logger = LoggerFactory.getLogger(RequestLocalObjectServiceImpl.class);

    /**
     * 请求对象文件
     *
     * @param filePath 文件路径
     * @param response 响应对象
     * @param inline   是否浏览器预览还是下载
     * @throws FileNotFoundException 文件不存在
     */
    @Override
    public void requestObject(String filePath, HttpServletResponse response, boolean inline) throws FileNotFoundException {
        // 设置编码
        response.setCharacterEncoding("UTF-8");
        File file = new File(filePath);
        if (file.exists()) {
            String fileName = file.getName();
            String fileType = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
            // 清空response
            response.reset();
            if (inline) {
                response.addHeader("Content-Disposition", "inline;filename=" + fileName);
            } else {
                response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            }
            response.setContentType(ContentTypeEnum.getDesc(fileType));
            try {
                ServletOutputStream servletOutputStream = response.getOutputStream();
                //获取文件输入流
                FileInputStream fileInputStream = new FileInputStream(file);
                //获取输出流通道
                WritableByteChannel writableByteChannel = Channels.newChannel(servletOutputStream);
                FileChannel fileChannel = fileInputStream.getChannel();
                //采用零拷贝的方式实现文件的下载
                fileChannel.transferTo(0, fileChannel.size(), writableByteChannel);
                //关闭对应的资源
                fileChannel.close();
                servletOutputStream.flush();
                writableByteChannel.close();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        } else {
            // 文件不存在
            throw new FileNotFoundException(filePath + " not found.");
        }
    }
}
