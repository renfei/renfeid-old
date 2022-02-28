package net.renfei.utils;


import org.junit.Test;

public class PasswordUtilsTests {
    @Test
    public void test() throws PasswordUtils.CannotPerformOperationException {
        String hash = PasswordUtils.createHash("test");
        System.out.println(hash);
        PasswordUtils.verifyPassword("test", hash);
    }
}
