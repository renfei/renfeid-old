package net.renfei.utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CommonUtilTests {
    @Test
    public void getOneTest() {
        Object obj = new Object();
        List<Object> list = new ArrayList<>();
        list.add(obj);
        assert CommonUtil.getOne(list) == obj;
        assert ListUtils.getOne(list) == obj;
    }
}
