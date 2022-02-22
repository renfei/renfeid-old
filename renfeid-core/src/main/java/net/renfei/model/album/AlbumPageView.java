package net.renfei.model.album;

import net.renfei.model.PageFooter;
import net.renfei.model.PageHead;
import net.renfei.model.PageView;

/**
 * 相册页面对象
 *
 * @author renfei
 */
public class AlbumPageView<T> extends PageView<T> {
    public AlbumPageView(T object) {
        super(object);
    }

    public AlbumPageView(PageHead pageHead, PageFooter pageFooter, T object) {
        super(object);
        this.setPageHead(pageHead);
        this.setPageFooter(pageFooter);
    }
}
