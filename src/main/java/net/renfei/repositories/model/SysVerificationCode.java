package net.renfei.repositories.model;

import java.io.Serializable;
import java.util.Date;

public class SysVerificationCode implements Serializable {
    private Long id;

    private String uuid;

    private String code;

    private Date expires;

    private String addressee;

    private String channel;

    private Boolean beUsed;

    private String authType;

    private String accountUuid;

    private Date sendTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getExpires() {
        return expires;
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Boolean getBeUsed() {
        return beUsed;
    }

    public void setBeUsed(Boolean beUsed) {
        this.beUsed = beUsed;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public String getAccountUuid() {
        return accountUuid;
    }

    public void setAccountUuid(String accountUuid) {
        this.accountUuid = accountUuid;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", uuid=").append(uuid);
        sb.append(", code=").append(code);
        sb.append(", expires=").append(expires);
        sb.append(", addressee=").append(addressee);
        sb.append(", channel=").append(channel);
        sb.append(", beUsed=").append(beUsed);
        sb.append(", authType=").append(authType);
        sb.append(", accountUuid=").append(accountUuid);
        sb.append(", sendTime=").append(sendTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}