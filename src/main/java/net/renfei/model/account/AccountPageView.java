package net.renfei.model.account;

import net.renfei.model.PageFooter;
import net.renfei.model.PageHead;
import net.renfei.model.PageView;
import net.renfei.model.discuz.DiscuzInfo;

/**
 * @author renfei
 */
public class AccountPageView<T> extends PageView<T> {
    private DiscuzInfo discuzInfo;

    public AccountPageView(T object) {
        super(object);
    }

    public AccountPageView(PageHead pageHead, PageFooter pageFooter, T object) {
        super(object);
        this.setPageHead(pageHead);
        this.setPageFooter(pageFooter);
    }

    public DiscuzInfo getDiscuzInfo() {
        return discuzInfo;
    }

    public void setDiscuzInfo(DiscuzInfo discuzInfo) {
        this.discuzInfo = discuzInfo;
    }
}
