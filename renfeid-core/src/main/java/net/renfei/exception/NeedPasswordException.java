package net.renfei.exception;

/**
 * 博客文章需要密码
 *
 * @author renfei
 */
public class NeedPasswordException extends Exception {
    public NeedPasswordException(String message) {
        super(message);
    }
}
