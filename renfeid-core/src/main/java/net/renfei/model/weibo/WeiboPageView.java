package net.renfei.model.weibo;

import net.renfei.model.PageFooter;
import net.renfei.model.PageHead;
import net.renfei.model.PageView;

public class WeiboPageView<T> extends PageView<T> {
    public WeiboPageView(T object) {
        super(object);
    }

    public WeiboPageView(PageHead pageHead, PageFooter pageFooter, T object) {
        super(object);
        this.setPageHead(pageHead);
        this.setPageFooter(pageFooter);
    }
}
