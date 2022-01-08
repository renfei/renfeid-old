package net.renfei.controllers.api.impl;

import net.renfei.controllers.BaseController;
import net.renfei.controllers.api.WeChatMpApi;
import net.renfei.model.wechat.message.EventMessage;
import net.renfei.model.wechat.xmlmessage.XMLMessage;
import net.renfei.services.WeChatService;
import net.renfei.utils.wechat.SignatureUtil;
import net.renfei.utils.wechat.XMLConverUtil;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * 微信公众号API对接
 *
 * @author renfei
 */
@Controller
public class WeChatMpApiController extends BaseController implements WeChatMpApi {
    private final WeChatService weChatService;

    public WeChatMpApiController(WeChatService weChatService) {
        this.weChatService = weChatService;
    }

    @Override
    public void doPost(HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        ServletOutputStream outputStream = response.getOutputStream();
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        //验证请求签名
        assert SYSTEM_CONFIG != null;
        if (!signature.equals(SignatureUtil.generateEventMessageSignature(SYSTEM_CONFIG.getWeChat().getToken(), timestamp, nonce))) {
            System.out.println("The request signature is invalid");
            return;
        }
        if (echostr != null) {
            outputStreamWrite(outputStream, echostr);
            return;
        }
        if (inputStream != null) {
            //转换XML
            EventMessage eventMessage = XMLConverUtil.convertToObject(EventMessage.class, inputStream);
            //处理回复
            XMLMessage xmlTextMessage = weChatService.weChatMessageHandel(eventMessage);
            //回复
            xmlTextMessage.outputStreamWrite(outputStream);
            return;
        }
        outputStreamWrite(outputStream, "");
    }

    /**
     * 数据流输出
     *
     * @param outputStream
     * @param text
     */
    private void outputStreamWrite(OutputStream outputStream, String text) {
        try {
            outputStream.write(text.getBytes(StandardCharsets.UTF_8));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
