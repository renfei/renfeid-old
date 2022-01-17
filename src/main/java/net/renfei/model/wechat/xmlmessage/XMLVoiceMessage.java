package net.renfei.model.wechat.xmlmessage;

import net.renfei.model.wechat.message.message.Message;
import net.renfei.model.wechat.message.message.VoiceMessage;

public class XMLVoiceMessage extends XMLMessage {

	private static final long serialVersionUID = 2372031664279510333L;

	private String mediaId;

	public XMLVoiceMessage(String toUserName, String fromUserName, String mediaId) {
		super(toUserName, fromUserName, "voice");
		this.mediaId = mediaId;
	}

	@Override
	public String subXML() {
		return "<Voice><MediaId><![CDATA[" + mediaId + "]]></MediaId></Voice>";
	}

	@Override
	public Message convert() {
		return new VoiceMessage(toUserName, mediaId);
	}

}
