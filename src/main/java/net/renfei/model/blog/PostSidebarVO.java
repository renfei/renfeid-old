package net.renfei.model.blog;

import lombok.Builder;
import lombok.Data;
import net.renfei.model.LinkTree;

import java.util.List;

/**
 * <p>Title: PostSidebarVO</p>
 * <p>Description: 文章侧边栏</p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Data
@Builder
public class PostSidebarVO {
    private List<PostSidebar> postSidebars;

    @Data
    @Builder
    public static class PostSidebar {
        private String title;
        private List<LinkTree> link;
    }
}
