package com.tonghang.server.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tonghang.server.common.dto.BasicRequestDTO;
import com.tonghang.server.common.dto.ResponseResult;
import com.tonghang.server.exception.ErrorCode;
import com.tonghang.server.exception.ServiceException;
import com.tonghang.server.util.RequestSignatureUtil;

public class AuthFilter implements Filter {
    private static Logger log = LoggerFactory.getLogger(AuthFilter.class);

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {
        // 类路径+方法名
        log.info("======" + "begin======");
        try {

            HttpServletRequest httpRequest = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) res;

            String auth = "";
            String authorization = httpRequest.getHeader("Authorization");
            if (authorization != null) {
                auth = authorization.trim();
            } else if (httpRequest.getParameter("auth") != null) {
                auth = httpRequest.getParameter("auth").trim();
            }

            String content = httpRequest.getParameter("a");
            String sign = httpRequest.getParameter("b");
            String time = httpRequest.getParameter("c");
            String appKey = httpRequest.getParameter("d");
            String userId = httpRequest.getParameter("e");
            String accessToken = httpRequest.getParameter("f");
            String callback = httpRequest.getParameter("callback");
            log.debug("URI {}", httpRequest.getRequestURI());
            log.info(
                    "request content = {},sign = {},time = {},appKey = {},userId = {},accessToken = {}",
                    content, sign, time, appKey, userId, accessToken);
            if (auth == null || auth.equals("")) {
                log.info("auth = {} is error", auth);
                response.getWriter()
                        .println(ResponseResult.error(
                                new ServiceException(ErrorCode.code21.getCode(),
                                        ErrorCode.code21.getHttpCode(),
                                        ErrorCode.code21.getDesc())));
                return;
            }
            String userAndPass = "";
            // userAndPass = new String(Base64.getDecoder().decode(auth.split("
            // ")[1]));
            if (auth.length() < 4 || !auth.startsWith("Basic ")) {
                // RpcResult result = new RpcResult();
                // ServiceException e = new ServiceException(
                // ErrorCode.BASE_AUTHENTICATION_ERROR.getCode(),
                // ErrorCode.BASE_AUTHENTICATION_ERROR.getDesc());
                // result.setValue(ResponseResult.error(e));
                // log.info("auth = {} is error", auth);
                // return result;
            }
            userAndPass = auth.split(" ")[1];
            if (userAndPass.split("=").length < 2) {
                // RpcResult result = new RpcResult();
                // result.setValue(ResponseResult.error(new ServiceException(
                // ErrorCode.BASE_AUTHENTICATION_ERROR.getCode(),
                // ErrorCode.BASE_AUTHENTICATION_ERROR.getDesc())));
                // log.info("userAndPass = {} is error", userAndPass);
                // return result;
            }
            try {
                RequestSignatureUtil.checkRequestLegal(content, time, appKey,
                        sign);
            } catch (ServiceException e) {
                response.getWriter().println(ResponseResult.error(e));
                return;
            }
            BasicRequestDTO requestDTO = new BasicRequestDTO();
            requestDTO.setAppkey(appKey);
            requestDTO.setAccessToken(accessToken);
            requestDTO.setContent(content);
            if (userId != null && !userId.equals("")) {
                requestDTO.setUserId(Long.valueOf(userId));
            }
            requestDTO.setAccessToken(accessToken);
            httpRequest.setAttribute("requestDTO", requestDTO);

            chain.doFilter(req, res);

        } catch (Exception e) {
            log.error("======" + "error======msg:" + e);
        }
        log.info("======" + "end======");
    }

    public void init(FilterConfig arg0) throws ServletException {

    }

}
