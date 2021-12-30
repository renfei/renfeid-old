package net.renfei.utils;

import net.renfei.ApplicationTests;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CommonUtilTests extends ApplicationTests {
    @Test
    public void getOneTest() {
        Object obj = new Object();
        List<Object> list = new ArrayList<>();
        list.add(obj);
        assert CommonUtil.getOne(list) == obj;
        assert ListUtils.getOne(list) == obj;
    }
}
