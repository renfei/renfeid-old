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
package net.renfei.pro.wechat.mp.builder;

import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * 微信图文消息构建器
 * @author renfei
 */
public class NewsBuilder {
    public WxMpXmlOutMessage build(WxMpXmlOutNewsMessage.Item item, WxMpXmlMessage wxMessage) {
        List<WxMpXmlOutNewsMessage.Item> articles = new ArrayList<WxMpXmlOutNewsMessage.Item>() {{
            this.add(item);
        }};
        return this.build(articles, wxMessage);
    }

    public WxMpXmlOutMessage build(List<WxMpXmlOutNewsMessage.Item> articles,
                                   WxMpXmlMessage wxMessage) {
        return WxMpXmlOutMessage.NEWS().articles(articles)
                .fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
                .build();
    }
}
