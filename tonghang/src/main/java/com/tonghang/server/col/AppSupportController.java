package com.tonghang.server.col;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public String getVerifyCode(HttpServletRequest request,
            HttpServletResponse response) throws ServiceException {
        BasicRequestDTO baseRequest = (BasicRequestDTO) request
                .getAttribute("requestDTO");
        String content = baseRequest.getContent();
        Map<String, String> params = (Map<String, String>) JSON.parse(content);
        checkParams(params);
        String mobileNum = params.get("mobile");
        String type = params.get("type");
        if ("1".equals(type)) {
            smsService.sendRegCode(params.get(mobileNum));
        } else if ("2".equals(type)) {
            smsService.sendRetPasswordCode(mobileNum);
        }
        return "succuess";
    }

    @RequestMapping(value = "/verifycode", method = { RequestMethod.POST,
            RequestMethod.GET })
    public String verifyCode(HttpServletRequest request,
            HttpServletResponse response) throws ServiceException {
        BasicRequestDTO baseRequest = (BasicRequestDTO) request
                .getAttribute("requestDTO");
        String content = baseRequest.getContent();
        Map<String, String> params = (Map<String, String>) JSON.parse(content);
        checkParams(params);
        String mobileNum = params.get("mobile");
        String code = params.get("code");
        smsService.verifyCode(mobileNum, code);
        return "succuess";
    }

    @RequestMapping(value = "/getrecommendarea", method = { RequestMethod.POST,
            RequestMethod.GET })
    public String getArea(HttpServletRequest request,
            HttpServletResponse response) throws ServiceException {
        BasicRequestDTO baseRequest = (BasicRequestDTO) request
                .getAttribute("requestDTO");
        String ip = getIp(request);

        return JSON.toJSONString(
                supportService.getAreaByIp(ip, baseRequest.getUserId()));
    }

}
