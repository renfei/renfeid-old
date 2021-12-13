package net.renfei.model.blog;

import net.renfei.model.PageFooter;
import net.renfei.model.PageHead;
import net.renfei.model.PageView;

/**
 * 博客文章页面对象
 *
 * @author renfei
 */
public class PostPageView<T> extends PageView<T> {
    public PostPageView(T object) {
        super(object);
    }

    public PostPageView(PageHead pageHead, PageFooter pageFooter, T object) {
        super(object);
        this.setPageHead(pageHead);
        this.setPageFooter(pageFooter);
    }
}
