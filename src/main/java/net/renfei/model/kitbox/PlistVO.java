package net.renfei.model.kitbox;

import java.io.Serializable;

/**
 * <p>Title: PlistVO</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 */
public class PlistVO implements Serializable {
    private String appname;
    private String version;
    private String bundleid;
    private String ipa;
    private String icon;

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBundleid() {
        return bundleid;
    }

    public void setBundleid(String bundleid) {
        this.bundleid = bundleid;
    }

    public String getIpa() {
        return ipa;
    }

    public void setIpa(String ipa) {
        this.ipa = ipa;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
