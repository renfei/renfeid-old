package net.renfei.exception;

/**
 * 保密等级不足异常
 *
 * @author renfei
 */
public class SecretLevelException extends Exception {
    public SecretLevelException(String message) {
        super(message);
    }
}
