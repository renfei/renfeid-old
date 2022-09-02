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
package net.renfei.pro.wechat.mp.handler;

import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import net.renfei.common.api.utils.JacksonUtil;
import net.renfei.common.core.entity.LogLevelEnum;
import net.renfei.common.core.entity.OperationTypeEnum;
import net.renfei.common.core.entity.SystemTypeEnum;
import net.renfei.common.core.service.SystemLogService;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 记录所有事件的日志处理器
 *
 * @author renfei
 */
@Component
public class LogHandler extends AbstractHandler {
    private final SystemLogService systemLogService;

    public LogHandler(SystemLogService systemLogService) {
        this.systemLogService = systemLogService;
    }

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage,
                                    Map<String, Object> map, WxMpService wxMpService,
                                    WxSessionManager wxSessionManager) {
        this.logger.debug("\n接收到请求消息，内容：{}", JacksonUtil.obj2String(wxMpXmlMessage));
        systemLogService.save(LogLevelEnum.INFO, SystemTypeEnum.WECHAT_MP, OperationTypeEnum.RETRIEVE,
                wxMpXmlMessage.getContent(), null, null, null);
        return null;
    }
}
