package com.tonghang.server.col;

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
import com.tonghang.server.exception.ServiceException;
import com.tonghang.server.service.impl.SocialServiceImpl;

@Controller
@RequestMapping(value = "/")
public class ShareController  extends  AppBaseController {

    private static Logger log = LoggerFactory.getLogger(ShareController.class);

    @Autowired
    private SocialServiceImpl socialService;

    @RequestMapping(value = "/share/{id}", method = RequestMethod.GET)
    public void getvideo(HttpServletRequest request,
            HttpServletResponse response, Model model,
            @PathVariable("id") Integer id) {
        this.processShareInfo(request, response, model, id);
    }

    private void processShareInfo(HttpServletRequest request,
            HttpServletResponse response, Model model, Integer id) {
        // 取用户操作系统信息
        // String agent = request.getHeader("User-Agent") == null ? ""
        // : request.getHeader("User-Agent");
        try {
            TCircleDTO bean = socialService.getCircleInfoById(id);
            model.addAttribute("bean", bean);
            // if (UrlFilterUtil.isMobile(agent)) {
            // echoHtml(_request, _response, model, "video_new_m");
            // return;
            // }
            // echoHtml(_request, _response, model, "video");
            forward(request, response, "/WEB_INF/views/share/share.html");
        } catch (ServiceException e1) {
            log.error("get share info error");
            model.addAttribute("errormsg", "找不到相关分享信息");
            forward(request, response, "/WEB_INF/views/share/error.html");
        }

    }

   
}
