package org.spring.springboot.util;

import java.util.UUID;

/**
 * 生成UUID
 */
public class Uuid {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }


}
