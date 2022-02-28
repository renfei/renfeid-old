package net.renfei.utils;

import org.junit.Test;

public class AESUtilsTests {
    @Test
    public void encryptTest() throws Exception {
        String key = "1234567890123456";
        String cont = "cont";
        String encrypt = AESUtils.encrypt(cont, key);
        assert cont.equals(AESUtils.decrypt(encrypt, key));
    }
}
