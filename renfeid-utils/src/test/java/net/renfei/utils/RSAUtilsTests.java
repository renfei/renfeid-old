package net.renfei.utils;

import org.junit.Test;

import java.util.Map;

/**
 * @author renfei
 */
public class RSAUtilsTests {
    @Test
    public void test() throws Exception {
        String msg = "tester";
        //0表示公钥
        //1表示私钥
        Map<Integer, String> data = RSAUtils.genKeyPair(1024);
        //RSA公钥加密
        String encrypt = RSAUtils.encrypt(msg, data.get(0));
        assert msg.equals(RSAUtils.decrypt(encrypt, data.get(1)));
        //RSA私钥加密
        encrypt = RSAUtils.encryptByPrivateKey(msg, data.get(1));
        assert msg.equals(RSAUtils.decryptByPublicKey(encrypt, data.get(0)));
    }
}
