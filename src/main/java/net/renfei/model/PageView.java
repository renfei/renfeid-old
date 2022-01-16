package net.renfei.model;

/**
 * 页面对象
 *
 * @author renfei
 */
public abstract class PageView<T> {
    private PageHead pageHead;
    private PageHeader pageHeader;
    private PageFooter pageFooter;
    private T object;

    protected PageView(T object) {
        this.pageHead = null;
        this.pageHeader = null;
        this.pageFooter = null;
        this.object = object;
    }

    public PageHead getPageHead() {
        return pageHead;
    }

    public void setPageHead(PageHead pageHead) {
        this.pageHead = pageHead;
    }

    public PageHeader getPageHeader() {
        return pageHeader;
    }

    public void setPageHeader(PageHeader pageHeader) {
        this.pageHeader = pageHeader;
    }

    public PageFooter getPageFooter() {
        return pageFooter;
    }

    public void setPageFooter(PageFooter pageFooter) {
        this.pageFooter = pageFooter;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
