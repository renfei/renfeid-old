package net.renfei.model;

import lombok.*;
import net.renfei.config.SystemConfig;
import net.renfei.utils.ApplicationContextUtil;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Html页面中的Head头部分
 *
 * @author renfei
 */
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PageHead implements Serializable {
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
}
