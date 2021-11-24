package net.renfei.model;

import lombok.Data;

/**
 * 页面对象
 *
 * @author renfei
 */
@Data
public abstract class PageView<T> {
    private PageHead pageHead;
    private PageFooter pageFooter;
    private T object;

    protected PageView(T object) {
        this.pageHead = null;
        this.pageFooter = null;
        this.object = object;
    }
}
