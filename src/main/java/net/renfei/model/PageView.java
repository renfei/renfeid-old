package net.renfei.model;

import lombok.Data;

/**
 * 页面对象
 *
 * @author renfei
 */
@Data
public abstract class PageView<T> {
    private final PageHead pageHead;
    private final PageFooter pageFooter;
    private final T object;

    protected PageView(T object) {
        // TODO 加载默认的对象
        this.pageHead = null;
        this.pageFooter = null;
        this.object = object;
    }

    protected PageView(PageHead pageHead, PageFooter pageFooter, T object) {
        this.pageHead = pageHead;
        this.pageFooter = pageFooter;
        this.object = object;
    }
}
