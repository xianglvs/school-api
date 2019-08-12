package org.spring.springboot.util;

import org.springframework.util.DigestUtils;

public class SecurityUtils {
    //盐，用于混交md5
    private static final String slat = "ab12&%*&&%%$$#@";

    public static String getMD5(String str) {
        String base = str +"/"+slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}
