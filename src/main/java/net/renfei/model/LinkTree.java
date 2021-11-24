package net.renfei.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LinkTree {
    private String icon;
    private String href;
    private String text;
    private String target;
    private String rel;
    private String style;
    private List<LinkTree> subLink;
}
