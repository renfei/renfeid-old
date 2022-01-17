package net.renfei.model;

import java.io.Serializable;
import java.util.List;

/**
 * Html页面中的Head头部分
 *
 * @author renfei
 */
public class PageHead implements Serializable {
    private static final long serialVersionUID = 176048804132630553L;
    private String title;
    private String description;
    private String keywords;
    private String author;
    private String copyright;
    private List<String> dnsPrefetch;
    private OGProtocol ogProtocol;
    private String fbAppId;
    private String fbPages;
    private String favicon;
    private String appleTouchIcon;
    private List<String> css;
    private String cssText;
    private String jsText;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public List<String> getDnsPrefetch() {
        return dnsPrefetch;
    }

    public void setDnsPrefetch(List<String> dnsPrefetch) {
        this.dnsPrefetch = dnsPrefetch;
    }

    public OGProtocol getOgProtocol() {
        return ogProtocol;
    }

    public void setOgProtocol(OGProtocol ogProtocol) {
        this.ogProtocol = ogProtocol;
    }

    public String getFbAppId() {
        return fbAppId;
    }

    public void setFbAppId(String fbAppId) {
        this.fbAppId = fbAppId;
    }

    public String getFbPages() {
        return fbPages;
    }

    public void setFbPages(String fbPages) {
        this.fbPages = fbPages;
    }

    public String getFavicon() {
        return favicon;
    }

    public void setFavicon(String favicon) {
        this.favicon = favicon;
    }

    public String getAppleTouchIcon() {
        return appleTouchIcon;
    }

    public void setAppleTouchIcon(String appleTouchIcon) {
        this.appleTouchIcon = appleTouchIcon;
    }

    public List<String> getCss() {
        return css;
    }

    public void setCss(List<String> css) {
        this.css = css;
    }

    public String getCssText() {
        return cssText;
    }

    public void setCssText(String cssText) {
        this.cssText = cssText;
    }

    public String getJsText() {
        return jsText;
    }

    public void setJsText(String jsText) {
        this.jsText = jsText;
    }
}
