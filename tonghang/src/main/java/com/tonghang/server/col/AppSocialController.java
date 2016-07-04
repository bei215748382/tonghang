package com.tonghang.server.col;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
            HttpServletResponse response) throws ServiceException {
        BasicRequestDTO baseRequest = (BasicRequestDTO) request
                .getAttribute("requestDTO");
        checkUserLogin(baseRequest);
        String content = baseRequest.getContent();
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, String> params = (Map<String, String>) JSON.parse(content);
        checkParams(params);
        String files = baseRequest.getFilepaths();
        String txt = params.get("content");
        result.put("code", 200);
        result.put("msg", "success");
        result.put("data", socialService
                .publishSns(baseRequest.getUserId().intValue(), txt, files));

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
        result.put("data", socialService.browseSns(
                baseRequest.getUserId().intValue(), userId, pageNo, pageSize));
        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/recommend", method = { RequestMethod.POST,
            RequestMethod.GET })
    public @ResponseBody Object recommend(HttpServletRequest request,
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
        result.put("data", socialService.recommend(
                baseRequest.getUserId().intValue(), pageNo, pageSize, tradeId));

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
        result.put("data", socialService
                .applyFriend(baseRequest.getUserId().intValue(), userId));

        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/confirmfriend", method = { RequestMethod.POST,
            RequestMethod.PUT })
    public @ResponseBody Object confirmFriend(HttpServletRequest request,
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
        result.put("data", socialService
                .confirmFriend(baseRequest.getUserId().intValue(), userId));

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
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 200);
        result.put("msg", "success");
        result.put("data",
                socialService.friends(baseRequest.getUserId().intValue()));

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
        result.put("data", socialService.browseArticles(
                baseRequest.getUserId().intValue(), tradeId, pageSize, pageNo));

        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/getarticle", method = { RequestMethod.POST,
            RequestMethod.PUT })
    public @ResponseBody Object getArticle(HttpServletRequest request,
            HttpServletResponse response) throws ServiceException {
        BasicRequestDTO baseRequest = (BasicRequestDTO) request
                .getAttribute("requestDTO");
        checkUserLogin(baseRequest);
        String content = baseRequest.getContent();
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, String> params = (Map<String, String>) JSON.parse(content);
        checkParams(params);
        String id = params.get("id");
        result.put("code", 200);
        result.put("msg", "success");
        result.put("data", socialService
                .browseArticle(baseRequest.getUserId().intValue(), id));

        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/hotarticle", method = { RequestMethod.POST,
            RequestMethod.PUT })
    public @ResponseBody Object hotArticle(HttpServletRequest request,
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
        result.put("data", socialService.hotArticle(
                baseRequest.getUserId().intValue(), pageSize, pageNo));

        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/comment", method = { RequestMethod.POST,
            RequestMethod.PUT })
    public @ResponseBody Object comment(HttpServletRequest request,
            HttpServletResponse response) throws ServiceException {
        BasicRequestDTO baseRequest = (BasicRequestDTO) request
                .getAttribute("requestDTO");
        checkUserLogin(baseRequest);
        String content = baseRequest.getContent();
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, String> params = (Map<String, String>) JSON.parse(content);
        checkParams(params);
        String id = params.get("id");
        String comment = params.get("content");
        String commentId = params.get("commentId");
        result.put("code", 200);
        result.put("msg", "success");
        result.put("data",
                socialService.comment(baseRequest.getUserId().intValue(),
                        Integer.valueOf(id), comment, commentId));

        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/like", method = { RequestMethod.POST,
            RequestMethod.PUT })
    public @ResponseBody Object like(HttpServletRequest request,
            HttpServletResponse response) throws ServiceException {
        BasicRequestDTO baseRequest = (BasicRequestDTO) request
                .getAttribute("requestDTO");
        checkUserLogin(baseRequest);
        String content = baseRequest.getContent();
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, String> params = (Map<String, String>) JSON.parse(content);
        checkParams(params);
        String id = params.get("id");
        result.put("code", 200);
        result.put("msg", "success");
        result.put("data", socialService
                .like(baseRequest.getUserId().intValue(), Integer.valueOf(id)));

        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/homepage", method = { RequestMethod.POST,
            RequestMethod.PUT })
    public @ResponseBody Object homepage(HttpServletRequest request,
            HttpServletResponse response) throws ServiceException {
        BasicRequestDTO baseRequest = (BasicRequestDTO) request
                .getAttribute("requestDTO");
        checkUserLogin(baseRequest);
        String content = baseRequest.getContent();
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, String> params = (Map<String, String>) JSON.parse(content);
        checkParams(params);
        String id = params.get("userId");
        result.put("code", 200);
        result.put("msg", "success");
        result.put("data",
                socialService.homepage(baseRequest.getUserId().toString(), id));

        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/friendapply", method = { RequestMethod.POST,
            RequestMethod.PUT })
    public @ResponseBody Object friendApply(HttpServletRequest request,
            HttpServletResponse response) throws ServiceException {
        BasicRequestDTO baseRequest = (BasicRequestDTO) request
                .getAttribute("requestDTO");
        checkUserLogin(baseRequest);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 200);
        result.put("msg", "success");
        result.put("data",
                socialService.friendApply(baseRequest.getUserId().toString()));

        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/favorite", method = { RequestMethod.POST,
            RequestMethod.PUT })
    public @ResponseBody Object favorite(HttpServletRequest request,
            HttpServletResponse response) throws ServiceException {
        BasicRequestDTO baseRequest = (BasicRequestDTO) request
                .getAttribute("requestDTO");
        checkUserLogin(baseRequest);
        String content = baseRequest.getContent();
        Map<String, String> params = (Map<String, String>) JSON.parse(content);
        checkParams(params);
        String type = params.get("type");
        String id = params.get("id");
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 200);
        result.put("msg", "success");
        result.put("data", socialService
                .favorite(baseRequest.getUserId().intValue(), type, id));

        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/favorites", method = { RequestMethod.POST,
            RequestMethod.PUT })
    public @ResponseBody Object favorites(HttpServletRequest request,
            HttpServletResponse response) throws ServiceException {
        BasicRequestDTO baseRequest = (BasicRequestDTO) request
                .getAttribute("requestDTO");
        checkUserLogin(baseRequest);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 200);
        result.put("msg", "success");
        result.put("data",
                socialService.favoritelist(baseRequest.getUserId().intValue()));

        return JSON.toJSONString(result);
    }

}
