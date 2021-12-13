package net.renfei.model;

import lombok.Data;

import java.util.List;

/**
 * <p>Title: LinkVO</p>
 * <p>Description: </p>
 *
 * @author RenFei(i@renfei.net)
 */
@Data
public class LinkVO {
    private String icon;
    private String href;
    private String text;
    private String target;
    private String rel;
    private String style;
    private List<LinkVO> subLink;
}
