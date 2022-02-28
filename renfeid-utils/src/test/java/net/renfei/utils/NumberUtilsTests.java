package net.renfei.utils;

import org.junit.Test;

public class NumberUtilsTests {
    @Test
    public void test() {
        assert NumberUtils.parseInt("2", 1) == 2;
        assert NumberUtils.parseFloat("3.14", 1F) == 3.14F;
        assert NumberUtils.parseDouble("3.1415926", 1D) == 3.1415926D;
    }
}
