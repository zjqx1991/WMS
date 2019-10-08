package com.revanwang.utils;

import java.util.HashMap;
import java.util.Map;

public class RevanMapUtils {

    /**
     * 获取Map对象
     * @param key       键值
     * @param value     value
     * @return
     */
    public static Map<String, Object> revan_createMapObject(String key, Object value) {
        Map<String, Object> map = new HashMap();
        map.put(key, value);
        return map;
    }


    /**
     * 获取Map对象 key
     * @param map
     * @return
     */
    public static Object revan_getMapKey(Map<String, Object> map) {
        for (Map.Entry entry : map.entrySet()) {
            return entry.getKey();
        }
        return null;
    }


    /**
     * 获取 Map 对象 value
     * @param map
     * @return
     */
    public static Object revan_getMapValue(Map<String, Object> map) {
        for (Map.Entry entry : map.entrySet()) {
            return entry.getValue();
        }
        return null;
    }


}
