package net.renfei.model.wechat.xmlmessage;

import net.renfei.model.wechat.message.message.Message;
import net.renfei.utils.wechat.mp.AesException;
import net.renfei.utils.wechat.mp.WXBizMsgCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

public abstract class XMLMessage implements Serializable{
	private static final long serialVersionUID = -314420603322403668L;
	private static final Logger logger = LoggerFactory.getLogger(XMLMessage.class);
	protected String toUserName;
	protected String fromUserName;
	protected String msgType;

	protected XMLMessage(String toUserName, String fromUserName, String msgType) {
		super();
		this.toUserName = toUserName;
		this.fromUserName = fromUserName;
		this.msgType = msgType;
	}

	/**
	 * 子类自定义XML
	 * @return XML
	 */
	public abstract String subXML();
	
	/**
	 * 转换为  Message 对象
	 * @return Message
	 */
	public abstract Message convert();

	public String toXML(){
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		sb.append("<ToUserName><![CDATA["+toUserName+"]]></ToUserName>");
		sb.append("<FromUserName><![CDATA["+fromUserName+"]]></FromUserName>");
		sb.append("<CreateTime>"+System.currentTimeMillis()/1000+"</CreateTime>");
		sb.append("<MsgType><![CDATA["+msgType+"]]></MsgType>");
		sb.append(subXML());
		sb.append("</xml>");
		return sb.toString();
	}

	public boolean outputStreamWrite(OutputStream outputStream){
		try {
			outputStream.write(toXML().getBytes("utf-8"));
			outputStream.flush();
		} catch (UnsupportedEncodingException e) {
			logger.error("", e);
			return false;
		} catch (IOException e) {
			logger.error("", e);
			return false;
		}
		return true;
	}

	public boolean outputStreamWrite(OutputStream outputStream, WXBizMsgCrypt bizMsgCrypt){
		if(bizMsgCrypt != null){
			try {
				String outputStr = bizMsgCrypt.encryptMsg(toXML(), System.currentTimeMillis()+"",UUID.randomUUID().toString());
				outputStream.write(outputStr.getBytes("utf-8"));
				outputStream.flush();
			} catch (UnsupportedEncodingException e) {
				logger.error("", e);
				return false;
			} catch (IOException e) {
				logger.error("", e);
				return false;
			} catch (AesException e) {
				logger.error("", e);
				return false;
			}
			return true;
		}else{
			return outputStreamWrite(outputStream);
		}
	}

	public String getToUserName() {
		return toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public String getMsgType() {
		return msgType;
	}
	
	
}
