package net.renfei.model.blog;

import lombok.*;
import net.renfei.model.LinkTree;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: PostSidebarVO</p>
 * <p>Description: 文章侧边栏</p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostSidebarVO implements Serializable {
    private List<PostSidebar> postSidebars;

    @Data
    @Builder
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class PostSidebar implements Serializable {
        private String title;
        private List<LinkTree> link;
    }
}
