package net.renfei.utils;

import io.sentry.Sentry;
import io.sentry.protocol.SentryId;

/**
 * Sentry 工具类
 *
 * @author renfei
 */
public class SentryUtils {
    private static final boolean ENABLE_SENTRY = true;

    public static SentryId captureException(Throwable throwable) {
        if (ENABLE_SENTRY) {
            return Sentry.captureException(throwable);
        }
        return null;
    }
}
