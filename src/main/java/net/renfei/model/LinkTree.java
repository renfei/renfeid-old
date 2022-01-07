package net.renfei.model;

import lombok.*;
import lombok.experimental.Tolerate;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LinkTree implements Serializable {
    private String icon;
    private String href;
    private String text;
    private String target;
    private String rel;
    private String style;
    private List<LinkTree> subLink;
}
