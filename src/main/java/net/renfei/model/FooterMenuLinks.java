package net.renfei.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FooterMenuLinks {
    private String title;
    private List<LinkTree> links;
}
