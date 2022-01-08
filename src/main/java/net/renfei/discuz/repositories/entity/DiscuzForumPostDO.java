package net.renfei.discuz.repositories.entity;

public class DiscuzForumPostDO extends net.renfei.discuz.repositories.entity.DiscuzForumPostDOKey {
    private Integer pid;

    private Integer fid;

    private Integer first;

    private String author;

    private Integer authorid;

    private String subject;

    private Integer dateline;

    private String useip;

    private Integer port;

    private Integer invisible;

    private Integer anonymous;

    private Integer usesig;

    private Integer htmlon;

    private Integer bbcodeoff;

    private Integer smileyoff;

    private Integer parseurloff;

    private Integer attachment;

    private Short rate;

    private Integer ratetimes;

    private Integer status;

    private String tags;

    private Integer comment;

    private Integer replycredit;

    private String message;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getFirst() {
        return first;
    }

    public void setFirst(Integer first) {
        this.first = first;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public Integer getAuthorid() {
        return authorid;
    }

    public void setAuthorid(Integer authorid) {
        this.authorid = authorid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public Integer getDateline() {
        return dateline;
    }

    public void setDateline(Integer dateline) {
        this.dateline = dateline;
    }

    public String getUseip() {
        return useip;
    }

    public void setUseip(String useip) {
        this.useip = useip == null ? null : useip.trim();
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getInvisible() {
        return invisible;
    }

    public void setInvisible(Integer invisible) {
        this.invisible = invisible;
    }

    public Integer getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(Integer anonymous) {
        this.anonymous = anonymous;
    }

    public Integer getUsesig() {
        return usesig;
    }

    public void setUsesig(Integer usesig) {
        this.usesig = usesig;
    }

    public Integer getHtmlon() {
        return htmlon;
    }

    public void setHtmlon(Integer htmlon) {
        this.htmlon = htmlon;
    }

    public Integer getBbcodeoff() {
        return bbcodeoff;
    }

    public void setBbcodeoff(Integer bbcodeoff) {
        this.bbcodeoff = bbcodeoff;
    }

    public Integer getSmileyoff() {
        return smileyoff;
    }

    public void setSmileyoff(Integer smileyoff) {
        this.smileyoff = smileyoff;
    }

    public Integer getParseurloff() {
        return parseurloff;
    }

    public void setParseurloff(Integer parseurloff) {
        this.parseurloff = parseurloff;
    }

    public Integer getAttachment() {
        return attachment;
    }

    public void setAttachment(Integer attachment) {
        this.attachment = attachment;
    }

    public Short getRate() {
        return rate;
    }

    public void setRate(Short rate) {
        this.rate = rate;
    }

    public Integer getRatetimes() {
        return ratetimes;
    }

    public void setRatetimes(Integer ratetimes) {
        this.ratetimes = ratetimes;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    public Integer getComment() {
        return comment;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
    }

    public Integer getReplycredit() {
        return replycredit;
    }

    public void setReplycredit(Integer replycredit) {
        this.replycredit = replycredit;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }
}