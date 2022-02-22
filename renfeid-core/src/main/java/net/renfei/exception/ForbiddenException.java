package net.renfei.exception;

/**
 * 权限不足异常
 *
 * @author renfei
 */
public class ForbiddenException extends Exception {
    public ForbiddenException(String message) {
        super(message);
    }
}
