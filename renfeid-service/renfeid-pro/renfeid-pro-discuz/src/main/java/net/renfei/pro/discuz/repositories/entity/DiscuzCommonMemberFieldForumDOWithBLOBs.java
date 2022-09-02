package net.renfei.pro.discuz.repositories.entity;

public class DiscuzCommonMemberFieldForumDOWithBLOBs extends DiscuzCommonMemberFieldForumDO {
    private String medals;

    private String sightml;

    private String groupterms;

    private String groups;

    public String getMedals() {
        return medals;
    }

    public void setMedals(String medals) {
        this.medals = medals == null ? null : medals.trim();
    }

    public String getSightml() {
        return sightml;
    }

    public void setSightml(String sightml) {
        this.sightml = sightml == null ? null : sightml.trim();
    }

    public String getGroupterms() {
        return groupterms;
    }

    public void setGroupterms(String groupterms) {
        this.groupterms = groupterms == null ? null : groupterms.trim();
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups == null ? null : groups.trim();
    }
}