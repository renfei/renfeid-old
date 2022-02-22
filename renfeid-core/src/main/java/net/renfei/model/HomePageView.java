package net.renfei.model;

public class HomePageView<T> extends PageView<T> {
    public HomePageView(T object) {
        super(object);
    }

    public HomePageView(PageHead pageHead, PageFooter pageFooter, T object) {
        super(object);
        this.setPageHead(pageHead);
        this.setPageFooter(pageFooter);
    }
}
