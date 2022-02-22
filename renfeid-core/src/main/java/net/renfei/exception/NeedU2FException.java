package net.renfei.exception;

/**
 * 需要两步验证
 *
 * @author renfei
 */
public class NeedU2FException extends Exception {
    public NeedU2FException(String message) {
        super(message);
    }
}
