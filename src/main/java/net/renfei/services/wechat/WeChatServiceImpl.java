package net.renfei.services.wechat;

import net.renfei.model.wechat.message.EventMessage;
import net.renfei.model.wechat.xmlmessage.XMLMessage;
import net.renfei.model.wechat.xmlmessage.XMLTextMessage;
import net.renfei.services.BaseService;
import net.renfei.services.WeChatService;
import org.springframework.stereotype.Service;

/**
 * 微信服务
 *
 * @author renfei
 */
@Service
public class WeChatServiceImpl extends BaseService implements WeChatService {
    @Override
    public XMLMessage weChatMessageHandel(EventMessage eventMessage) {
        System.out.println(eventMessage.getMsgType());
        System.out.println(eventMessage.getContent());
        System.out.println(eventMessage.getRecognition());
        return new XMLTextMessage(
                eventMessage.getFromUserName(),
                eventMessage.getToUserName(),
                "你好呀");
    }
}
