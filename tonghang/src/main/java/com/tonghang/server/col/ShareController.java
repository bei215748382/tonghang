package com.tonghang.server.col;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tonghang.server.common.dto.TCircleDTO;
import com.tonghang.server.entity.TPhone;
import com.tonghang.server.exception.ServiceException;
import com.tonghang.server.service.impl.SocialServiceImpl;

@Controller
@RequestMapping(value = "/share")
public class ShareController  extends  AppBaseController {

    private static Logger log = LoggerFactory.getLogger(ShareController.class);

    @Autowired
    private SocialServiceImpl socialService;

    @RequestMapping(value = "/c/{id}", method = RequestMethod.GET)
    public String getcircle(HttpServletRequest request,
            HttpServletResponse response, Model model,
            @PathVariable("id") Integer id) {
        try {
            TCircleDTO bean = socialService.getCircleInfoById(id);
            model.addAttribute("bean", bean);
            return "share/share";
        } catch (ServiceException e1) {
            log.error("get share info error");
            model.addAttribute("errormsg", "找不到相关分享信息");
            forward(request, response, "share/error");
            return null;
        }
    }
    
    @RequestMapping(value = "/homepage/{id}", method = RequestMethod.GET)
    public String homepage(HttpServletRequest request,
            HttpServletResponse response, Model model,
            @PathVariable("id") String id) {
        try {
            Map<String, Object>  bean = (Map<String, Object>) socialService.homepage(id, id);
            TPhone user = (TPhone) bean.get("user");
            TCircleDTO service = (TCircleDTO) bean.get("service");
            TCircleDTO circle = (TCircleDTO) bean.get("circle");
            model.addAttribute("user", user);
            model.addAttribute("service", service);
            model.addAttribute("circle", circle);
            return "share/homepage";
        } catch (ServiceException e1) {
            log.error("get share info error");
            model.addAttribute("errormsg", "找不到相关分享信息");
            forward(request, response, "share/error");
            return null;
        }
    }



   
}
