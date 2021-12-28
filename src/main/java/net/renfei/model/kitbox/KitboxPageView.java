package net.renfei.model.kitbox;

import net.renfei.model.PageFooter;
import net.renfei.model.PageHead;
import net.renfei.model.PageView;

/**
 * 博客文章页面对象
 *
 * @author renfei
 */
public class KitboxPageView<T> extends PageView<T> {
    public KitboxPageView(T object) {
        super(object);
    }

    public KitboxPageView(PageHead pageHead, PageFooter pageFooter, T object) {
        super(object);
        this.setPageHead(pageHead);
        this.setPageFooter(pageFooter);
    }
}
