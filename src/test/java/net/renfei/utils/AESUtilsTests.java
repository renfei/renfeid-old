package net.renfei.utils;

import net.renfei.ApplicationTests;
import org.junit.jupiter.api.Test;

public class AESUtilsTests extends ApplicationTests {
    @Test
    public void encryptTest() throws Exception {
        String key = "1234567890123456";
        String cont = "cont";
        String encrypt = AESUtils.encrypt(cont, key);
        assert cont.equals(AESUtils.decrypt(encrypt, key));
    }
}
