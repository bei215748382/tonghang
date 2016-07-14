package com.tonghang.server.common;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.tonghang.server.common.dto.AccessToken;
import com.tonghang.server.common.dto.BasicRequestDTO;
import com.tonghang.server.common.dto.ResponseResult;
import com.tonghang.server.exception.ErrorCode;
import com.tonghang.server.exception.ServiceException;
import com.tonghang.server.util.CodecUtil;
import com.tonghang.server.util.RequestSignatureUtil;

public class AuthFilter implements Filter {
    private static Logger log = LoggerFactory.getLogger(AuthFilter.class);

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {
        // 类路径+方法名
        long startTime = System.currentTimeMillis();
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        log.info("======begin======URI {}  ", httpRequest.getRequestURI());
        String content = null;
        String sign = null;
        String time = null;
        String appKey = null;
        String userId = null;
        String accessToken = null;
        String filepaths = null;
        try {
            log.info(httpRequest.getContentType());
            Map param = new HashMap();
            if (httpRequest.getContentType().contains("multipart/form-data")) {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List items = upload.parseRequest(httpRequest);
                String filePath = "";
                for (Object object : items) {
                    FileItem fileItem = (FileItem) object;
                    if (fileItem.isFormField()) {
                        param.put(fileItem.getFieldName(),
                                fileItem.getString("utf-8"));// 如果你页面编码是utf-8的
                    } else {
                        String path = httpRequest.getSession()
                                .getServletContext().getRealPath("/");
                        path = path + System.currentTimeMillis()
                                + RandomUtils.nextInt(0, 1000)
                                + fileItem.getName().substring(
                                        fileItem.getName().lastIndexOf("."));
                        fileItem.write(new File(path));
                        filePath += path + ",";
                    }
                }
                param.put("files", filePath);
                content = (String) param.get("a");
                sign = (String) param.get("b");
                time = (String) param.get("c");
                appKey = (String) param.get("d");
                userId = (String) param.get("e");
                accessToken = (String) param.get("f");
                filepaths = (String) param.get("files");
            } else {
                content = httpRequest.getParameter("a");
                sign = httpRequest.getParameter("b");
                time = httpRequest.getParameter("c");
                appKey = httpRequest.getParameter("d");
                userId = httpRequest.getParameter("e");
                accessToken = httpRequest.getParameter("f");
            }
            String auth = "";
            String authorization = httpRequest.getHeader("Authorization");
            if (authorization != null) {
                auth = authorization.trim();
            } else if (httpRequest.getParameter("auth") != null) {
                auth = httpRequest.getParameter("auth").trim();
            }

            log.info(
                    "request content = {},sign = {},time = {},appKey = {},userId = {},accessToken = {},files = {}",
                    content, sign, time, appKey, userId, accessToken,
                    filepaths);
            if (StringUtils.isBlank(appKey)) {
                throw new ServiceException(ErrorCode.code6);
            }
            // if (auth == null || auth.equals("")) {
            // log.info("auth = {} is error", auth);
            // throw new ServiceException(ErrorCode.code21);
            // }
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
            // userAndPass = auth.split(" ")[1];
            // if (userAndPass.split("=").length < 2) {
            // RpcResult result = new RpcResult();
            // result.setValue(ResponseResult.error(new ServiceException(
            // ErrorCode.BASE_AUTHENTICATION_ERROR.getCode(),
            // ErrorCode.BASE_AUTHENTICATION_ERROR.getDesc())));
            // log.info("userAndPass = {} is error", userAndPass);
            // return result;
            // }
            if ("TS".equals(appKey)) {
                log.warn("测试渠道进入");
            } else {
                RequestSignatureUtil.checkRequestLegal(content, time, appKey,
                        sign);
            }

            Map<String, String> param1 = (Map<String, String>) JSON
                    .parse(content);
            if (param1 != null) {
                String mobile = param1.get("mobile");
                if (StringUtils.isNotBlank(mobile)) {
                    Pattern p = Pattern.compile(
                            "((13[0-9])|(15[^4,\\D])|(17[0,6-8])|(18[0-1,5-9]))\\d{8}");
                    Matcher m = p.matcher(mobile);
                    if (!m.matches()) {
                        throw new ServiceException(ErrorCode.code120);
                    }
                }
                String password = param1.get("password");
                if (StringUtils.isNotBlank(password)) {
                    Pattern p = Pattern.compile("[A-Za-z0-9_*]{6,20}");
                    Matcher m = p.matcher(password);
                    if (!m.matches()) {
                        throw new ServiceException(ErrorCode.code170);
                    }
                }
            }

            BasicRequestDTO requestDTO = new BasicRequestDTO();
            requestDTO.setAppkey(appKey);
            requestDTO.setAccessToken(accessToken);
            requestDTO.setContent(content);
            requestDTO.setFilepaths(filepaths);
            if (StringUtils.isNotBlank(userId)) {
                requestDTO.setUserId(Long.valueOf(userId));
                checkUserLogin(requestDTO);
            }
            httpRequest.setAttribute("requestDTO", requestDTO);

            chain.doFilter(req, res);
        } catch (Exception e) {
            log.error("======" + "error======msg:" + e.getMessage());
            if (e.getCause() instanceof ServiceException) {
                response.getWriter().println(
                        ResponseResult.error((ServiceException) e.getCause()));
            } else if (e instanceof ServiceException) {
                ServiceException exception = (ServiceException) e;
                if (exception.getCode() == ErrorCode.code50.getCode()
                        || exception.getCode() == ErrorCode.code51.getCode()) {
                    response.setStatus(401);
                }
                response.getWriter()
                        .println(ResponseResult.error(exception));
            } else {
                response.getWriter()
                        .println(ResponseResult.error(
                                new ServiceException(ErrorCode.code10.getCode(),
                                        ErrorCode.code10.getHttpCode(),
                                        ErrorCode.code10.getDesc())));
            }
            return;
        }
        log.info("======end======URI {}  ,cost {}", httpRequest.getRequestURI(),
                System.currentTimeMillis() - startTime);

    }

