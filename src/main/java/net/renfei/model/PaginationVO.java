package net.renfei.model;

import lombok.Data;

@Data
public class PaginationVO {
    private String link;
    private String page;
    private boolean active;
}
