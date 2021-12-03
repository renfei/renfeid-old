package net.renfei.utils;

import net.renfei.ApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class PasswordUtilsTests extends ApplicationTests {
    @Test
    public void test() throws PasswordUtils.CannotPerformOperationException {
        String hash = PasswordUtils.createHash("test");
        System.out.println(hash);
        Assert.isTrue(PasswordUtils.verifyPassword("test", hash), "");
    }
}
