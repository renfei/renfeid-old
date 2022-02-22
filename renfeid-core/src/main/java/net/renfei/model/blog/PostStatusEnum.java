package net.renfei.model.blog;

/**
 * 文章或页面状态
 *
 * @author renfei
 */
public enum PostStatusEnum {
    /**
     * 发布
     */
    PUBLISH,
    /**
     * 修订版
     */
    REVISION,
    /**
     * 删除
     */
    DELETED,
    /**
     * 下线
     */
    OFFLINE,
    /**
     * 审核流程中
     */
    REVIEW
}
