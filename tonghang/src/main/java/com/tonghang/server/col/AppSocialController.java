package com.tonghang.server.col;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.multi.MultiListUI;

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
import com.tonghang.server.service.impl.SocialServiceImpl;

@Controller
@RequestMapping(value = "/api/social")
public class AppSocialController extends AppBaseController {

    @Autowired
    private SocialServiceImpl socialService;

    @RequestMapping(value = "/publishsns", method = { RequestMethod.POST,
            RequestMethod.PUT })
    public @ResponseBody Object publishSns(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "pictures", required = false) MultipartFile[] pictures)
                    throws ServiceException {
        BasicRequestDTO baseRequest = (BasicRequestDTO) request
                .getAttribute("requestDTO");
        checkUserLogin(baseRequest);
        String content = baseRequest.getContent();
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, String> params = (Map<String, String>) JSON.parse(content);
        checkParams(params);
        String txt = params.get("content");
        result.put("code", 200);
        result.put("msg", "success");
        Map<String, Object> data = socialService
                .publishSns(baseRequest.getUserId().intValue(), txt, pictures);
        result.put("data", data);

        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/browsesns", method = { RequestMethod.POST,
            RequestMethod.GET })
    public @ResponseBody Object browseSns(HttpServletRequest request,
            HttpServletResponse response) throws ServiceException {
        BasicRequestDTO baseRequest = (BasicRequestDTO) request
                .getAttribute("requestDTO");
        checkUserLogin(baseRequest);
        String content = baseRequest.getContent();
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, String> params = (Map<String, String>) JSON.parse(content);
        checkParams(params);
        String pageSize = params.get("pageSize");
        String pageNo = params.get("pageNo");
        String userId = params.get("userId");
        result.put("code", 200);
        result.put("msg", "success");
        Map<String, Object> data = socialService.browseSns(
                baseRequest.getUserId().intValue(), userId, pageNo, pageSize);
        result.put("data", data);

        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/browselikesns", method = { RequestMethod.POST,
            RequestMethod.GET })
    public @ResponseBody Object browseLikeSns(HttpServletRequest request,
            HttpServletResponse response) throws ServiceException {
        BasicRequestDTO baseRequest = (BasicRequestDTO) request
                .getAttribute("requestDTO");
        checkUserLogin(baseRequest);
        String content = baseRequest.getContent();
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, String> params = (Map<String, String>) JSON.parse(content);
        checkParams(params);
        String pageSize = params.get("pageSize");
        String pageNo = params.get("pageNo");
        result.put("code", 200);
        result.put("msg", "success");
        Map<String, Object> data = socialService.browseLikeSns(
                baseRequest.getUserId().intValue(), pageNo, pageSize);
        result.put("data", data);

        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/applyfriend", method = { RequestMethod.POST,
            RequestMethod.PUT })
    public @ResponseBody Object applyFriend(HttpServletRequest request,
            HttpServletResponse response) throws ServiceException {
        BasicRequestDTO baseRequest = (BasicRequestDTO) request
                .getAttribute("requestDTO");
        checkUserLogin(baseRequest);
        String content = baseRequest.getContent();
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, String> params = (Map<String, String>) JSON.parse(content);
        checkParams(params);
        String userId = params.get("userId");
        result.put("code", 200);
        result.put("msg", "success");
        Map<String, Object> data = socialService
                .applyFriend(baseRequest.getUserId().intValue(), userId);
        result.put("data", data);

        return JSON.toJSONString(result);
    }

    /**
     * 我的好友列表
     * 
     * @param request
     * @param response
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/friends", method = { RequestMethod.POST,
            RequestMethod.GET })
    public @ResponseBody Object friends(HttpServletRequest request,
            HttpServletResponse response) throws ServiceException {
        BasicRequestDTO baseRequest = (BasicRequestDTO) request
                .getAttribute("requestDTO");
        checkUserLogin(baseRequest);
        String content = baseRequest.getContent();
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 200);
        result.put("msg", "success");
        Map<String, Object> data = socialService
                .friends(baseRequest.getUserId().intValue());
        result.put("data", data);

        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/browsearticle", method = { RequestMethod.POST,
            RequestMethod.PUT })
    public @ResponseBody Object browseArticle(HttpServletRequest request,
            HttpServletResponse response) throws ServiceException {
        BasicRequestDTO baseRequest = (BasicRequestDTO) request
                .getAttribute("requestDTO");
        checkUserLogin(baseRequest);
        String content = baseRequest.getContent();
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, String> params = (Map<String, String>) JSON.parse(content);
        checkParams(params);
        String pageSize = params.get("pageSize");
        String pageNo = params.get("pageNo");
        String tradeId = params.get("tradeId");
        result.put("code", 200);
        result.put("msg", "success");
        Map<String, Object> data = socialService
                .browseArticle(baseRequest.getUserId().intValue(),tradeId,pageSize,pageNo);
        result.put("data", data);

        return JSON.toJSONString(result);
    }

}
