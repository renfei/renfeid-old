package net.renfei.services;

import net.renfei.model.wechat.message.EventMessage;
import net.renfei.model.wechat.xmlmessage.XMLMessage;

/**
 * 微信服务
 *
 * @author renfei
 */
public interface WeChatService {
    XMLMessage weChatMessageHandel(EventMessage eventMessage);
}
