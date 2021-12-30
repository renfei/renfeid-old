package net.renfei.utils;

import net.renfei.ApplicationTests;
import org.junit.jupiter.api.Test;

public class AESUtilsTests extends ApplicationTests {
    @Test
    public void encryptTest() throws Exception {
        String key = "key";
        String cont = "cont";
        byte[] encrypt = AESUtils.encrypt(cont.getBytes(), key.getBytes());
        assert cont.equals(new String(AESUtils.decrypt(encrypt, key.getBytes())));
    }
}
