package com.tonghang.server.col;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.tonghang.server.common.dto.BasicRequestDTO;
import com.tonghang.server.exception.ErrorCode;
import com.tonghang.server.exception.ServiceException;

public class AppBaseController {

    
    public static final String ENCODER_UTF8        = "utf-8";
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

    protected void checkParams(Map<String, String> params)
            throws ServiceException {

        if (params == null || params.size() <= 0) {
            throw new ServiceException(ErrorCode.code20.getCode(),
                    ErrorCode.code20.getHttpCode(), "params can not be null ");
        }
        for (String key : params.keySet()) {
            String param = params.get(key);
            if (StringUtils.isEmpty(param)) {
                throw new ServiceException(ErrorCode.code20.getCode(),
                        ErrorCode.code20.getHttpCode(),
                        "param[" + key + "] is needed.");
            }
        }
    }

    protected void checkUserLogin(BasicRequestDTO baseRequest)
            throws ServiceException {

        if (baseRequest == null || baseRequest.getUserId() == null
                || 0l == (baseRequest.getUserId())) {
            throw new ServiceException(ErrorCode.code117.getCode(),
                    ErrorCode.code117.getHttpCode(), "params can not be null ");
        }
    }
    
    protected void html(HttpServletResponse _response) {
        _response.setContentType("text/html");
        _response.setCharacterEncoding(ENCODER_UTF8);
    }

    
    /**
     * 直接跳转
     * 
     * @param _request
     * @param _response
     * @param url
     */
    protected void forward(HttpServletRequest request,
            HttpServletResponse response, String url) {
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

}
