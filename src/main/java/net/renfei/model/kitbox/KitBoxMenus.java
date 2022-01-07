package net.renfei.model.kitbox;

import lombok.*;
import lombok.experimental.Tolerate;
import net.renfei.model.LinkTree;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: KitboxMenus</p>
 * <p>Description: 工具箱菜单</p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class KitBoxMenus implements Serializable {
    private String title;
    private String elementId;
    private Boolean isOpen;
    private List<LinkTree> links;
}
