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

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import net.renfei.pro.wechat.mp.builder.TextBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 微信位置消息处理器
 *
 * @author renfei
 */
@Component
public class LocationHandler extends AbstractHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage,
                                    Map<String, Object> map, WxMpService wxMpService,
                                    WxSessionManager wxSessionManager) {
        if (wxMpXmlMessage.getMsgType().equals(WxConsts.XmlMsgType.LOCATION)) {
            String content = "我们已经收到了您的地理位置信息，但我们目前无法处理这类信息。";
            return new TextBuilder().build(content, wxMpXmlMessage, null);
        }
        //上报地理位置事件
        logger.info("微信用户上报地理位置，纬度 : {}，经度 : {}，精度 : {}",
                wxMpXmlMessage.getLatitude(), wxMpXmlMessage.getLongitude(), wxMpXmlMessage.getPrecision());
        String content = "我们已经收到了您的地理位置，经度：%s，纬度：%s，但目前我们无法处理这类信息。";
        return new TextBuilder().build(
                String.format(content, wxMpXmlMessage.getLatitude(), wxMpXmlMessage.getLongitude()), wxMpXmlMessage, null);
    }
}
