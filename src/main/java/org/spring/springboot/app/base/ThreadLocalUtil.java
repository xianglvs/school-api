package org.spring.springboot.app.base;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalUtil {
    /**
     * 保存变量的ThreadLocal，保持在同一线程中同步数据.
     */
    private static final ThreadLocal<Map<String, Object>> sThreadLocal = new ThreadLocal<Map<String, Object>>();

    /**
     * 获得线程中保存的属性.
     *
     * @param key 属性名称
     * @return 属性值
     */
    public static Object get(String key) {
        Map<String, Object> map = sThreadLocal.get();
        if(map == null){
            return null;
        }
        return map.get(key);
    }

    /**
     * 判断线程中是否有保存某key
     *
     * @param key 属性名称
     * @return 属性值
     */
    public static boolean containsKey(String key) {
        Map<String, Object> map = sThreadLocal.get();
        if(map == null){
            return false;
        }
        return map.containsKey(key);
    }

    /**
     * 获得线程中保存的属性，使用指定类型进行转型.
     *
     * @param key   属性名称
     * @param clazz 类型
     * @param <T>   自动转型
     * @return 属性值
     */
    public static <T> T get(String key, Class<T> clazz) {
        Object o = get(key);
        if (o == null) {
            return null;
        }
        return (T) o;
    }

    /**
     * 设置制定属性名的值.
     *
     * @param key   属性名称
     * @param value 属性值
     */
    public static void put(String key, Object value) {
        Map<String, Object> map = sThreadLocal.get();
        if (map == null) {
            map = new HashMap<String, Object>();
            sThreadLocal.set(map);
        }
        map.put(key, value);
    }

    /**
     * 移除属性名的值.
     *
     * @param key   属性名称
     */
    public static void remove(String key) {
        Map<String, Object> map = sThreadLocal.get();
        if (map != null) {
            map.remove(key);
        }
    }

    /**
     * 清空所有值.
     *
     */
    public static void clear() {
        Map<String, Object> map = sThreadLocal.get();
        if (map != null) {
            map.clear();
        }
    }

}