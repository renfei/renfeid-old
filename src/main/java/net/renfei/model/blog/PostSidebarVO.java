package net.renfei.model.blog;

import net.renfei.model.LinkTree;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: PostSidebarVO</p>
 * <p>Description: 文章侧边栏</p>
 *
 * @author RenFei(i @ renfei.net)
 */
public class PostSidebarVO implements Serializable {
    private List<PostSidebar> postSidebars;

    public static class PostSidebar implements Serializable {
        private String title;
        private List<LinkTree> link;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<LinkTree> getLink() {
            return link;
        }

        public void setLink(List<LinkTree> link) {
            this.link = link;
        }
    }

    public List<PostSidebar> getPostSidebars() {
        return postSidebars;
    }

    public void setPostSidebars(List<PostSidebar> postSidebars) {
        this.postSidebars = postSidebars;
    }
}
