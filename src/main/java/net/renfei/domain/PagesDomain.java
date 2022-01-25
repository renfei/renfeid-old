package net.renfei.domain;

import net.renfei.domain.pages.Page;
import net.renfei.domain.user.User;
import net.renfei.exception.NeedPasswordException;
import net.renfei.exception.NotExistException;
import net.renfei.exception.SecretLevelException;
import net.renfei.model.SecretLevelEnum;
import net.renfei.model.blog.PostStatusEnum;
import net.renfei.repositories.SysPagesMapper;
import net.renfei.repositories.model.SysPagesWithBLOBs;
import net.renfei.application.ApplicationContextUtil;
import org.springframework.util.ObjectUtils;

/**
 * @author renfei
 */
public final class PagesDomain {
    private final Page page;
    private final SysPagesMapper pagesMapper;

    {
        pagesMapper = (SysPagesMapper) ApplicationContextUtil.getBean("sysPagesMapper");
    }

    private PagesDomain() {
        page = null;
    }

    public PagesDomain(Long id, User user, String password, boolean isAdmin)
            throws NotExistException, NeedPasswordException, SecretLevelException {
        if (id == null) {
            throw new NotExistException("页面不存在");
        }
        page = initPage(id);
        if (!isAdmin) {
            // 如果不是管理员操作，需要更多限制判断
            // 判断页面状态
            if (!PostStatusEnum.PUBLISH.equals(page.getPageStatus())) {
                throw new NotExistException("文章当前状态不可被阅读。");
            }
            // 判断密码正确性
            if (!ObjectUtils.isEmpty(page.getPagePassword())) {
                if (ObjectUtils.isEmpty(password)) {
                    throw new NeedPasswordException("文章需要密码才能查看。");
                } else {
                    // 判断密码是否正确
                    if (!password.equals(page.getPagePassword())) {
                        throw new NeedPasswordException("文章需要密码才能查看。");
                    }
                }
            }
        }
        // 判断保密等级，管理员也需要判断
        if (user != null) {
            if (user.getSecretLevelEnum().getLevel() < page.getSecretLevel().getLevel()) {
                throw new SecretLevelException("您当前的保密等级无权查看此页面内容。");
            }
        } else if (SecretLevelEnum.UNCLASSIFIED.getLevel() < page.getSecretLevel().getLevel()) {
            throw new SecretLevelException("当前页面内容受到保密系统保护，请先登陆后查看。");
        }
    }

    private Page initPage(Long id) throws NotExistException {
        Page page = convert(pagesMapper.selectByPrimaryKey(id));
        if (page == null || PostStatusEnum.DELETED.equals(page.getPageStatus())) {
            throw new NotExistException("页面不存在");
        }
        return page;
    }

    private Page convert(SysPagesWithBLOBs sysPagesWithBlobs) {
        if (sysPagesWithBlobs == null) {
            return null;
        }
        Page page = new Page();
        page.setId(sysPagesWithBlobs.getId());
        page.setPageAuthor(sysPagesWithBlobs.getPageAuthor());
        page.setPageDate(sysPagesWithBlobs.getPageDate());
        page.setPageStatus(PostStatusEnum.valueOf(sysPagesWithBlobs.getPageStatus()));
        page.setPageViews(sysPagesWithBlobs.getPageViews());
        page.setPagePassword(sysPagesWithBlobs.getPagePassword());
        page.setPageModified(sysPagesWithBlobs.getPageModified());
        page.setPageParent(sysPagesWithBlobs.getPageParent());
        page.setThumbsUp(sysPagesWithBlobs.getThumbsUp());
        page.setThumbsDown(sysPagesWithBlobs.getThumbsDown());
        page.setSecretLevel(SecretLevelEnum.valueOf(sysPagesWithBlobs.getSecretLevel()));
        page.setFeaturedImage(sysPagesWithBlobs.getFeaturedImage());
        page.setPageTitle(sysPagesWithBlobs.getPageTitle());
        page.setPageKeyword(sysPagesWithBlobs.getPageKeyword());
        page.setPageExcerpt(sysPagesWithBlobs.getPageExcerpt());
        page.setPageContent(sysPagesWithBlobs.getPageContent());
        return page;
    }

    public Page getPage() {
        return page;
    }
}
