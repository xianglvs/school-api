package org.spring.springboot.util;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * @author ry
 * @Description: 获取客户端IP地址
 */
public class IpUtil {
    private static Log log = LogFactory.getLog(IpUtil.class);
    private static final String UNKNOWN = "unknown";
    private static final String LOCAHOST = "127.0.0.1";
    private static final String LOCAHOSTW = "0:0:0:0:0:0:0:1";
    private static final String DH = ",";
    private static final int IP_LENGTH = 15;

    public static String getIpAddr(HttpServletRequest request) {

        Enumeration<String> headerNames = request.getHeaderNames();
        log.info("当前完整请求头信息:");
        while (headerNames.hasMoreElements()) {
            String nextElement = headerNames.nextElement();
            log.info(nextElement + ":" + request.getHeader(nextElement));
        }
        log.info("请求头信息显示完成");

        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals(LOCAHOST) || ipAddress.equals(LOCAHOSTW)) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    log.error("get local host error", e);
                }
                ipAddress = inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        //"***.***.***.***".length() = 15
        if (ipAddress != null && ipAddress.length() > IP_LENGTH) {
            if (ipAddress.indexOf(DH) > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

}
