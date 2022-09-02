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
import net.renfei.pro.wechat.mp.builder.TextBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Map;

/**
 * 微信语音消息处理器
 *
 * @author renfei
 */
@Component
public class VoiceHandler extends AbstractHandler {
    private final MsgHandler msgHandler;

    public VoiceHandler(MsgHandler msgHandler) {
        this.msgHandler = msgHandler;
    }

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage,
                                    Map<String, Object> map, WxMpService wxMpService,
                                    WxSessionManager wxSessionManager) {
        if(ObjectUtils.isEmpty(wxMpXmlMessage.getRecognition())){
            logger.info("微信用户发送了语音，无法处理MediaId：{}", wxMpXmlMessage.getPicUrl());
            return new TextBuilder().build("我们收到了您的语音消息，但我们目前暂时无法处理，请尝试文字输入。", wxMpXmlMessage, wxMpService);
        }else {
            wxMpXmlMessage.setContent(wxMpXmlMessage.getRecognition());
            return msgHandler.handle(wxMpXmlMessage, map, wxMpService, wxSessionManager);
        }
    }
}
