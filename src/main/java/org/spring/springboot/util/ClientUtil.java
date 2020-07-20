package org.spring.springboot.util;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ClientUtil {

    private static final String UNKNOWN = "unknown";
    private static final String LOCAHOST = "127.0.0.1";
    private static final String LOCAHOSTV6 = "0:0:0:0:0:0:0:1";
    private static final String SEPARATORS = ",";
    private static final int IP_LENGTH = 15;

    /**
     * 获取客户端真实ip
     *
     * @param request
     * @return
     */
    public static String getClientIp(HttpServletRequest request) {

        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (ip.equals(LOCAHOST) || ip.equals(LOCAHOSTV6)) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ip = inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        //"***.***.***.***".length() = 15
        if (ip != null && ip.length() > IP_LENGTH) {
            if (ip.indexOf(SEPARATORS) > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }
}
