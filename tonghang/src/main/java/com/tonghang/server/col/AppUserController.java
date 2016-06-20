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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.tonghang.server.common.dto.BasicRequestDTO;
import com.tonghang.server.exception.ServiceException;
import com.tonghang.server.service.impl.UserService;

@Controller
@RequestMapping(value = "/api/user")
public class AppUserController extends AppBaseController {
    private static Logger log = LoggerFactory
            .getLogger(AppUserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/regist", method = { RequestMethod.POST })
    public @ResponseBody Object registUser(HttpServletRequest request,
            HttpServletResponse response) throws ServiceException {
        BasicRequestDTO baseRequest = (BasicRequestDTO) request
                .getAttribute("requestDTO");
        String content = baseRequest.getContent();
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, String> params = (Map<String, String>) JSON.parse(content);
        checkParams(params);
        String mobileNum = params.get("mobile");
        String password = params.get("password");
        String longitude = params.get("longitude");
        String latitude = params.get("latitude");
        result.put("code", 200);
        result.put("msg", "success");
        Map<String, Object> data = userService.registUser(mobileNum, password,
                longitude, latitude, baseRequest.getAppkey().substring(0, 1));
        result.put("data", data);

        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/login", method = { RequestMethod.POST,
            RequestMethod.GET })
    public @ResponseBody Object login(HttpServletRequest request,
            HttpServletResponse response) throws ServiceException {
        BasicRequestDTO baseRequest = (BasicRequestDTO) request
                .getAttribute("requestDTO");
        baseRequest.getUserId();
        String content = baseRequest.getContent();
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, String> params = (Map<String, String>) JSON.parse(content);
        checkParams(params);
        String mobileNum = params.get("mobile");
        String password = params.get("password");
        String longitude = params.get("longitude");
        String latitude = params.get("latitude");
        result.put("code", 200);
        result.put("msg", "success");
        Map<String, Object> data = userService.login(mobileNum, password,
                longitude, latitude);
        result.put("data", data);

        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/password", method = { RequestMethod.POST,
            RequestMethod.PUT })
    public @ResponseBody Object modifyPassword(HttpServletRequest request,
            HttpServletResponse response) throws ServiceException {
        BasicRequestDTO baseRequest = (BasicRequestDTO) request
                .getAttribute("requestDTO");
        String content = baseRequest.getContent();
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, String> params = (Map<String, String>) JSON.parse(content);
        checkParams(params);
        String mobileNum = params.get("mobile");
        String password = params.get("password");
        String code = params.get("code");
        result.put("code", 200);
        result.put("msg", "success");
        Map<String, Object> data = userService.modifyPassword(mobileNum,
                password, code);
        result.put("data", data);

        return JSON.toJSONString(result);

    }

    @RequestMapping(value = "/modifyinfo", method = { RequestMethod.POST,
            RequestMethod.PUT })
    public @ResponseBody Object modifyInfo(HttpServletRequest request,
            HttpServletResponse response) throws ServiceException {
        BasicRequestDTO baseRequest = (BasicRequestDTO) request
                .getAttribute("requestDTO");
        checkUserLogin(baseRequest);
        String content = baseRequest.getContent();
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, String> params = (Map<String, String>) JSON.parse(content);
        result.put("code", 200);
        result.put("msg", "success");
        result.put("data",
                userService.modifyInfo(baseRequest.getUserId(), params));

        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/getinfo", method = { RequestMethod.POST,
            RequestMethod.PUT })
    public @ResponseBody Object getInfo(HttpServletRequest request,
            HttpServletResponse response) throws ServiceException {
        BasicRequestDTO baseRequest = (BasicRequestDTO) request
                .getAttribute("requestDTO");
        checkUserLogin(baseRequest);
        String content = baseRequest.getContent();
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, String> params = (Map<String, String>) JSON.parse(content);
        checkParams(params);
        String targetUserId = params.get("userId");
        result.put("code", 200);
        result.put("msg", "success");
        result.put("data", userService.getInfo(targetUserId));

        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/addservice", method = { RequestMethod.POST,
            RequestMethod.PUT })
    public @ResponseBody Object addService(HttpServletRequest request,
            HttpServletResponse response) throws ServiceException {
        BasicRequestDTO baseRequest = (BasicRequestDTO) request
                .getAttribute("requestDTO");
        checkUserLogin(baseRequest);
        String content = baseRequest.getContent();
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, String> params = (Map<String, String>) JSON.parse(content);
        checkParams(params);
        String name = params.get("name");
        String describe = params.get("describe");
        String files = baseRequest.getFilepaths();
        result.put("code", 200);
        result.put("msg", "success");
        result.put("data", userService.addService(
                baseRequest.getUserId().intValue(), name, describe, files));

        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/getservice", method = { RequestMethod.POST,
            RequestMethod.GET })
    public @ResponseBody Object getService(HttpServletRequest request,
            HttpServletResponse response) throws ServiceException {
        BasicRequestDTO baseRequest = (BasicRequestDTO) request
                .getAttribute("requestDTO");
        checkUserLogin(baseRequest);
        String content = baseRequest.getContent();
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, String> params = (Map<String, String>) JSON.parse(content);
        checkParams(params);
        String targetUserId = params.get("userId");
        result.put("code", 200);
        result.put("msg", "success");
        result.put("data", userService
                .getService(baseRequest.getUserId().intValue(), targetUserId));

        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/gettrack", method = { RequestMethod.POST,
            RequestMethod.GET })
    public @ResponseBody Object geTtrack(HttpServletRequest request,
            HttpServletResponse response) throws ServiceException {
        BasicRequestDTO baseRequest = (BasicRequestDTO) request
                .getAttribute("requestDTO");
        checkUserLogin(baseRequest);
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> data = userService
                .getTrack(baseRequest.getUserId().intValue());
        result.put("code", 200);
        result.put("msg", "success");
        result.put("data", data);

        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/modifyicon", method = { RequestMethod.POST,
            RequestMethod.GET })
    public @ResponseBody Object modifyIcon(HttpServletRequest request,
            HttpServletResponse response) throws ServiceException {
        BasicRequestDTO baseRequest = (BasicRequestDTO) request
                .getAttribute("requestDTO");
        checkUserLogin(baseRequest);
        Map<String, Object> result = new HashMap<String, Object>();
        String files = baseRequest.getFilepaths();
        result.put("code", 200);
        result.put("msg", "success");
        result.put("data", userService
                .modifyIcon(baseRequest.getUserId().intValue(), files));

        return JSON.toJSONString(result);
    }

}
