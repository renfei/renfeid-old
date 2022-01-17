package net.renfei.model.wechat.xmlmessage;

import net.renfei.model.wechat.message.message.ImageMessage;
import net.renfei.model.wechat.message.message.Message;

public class XMLImageMessage extends XMLMessage{

	private static final long serialVersionUID = 5972200803798750987L;

	private String mediaId;

	public XMLImageMessage(String toUserName, String fromUserName, String mediaId) {
		super(toUserName, fromUserName, "image");
		this.mediaId = mediaId;
	}

	@Override
	public String subXML() {
		return "<Image><MediaId><![CDATA[" + mediaId + "]]></MediaId></Image>";
	}

	@Override
	public Message convert() {
		return new ImageMessage(toUserName, mediaId);
	}

}
