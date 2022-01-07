package net.renfei.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class PaginationVO implements Serializable {
    private String link;
    private String page;
    private boolean active;
}
