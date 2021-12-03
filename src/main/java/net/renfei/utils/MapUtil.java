package net.renfei.utils;

import java.util.*;

public class MapUtil {
    /**
     * 将List中的Key转换为小写
     *
     * @param list 返回新对象
     * @return
     */
    public static List<Map<String, Object>> convertKeyList2LowerCase(List<Map<String, Object>> list) {
        if (null == list) {
            return null;
        }
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Map<String, Object> map : list) {
            Map<String, Object> result = convertKey2LowerCase(map);
            if (null != result) {
                resultList.add(result);
            }
        }
        return resultList;
    }

    /**
     * 转换单个map,将key转换为小写.
     *
     * @param map 返回新对象
     * @return
     */
    public static Map<String, Object> convertKey2LowerCase(Map<String, Object> map) {
        if (null == map) {
            return null;
        }
        Map<String, Object> result = new HashMap<>();
        Set<String> keys = map.keySet();
        for (String key : keys) {
            Object value = map.get(key);
            if (null == key) {
                continue;
            }
            String keyL = key.toLowerCase();
            result.put(keyL, value);
        }
        return result;
    }
}
