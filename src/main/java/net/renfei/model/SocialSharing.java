package net.renfei.model;

import net.renfei.config.SystemConfig;
import net.renfei.domain.BlogDomain;
import net.renfei.utils.ApplicationContextUtil;

/**
 * 社会化分享
 */
public class SocialSharing {
    private String title;
    private String describes;
    private String url;
    private String pics;
    private String views;
    private String fb_appid = "205704373959112";
    private final SystemConfig SYSTEM_CONFIG;

    {
        SYSTEM_CONFIG = (SystemConfig) ApplicationContextUtil.getBean("systemConfig");
    }

    public SocialSharing(BlogDomain blogDomain) {
        this.title = blogDomain.getPost().getTitle();
        assert SYSTEM_CONFIG != null;
        this.url = SYSTEM_CONFIG.getSiteDomainName() + "/posts/" + blogDomain.getPost().getId();
        this.describes = blogDomain.getPost().getExcerpt();
        this.pics = blogDomain.getPost().getFeaturedImage();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribes() {
        return describes == null ? title : describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFb_appid() {
        return fb_appid;
    }

    public String getPics() {
        if (pics == null) {
            return "https://cdn.renfei.net/Logo/ogimage.png";
        }
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public void setFb_appid(String fb_appid) {
        this.fb_appid = fb_appid;
    }
}
