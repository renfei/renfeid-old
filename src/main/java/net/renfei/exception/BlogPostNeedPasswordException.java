package net.renfei.exception;

/**
 * 博客文章需要密码
 *
 * @author renfei
 */
public class BlogPostNeedPasswordException extends Exception {
    public BlogPostNeedPasswordException(String message) {
        super(message);
    }
}
