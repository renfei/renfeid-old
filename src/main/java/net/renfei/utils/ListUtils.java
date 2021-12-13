package net.renfei.utils;

import java.util.List;

public class ListUtils {
    public static <T> T getOne(List<T> list) {
        if (list == null || list.size() < 1) {
            return null;
        }
        return list.get(0);
    }
}
