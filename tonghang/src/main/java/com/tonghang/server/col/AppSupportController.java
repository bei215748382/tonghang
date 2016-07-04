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
import com.tonghang.server.service.impl.SMSServiceImpl;
import com.tonghang.server.service.impl.SupportServiceImpl;

@Controller
@RequestMapping(value = "/api/support")
public class AppSupportController extends AppBaseController {

    private static Logger log = LoggerFactory
            .getLogger(AppSupportController.class);

    @Autowired
    private SMSServiceImpl smsService;
    @Autowired
    private SupportServiceImpl supportService;

    @RequestMapping(value = "/getverifycode", method = { RequestMethod.POST,
            RequestMethod.GET })
    public @ResponseBody Object getVerifyCode(HttpServletRequest request,
            HttpServletResponse response) throws ServiceException {
        BasicRequestDTO baseRequest = (BasicRequestDTO) request
                .getAttribute("requestDTO");
        String content = baseRequest.getContent();
        log.info(content);
        Map<String, String> params = (Map<String, String>) JSON.parse(content);
        checkParams(params);
        String mobileNum = params.get("mobile");
        String type = params.get("type");

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 200);
        result.put("msg", "success");

        if ("1".equals(type)) {
            result.put("data", smsService.sendRegCode(mobileNum));
        } else if ("2".equals(type)) {
            result.put("data", smsService.sendRetPasswordCode(mobileNum));
        }
        return result;
    }

    @RequestMapping(value = "/verifycode", method = { RequestMethod.POST,
            RequestMethod.GET })
    public @ResponseBody Object verifyCode(HttpServletRequest request,
            HttpServletResponse response) throws ServiceException {
        BasicRequestDTO baseRequest = (BasicRequestDTO) request
                .getAttribute("requestDTO");
        String content = baseRequest.getContent();
        Map<String, String> params = (Map<String, String>) JSON.parse(content);
        checkParams(params);
        String mobileNum = params.get("mobile");
        String code = params.get("code");
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 200);
        result.put("msg", "success");
        result.put("data", smsService.verifyCode(mobileNum, code));
        return result;
    }

    @RequestMapping(value = "/getrecommendarea", method = { RequestMethod.POST,
            RequestMethod.GET })
    public  @ResponseBody Object getArea(HttpServletRequest request,
            HttpServletResponse response) throws ServiceException {
        BasicRequestDTO baseRequest = (BasicRequestDTO) request
                .getAttribute("requestDTO");
        checkUserLogin(baseRequest);
        String ip = getIp(request);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 200);
        result.put("msg", "success");
        result.put("data",
                supportService.getAreaByIp(ip, baseRequest.getUserId()));
        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/getprovince", method = { RequestMethod.POST,
            RequestMethod.GET })
    public @ResponseBody Object getProvince(HttpServletRequest request,
            HttpServletResponse response) throws ServiceException {
        BasicRequestDTO baseRequest = (BasicRequestDTO) request
                .getAttribute("requestDTO");
        checkUserLogin(baseRequest);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 200);
        result.put("msg", "success");
        result.put("data", supportService.getProvince(baseRequest.getUserId()));
        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/getcity", method = { RequestMethod.POST,
            RequestMethod.GET })
    public @ResponseBody Object getCity(HttpServletRequest request,
            HttpServletResponse response) throws ServiceException {
        BasicRequestDTO baseRequest = (BasicRequestDTO) request
                .getAttribute("requestDTO");
        checkUserLogin(baseRequest);
        String content = baseRequest.getContent();
        Map<String, String> params = (Map<String, String>) JSON.parse(content);
        checkParams(params);
        String provinceId = params.get("provinceId");
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 200);
        result.put("msg", "success");
        result.put("data", supportService.getCityByProvinceId(
                baseRequest.getUserId(), Integer.valueOf(provinceId)));
        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/gettrade", method = { RequestMethod.POST,
            RequestMethod.GET })
    public @ResponseBody Object getTrade(HttpServletRequest request,
            HttpServletResponse response) throws ServiceException {
        BasicRequestDTO baseRequest = (BasicRequestDTO) request
                .getAttribute("requestDTO");
        checkUserLogin(baseRequest);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 200);
        result.put("msg", "success");
        result.put("data", supportService.getTrade(baseRequest.getUserId()));
        return JSON.toJSONString(result);
    }
    
    @RequestMapping(value = "/message", method = { RequestMethod.POST,
            RequestMethod.GET })
    public @ResponseBody Object getMessage(HttpServletRequest request,
            HttpServletResponse response) throws ServiceException {
        BasicRequestDTO baseRequest = (BasicRequestDTO) request
                .getAttribute("requestDTO");
        checkUserLogin(baseRequest);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 200);
        result.put("msg", "success");
        result.put("data", supportService.getMessage(baseRequest.getUserId().intValue()));
        return JSON.toJSONString(result);
    }
    
    @RequestMapping(value = "/readmessage", method = { RequestMethod.POST,
            RequestMethod.DELETE })
    public @ResponseBody Object readMessage(HttpServletRequest request,
            HttpServletResponse response) throws ServiceException {
        BasicRequestDTO baseRequest = (BasicRequestDTO) request
                .getAttribute("requestDTO");
        checkUserLogin(baseRequest);
        Map<String, String> params = (Map<String, String>) JSON.parse(baseRequest.getContent());
        checkParams(params);
        String id = params.get("id");
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 200);
        result.put("msg", "success");
        result.put("data", supportService.readMessage(baseRequest.getUserId().intValue(),id));
        return JSON.toJSONString(result);
    }
    
    @RequestMapping(value = "/banner", method = { RequestMethod.POST,
            RequestMethod.DELETE })
    public @ResponseBody Object banner(HttpServletRequest request,
            HttpServletResponse response) throws ServiceException {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 200);
        result.put("msg", "success");
        result.put("data", supportService.banner());
        return JSON.toJSONString(result);
    }

}
