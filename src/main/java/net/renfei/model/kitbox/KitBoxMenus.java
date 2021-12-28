package net.renfei.model.kitbox;

import lombok.Builder;
import lombok.Data;
import net.renfei.model.LinkTree;

import java.util.List;

/**
 * <p>Title: KitboxMenus</p>
 * <p>Description: 工具箱菜单</p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Data
@Builder
public class KitBoxMenus {
    private String title;
    private String elementId;
    private Boolean isOpen;
    private List<LinkTree> links;
}
