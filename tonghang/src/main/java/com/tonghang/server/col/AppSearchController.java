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
import com.tonghang.server.service.impl.SearchServiceImpl;

@Controller
@RequestMapping(value = "/api/search")
public class AppSearchController extends  AppBaseController{
    
    @Autowired
    private SearchServiceImpl  searchService;
    

    @RequestMapping(value = "/searchuser", method = { RequestMethod.POST,
            RequestMethod.GET })
    public @ResponseBody Object searchUser(HttpServletRequest request,
            HttpServletResponse response) throws ServiceException {
        BasicRequestDTO baseRequest = (BasicRequestDTO) request
                .getAttribute("requestDTO");
        checkUserLogin(baseRequest);
        String content = baseRequest.getContent();
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, String> params = (Map<String, String>) JSON.parse(content);
        String key = params.get("key");
        result.put("code", 200);
        result.put("msg", "success");
        result.put("data", searchService.searchUser(key));

        return JSON.toJSONString(result);
    }
    
}
