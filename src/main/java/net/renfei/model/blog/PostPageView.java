package net.renfei.model.blog;

import net.renfei.domain.BlogDomain;
import net.renfei.model.PageFooter;
import net.renfei.model.PageHead;
import net.renfei.model.PageView;

/**
 * 博客文章页面对象
 *
 * @author renfei
 */
public class PostPageView extends PageView<BlogDomain> {
    public PostPageView(BlogDomain object) {
        super(object);
    }

    public PostPageView(PageHead pageHead, PageFooter pageFooter, BlogDomain object) {
        super(pageHead, pageFooter, object);
    }
}