    public void init(FilterConfig arg0) throws ServletException {

    }

    private void checkUserLogin(BasicRequestDTO requestDTO)
            throws ServiceException {
        String accessToken = requestDTO.getAccessToken();

        accessToken = CodecUtil.decodeCiphertext2Token(accessToken);

        log.debug("accesstoken string is:{}", accessToken);

        AccessToken at = checkAccessToken(accessToken);
        if (at == null || at.getAuid() == null || !String.valueOf(at.getAuid())
                .equals(String.valueOf(requestDTO.getUserId()))) {
            log.debug("accesstoken  is:{}", JSON.toJSON(at));
            throw new ServiceException(ErrorCode.code50.getCode(),
                    ErrorCode.code50.getHttpCode(), "accesstoken is invalid.");
        }
    }

    /**
     * 校验token json字符串是否合法，并得到对应的@AccessToken
     * 
     * @param accesstoken
     * @throws ServiceException
     */
    private static AccessToken checkAccessToken(String accessToken)
            throws ServiceException {
        if (accessToken == null) {
            return null;
        }
        AccessToken at = null;
        try {
            at = JSON.parseObject(accessToken, AccessToken.class);
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.code50.getCode(),
                    ErrorCode.code50.getHttpCode(), "token is illegal");
        }
        if (at.getExpriation() < System.currentTimeMillis()) {
            throw new ServiceException(ErrorCode.code51);
        }
        return at;
    }

    public static void main(String[] args) {
        String mobile = "1";
        if (StringUtils.isNotBlank(mobile)) {
            Pattern p = Pattern.compile("[A-Za-z0-9_*]{6,20}");
            Matcher m = p.matcher(mobile);
            System.out.println(m.matches());
        }

    }

}
