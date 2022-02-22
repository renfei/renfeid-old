package net.renfei.discuz.model;

/**
 * <p>Title: DiscuzInfo</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 */
public class DiscuzInfo {
    private String userGroup;
    private Integer postsCount;
    private Short essenceCount;
    private Short oltime;
    private Integer points;
    private Integer money;
    private Integer prestige;
    private Integer contribution;

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public Integer getPostsCount() {
        return postsCount;
    }

    public void setPostsCount(Integer postsCount) {
        this.postsCount = postsCount;
    }

    public Short getEssenceCount() {
        return essenceCount;
    }

    public void setEssenceCount(Short essenceCount) {
        this.essenceCount = essenceCount;
    }

    public Short getOltime() {
        return oltime;
    }

    public void setOltime(Short oltime) {
        this.oltime = oltime;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getPrestige() {
        return prestige;
    }

    public void setPrestige(Integer prestige) {
        this.prestige = prestige;
    }

    public Integer getContribution() {
        return contribution;
    }

    public void setContribution(Integer contribution) {
        this.contribution = contribution;
    }
}
