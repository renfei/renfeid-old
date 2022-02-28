package net.renfei.utils;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

public class StringUtilsTests {
    @Test
    public void isDomain() {
        Assert.assertTrue("", StringUtils.isDomain("abc-def.renfei.net"));
        Assert.assertFalse("", StringUtils.isDomain("abc_def.renfei.net"));
        Assert.assertFalse("", StringUtils.isDomain("abc+def.renfei.net"));
        Assert.assertFalse("", StringUtils.isDomain("abc=def.renfei.net"));
        Assert.assertFalse("", StringUtils.isDomain("abc=def_renfei-net"));
    }

    @Test
    public void isEmail() {
        Assert.assertTrue(StringUtils.isEmail("i@renfei.net"));
        Assert.assertTrue(StringUtils.isEmail("abc.def@renfei.net"));
        Assert.assertFalse(StringUtils.isEmail("abc@def@renfei.net"));
        Assert.assertFalse(StringUtils.isEmail("abc+def@ren-fei.net"));
        Assert.assertFalse(StringUtils.isEmail("abc=def@ren_fei.net"));
        Assert.assertFalse(StringUtils.isEmail("abc=def@renfei.netab"));
    }

    @Test
    public void isChinaPhone() {
        Assert.assertTrue(StringUtils.isChinaPhone("13001000123"));
        Assert.assertTrue(StringUtils.isChinaPhone("+8613001000123"));
        Assert.assertFalse(StringUtils.isChinaPhone("03138058991"));
        Assert.assertFalse(StringUtils.isChinaPhone("0313-8058991"));
        Assert.assertFalse(StringUtils.isChinaPhone("+86-13001000123"));
        Assert.assertFalse(StringUtils.isChinaPhone("06572"));
    }

    @Test
    public void isChinaMobilePhone() {
        Assert.assertTrue(StringUtils.isChinaMobilePhone("13901000123"));
        Assert.assertTrue(StringUtils.isChinaMobilePhone("+8613901000123"));
        Assert.assertFalse(StringUtils.isChinaMobilePhone("13001000123"));
        Assert.assertFalse(StringUtils.isChinaMobilePhone("18901000123"));
        Assert.assertFalse(StringUtils.isChinaMobilePhone("+86-13001000123"));
        Assert.assertFalse(StringUtils.isChinaMobilePhone("06572"));
    }

    @Test
    public void isChinaUnicomePhone() {
        Assert.assertTrue(StringUtils.isChinaUnicomePhone("13001000123"));
        Assert.assertTrue(StringUtils.isChinaUnicomePhone("+8613001000123"));
        Assert.assertFalse(StringUtils.isChinaUnicomePhone("13901000123"));
        Assert.assertFalse(StringUtils.isChinaUnicomePhone("18901000123"));
        Assert.assertFalse(StringUtils.isChinaUnicomePhone("+86-13001000123"));
        Assert.assertFalse(StringUtils.isChinaUnicomePhone("06572"));
    }

    @Test
    public void isChinaTelecomPhone() {
        Assert.assertTrue(StringUtils.isChinaTelecomPhone("18901000123"));
        Assert.assertTrue(StringUtils.isChinaTelecomPhone("+8618901000123"));
        Assert.assertFalse(StringUtils.isChinaTelecomPhone("13901000123"));
        Assert.assertFalse(StringUtils.isChinaTelecomPhone("13901000123"));
        Assert.assertFalse(StringUtils.isChinaTelecomPhone("+86-13001000123"));
        Assert.assertFalse(StringUtils.isChinaTelecomPhone("06572"));
    }

    @Test
    public void isChinaMvnoPhone() {
        Assert.assertTrue(StringUtils.isChinaMvnoPhone("17001000123"));
        Assert.assertTrue(StringUtils.isChinaMvnoPhone("+8617001000123"));
        Assert.assertFalse(StringUtils.isChinaMvnoPhone("13901000123"));
        Assert.assertFalse(StringUtils.isChinaMvnoPhone("13901000123"));
        Assert.assertFalse(StringUtils.isChinaMvnoPhone("+86-13001000123"));
        Assert.assertFalse(StringUtils.isChinaMvnoPhone("06572"));
    }

    @Test
    public void getRandomNumber() {
        Assert.assertNotNull(StringUtils.getRandomNumber(6));
    }

    @Test
    public void getRandomString() {
        Assert.assertNotNull(StringUtils.getRandomString(6));
    }

    @Test
    public void encodeBase64() {
        byte[] tmp = StringUtils.decodeBase64("test");
        Assert.assertEquals("test", StringUtils.encodeBase64(tmp));
    }

    @Test
    public void encodeUTF8StringBase64() {
        String tmp = StringUtils.encodeUTF8StringBase64("测试");
        Assert.assertEquals("测试", StringUtils.decodeUTF8StringBase64(tmp));
    }

    @Test
    public void encodeURL() {
        String tmp = StringUtils.encodeURL("测试");
        Assert.assertEquals("测试", StringUtils.decodeURL(tmp));
    }

    @Test
    public void delHtmlTags() {
        Assert.assertEquals("测试", StringUtils.delHtmlTags("<p>测试</p>"));
    }

    @Test
    public void stringToBigInt() {
        BigInteger tmp = StringUtils.stringToBigInt("114.1.1.1");
        Assert.assertEquals("114.1.1.1", StringUtils.bigIntToString(tmp));
    }

    @Test
    public void lineToHump() {
        Assert.assertEquals("testAbc", StringUtils.lineToHump("test_abc"));
    }

    @Test
    public void humpToLine() {
        Assert.assertEquals("test_abc", StringUtils.humpToLine("testAbc"));
    }
}
