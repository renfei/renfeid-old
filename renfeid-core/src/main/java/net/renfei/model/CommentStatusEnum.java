package net.renfei.model;

/**
 * 评论状态
 *
 * @author renfei
 */
public enum CommentStatusEnum {
    /**
     * 任意评论
     */
    OPEN,
    /**
     * 禁止评论
     */
    CLOSED,
    /**
     * 登录后可评论
     */
    SIGNED
}
