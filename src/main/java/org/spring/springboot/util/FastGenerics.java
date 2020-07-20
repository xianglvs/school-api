package org.spring.springboot.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class FastGenerics {


    /**
     * 取得泛型类型
     *
     * @param clazz 泛型所在类
     * @param index 泛型所在参数序列以0开始
     * @return
     */
    public static Class<?> getActualTypeArgument(Class<?> clazz, int index) {
        Class<?> entitiClass = null;
        Type genericSuperclass = clazz.getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass)
                    .getActualTypeArguments();
            if (actualTypeArguments != null && actualTypeArguments.length > 0) {
                entitiClass = (Class<?>) actualTypeArguments[index];
            }
        }

        return entitiClass;
    }
}
