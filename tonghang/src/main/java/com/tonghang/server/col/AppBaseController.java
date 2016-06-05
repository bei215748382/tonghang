package com.tonghang.server.col;

import javax.servlet.http.HttpServletRequest;

public class AppBaseController {

    /**
     * 得到请求IP
     * 
     * @return
     */
    protected String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
