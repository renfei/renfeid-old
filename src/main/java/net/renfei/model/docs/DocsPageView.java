package net.renfei.model.docs;

import net.renfei.model.PageFooter;
import net.renfei.model.PageHead;
import net.renfei.model.PageView;

/**
 * @author renfei
 */
public class DocsPageView<T> extends PageView<T> {
    public DocsPageView(T object) {
        super(object);
    }

    public DocsPageView(PageHead pageHead, PageFooter pageFooter, T object) {
        super(object);
        this.setPageHead(pageHead);
        this.setPageFooter(pageFooter);
    }
}
