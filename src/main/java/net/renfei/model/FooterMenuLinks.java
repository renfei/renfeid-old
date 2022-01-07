package net.renfei.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FooterMenuLinks implements Serializable {
    private String title;
    private List<LinkTree> links;
}
