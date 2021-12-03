package net.renfei.utils;

import net.renfei.ApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.math.BigInteger;

public class StringUtilsTests extends ApplicationTests {
    @Test
    public void isDomain() {
        Assert.isTrue(StringUtils.isDomain("abc-def.renfei.net"), "");
        Assert.isTrue(!StringUtils.isDomain("abc_def.renfei.net"), "");
        Assert.isTrue(!StringUtils.isDomain("abc+def.renfei.net"), "");
        Assert.isTrue(!StringUtils.isDomain("abc=def.renfei.net"), "");
        Assert.isTrue(!StringUtils.isDomain("abc=def_renfei-net"), "");
    }

    @Test
    public void isEmail() {
        Assert.isTrue(StringUtils.isEmail("i@renfei.net"), "");
        Assert.isTrue(StringUtils.isEmail("abc.def@renfei.net"), "");
        Assert.isTrue(!StringUtils.isEmail("abc@def@renfei.net"), "");
        Assert.isTrue(!StringUtils.isEmail("abc+def@ren-fei.net"), "");
        Assert.isTrue(!StringUtils.isEmail("abc=def@ren_fei.net"), "");
        Assert.isTrue(!StringUtils.isEmail("abc=def@renfei.netab"), "");
    }

    @Test
    public void isChinaPhone() {
        Assert.isTrue(StringUtils.isChinaPhone("13001000123"), "");
        Assert.isTrue(StringUtils.isChinaPhone("+8613001000123"), "");
        Assert.isTrue(!StringUtils.isChinaPhone("03138058991"), "");
        Assert.isTrue(!StringUtils.isChinaPhone("0313-8058991"), "");
        Assert.isTrue(!StringUtils.isChinaPhone("+86-13001000123"), "");
        Assert.isTrue(!StringUtils.isChinaPhone("06572"), "");
    }

    @Test
    public void isChinaMobilePhone() {
        Assert.isTrue(StringUtils.isChinaMobilePhone("13901000123"), "");
        Assert.isTrue(StringUtils.isChinaMobilePhone("+8613901000123"), "");
        Assert.isTrue(!StringUtils.isChinaMobilePhone("13001000123"), "");
        Assert.isTrue(!StringUtils.isChinaMobilePhone("18901000123"), "");
        Assert.isTrue(!StringUtils.isChinaMobilePhone("+86-13001000123"), "");
        Assert.isTrue(!StringUtils.isChinaMobilePhone("06572"), "");
    }

    @Test
    public void isChinaUnicomePhone() {
        Assert.isTrue(StringUtils.isChinaUnicomePhone("13001000123"), "");
        Assert.isTrue(StringUtils.isChinaUnicomePhone("+8613001000123"), "");
        Assert.isTrue(!StringUtils.isChinaUnicomePhone("13901000123"), "");
        Assert.isTrue(!StringUtils.isChinaUnicomePhone("18901000123"), "");
        Assert.isTrue(!StringUtils.isChinaUnicomePhone("+86-13001000123"), "");
        Assert.isTrue(!StringUtils.isChinaUnicomePhone("06572"), "");
    }

    @Test
    public void isChinaTelecomPhone() {
        Assert.isTrue(StringUtils.isChinaTelecomPhone("18901000123"), "");
        Assert.isTrue(StringUtils.isChinaTelecomPhone("+8618901000123"), "");
        Assert.isTrue(!StringUtils.isChinaTelecomPhone("13901000123"), "");
        Assert.isTrue(!StringUtils.isChinaTelecomPhone("13901000123"), "");
        Assert.isTrue(!StringUtils.isChinaTelecomPhone("+86-13001000123"), "");
        Assert.isTrue(!StringUtils.isChinaTelecomPhone("06572"), "");
    }

    @Test
    public void isChinaMvnoPhone() {
        Assert.isTrue(StringUtils.isChinaMvnoPhone("17001000123"), "");
        Assert.isTrue(StringUtils.isChinaMvnoPhone("+8617001000123"), "");
        Assert.isTrue(!StringUtils.isChinaMvnoPhone("13901000123"), "");
        Assert.isTrue(!StringUtils.isChinaMvnoPhone("13901000123"), "");
        Assert.isTrue(!StringUtils.isChinaMvnoPhone("+86-13001000123"), "");
        Assert.isTrue(!StringUtils.isChinaMvnoPhone("06572"), "");
    }

    @Test
    public void getRandomNumber() {
        Assert.notNull(StringUtils.getRandomNumber(6), "");
    }

    @Test
    public void getRandomString() {
        Assert.notNull(StringUtils.getRandomString(6), "");
    }

    @Test
    public void encodeBase64() {
        byte[] tmp = StringUtils.decodeBase64("test");
        Assert.isTrue(StringUtils.encodeBase64(tmp).equals("test"), "");
    }

    @Test
    public void encodeUTF8StringBase64() {
        String tmp = StringUtils.encodeUTF8StringBase64("测试");
        Assert.isTrue(StringUtils.decodeUTF8StringBase64(tmp).equals("测试"), "");
    }

    @Test
    public void encodeURL() {
        String tmp = StringUtils.encodeURL("测试");
        Assert.isTrue(StringUtils.decodeURL(tmp).equals("测试"), "");
    }

    @Test
    public void delHtmlTags() {
        Assert.isTrue(StringUtils.delHtmlTags("<p>测试</p>").equals("测试"), "");
    }

    @Test
    public void stringToBigInt(){
        BigInteger tmp = StringUtils.stringToBigInt("114.1.1.1");
        Assert.isTrue(StringUtils.bigIntToString(tmp).equals("114.1.1.1"), "");
    }

    @Test
    public void lineToHump(){
        Assert.isTrue(StringUtils.lineToHump("test_abc").equals("testAbc"), "");
    }

    @Test
    public void humpToLine(){
        Assert.isTrue(StringUtils.humpToLine("testAbc").equals("test_abc"), "");
    }
}
