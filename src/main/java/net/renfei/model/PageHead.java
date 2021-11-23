package net.renfei.model;

import lombok.Data;

import java.util.List;

/**
 * Html页面中的Head头部分
 *
 * @author renfei
 */
@Data
public class PageHead {
    private String title;
    private String description;
    private String keywords;
    private String author;
    private String copyright = "CopyRight RENFEI.NET, All Rights Reserved.";
    private List<String> dnsPrefetch;
    private OGProtocol ogProtocol;
    private String fbAppId;
    private String fbPages;
    private String favicon;
    private String appleTouchIcon;
    private List<String> css;
    private String cssText;
    private String jsText;
}
