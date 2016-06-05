package com.tonghang.server.col;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.tonghang.server.common.dto.BasicRequestDTO;
import com.tonghang.server.exception.ServiceException;
import com.tonghang.server.service.impl.UserService;

@Controller
@RequestMapping(value = "/api/user")
public class AppUserController extends AppBaseController{
    private static Logger log = LoggerFactory
            .getLogger(AppUserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/regist", method = { RequestMethod.POST })
    public @ResponseBody Object registUser(HttpServletRequest request,
            HttpServletResponse response) {
        BasicRequestDTO baseRequest = (BasicRequestDTO) request
                .getAttribute("requestDTO");
        String content = baseRequest.getContent();
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            Map<String, String> params = (Map<String, String>) JSON
                    .parse(content);
            String mobileNum = params.get("mobile");
            String password = params.get("password");
            result.put("code", 200);
            result.put("msg", "success");
            Map<String, Object> data = userService.registUser(mobileNum,
                    password);
            result.put("data", data);
        } catch (ServiceException e) {
            result.put("code", e.getCode());
            result.put("msg", e.getMessage());
            log.error("error when regist." + e.getMessage());
        }

        return (result);
    }

    @RequestMapping(value = "/login", method = { RequestMethod.POST,
            RequestMethod.GET })
    public @ResponseBody Object login(HttpServletRequest request,
            HttpServletResponse response) {
        BasicRequestDTO baseRequest = (BasicRequestDTO) request
                .getAttribute("requestDTO");
        baseRequest.getUserId();
        String content = baseRequest.getContent();
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            Map<String, String> params = (Map<String, String>) JSON
                    .parse(content);
            String mobileNum = params.get("mobile");
            String password = params.get("password");
            result.put("code", 200);
            result.put("msg", "success");
            Map<String, Object> data = userService.login(mobileNum, password);
            result.put("data", data);
        } catch (ServiceException e) {
            result.put("code", e.getCode());
            result.put("msg", e.getMessage());
            log.error("error when login." + e.getMessage());
        }

        return (result);
    }

    @RequestMapping(value = "/password", method = { RequestMethod.POST,
            RequestMethod.PUT })
    public @ResponseBody Object modifyPassword(HttpServletRequest request,
            HttpServletResponse response) {
        BasicRequestDTO baseRequest = (BasicRequestDTO) request
                .getAttribute("requestDTO");
        String content = baseRequest.getContent();
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            Map<String, String> params = (Map<String, String>) JSON
                    .parse(content);
            String mobileNum = params.get("mobile");
            String password = params.get("password");
            String code = params.get("code");
            result.put("code", 200);
            result.put("msg", "success");
            Map<String, Object> data = userService.modifyPassword(mobileNum,
                    password, code);
            result.put("data", data);
        } catch (ServiceException e) {
            result.put("code", e.getCode());
            result.put("msg", e.getMessage());
            log.error("error when login." + e.getMessage());
        }

        return (result);
    }
    
    
    @RequestMapping(value = "/modifyinfo", method = { RequestMethod.POST,
            RequestMethod.PUT })
    public @ResponseBody Object modifyInfo(HttpServletRequest request,
            HttpServletResponse response) {
        BasicRequestDTO baseRequest = (BasicRequestDTO) request
                .getAttribute("requestDTO");
        String content = baseRequest.getContent();
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            Map<String, String> params = (Map<String, String>) JSON
                    .parse(content);
            result.put("code", 200);
            result.put("msg", "success");
            Map<String, Object> data = userService.modifyInfo(baseRequest.getUserId(), params);
            result.put("data", data);
        } catch (ServiceException e) {
            result.put("code", e.getCode());
            result.put("msg", e.getMessage());
            log.error("error when login." + e.getMessage());
        }

        return (result);
    }
    
    @RequestMapping(value = "/getinfo", method = { RequestMethod.POST,
            RequestMethod.PUT })
    public @ResponseBody Object getInfo(HttpServletRequest request,
            HttpServletResponse response) {
        BasicRequestDTO baseRequest = (BasicRequestDTO) request
                .getAttribute("requestDTO");
        String content = baseRequest.getContent();
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            Map<String, String> params = (Map<String, String>) JSON
                    .parse(content);
            String targetUserId  = params.get("userId"); 
            Map<String, Object> data = userService.getInfo(targetUserId);
            result.put("code", 200);
            result.put("msg", "success");
            result.put("data", data);
        } catch (ServiceException e) {
            result.put("code", e.getCode());
            result.put("msg", e.getMessage());
            log.error("error when login." + e.getMessage());
        }

        return (result);
    }

}
