package com.tonghang.server.col;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.tonghang.server.common.dto.TCircleDTO;
import com.tonghang.server.entity.TPhone;
import com.tonghang.server.exception.ServiceException;
import com.tonghang.server.service.impl.SocialServiceImpl;

@Controller
@RequestMapping(value = "/share")
public class ShareController extends AppBaseController {

    private static Logger log = LoggerFactory.getLogger(ShareController.class);

    @Autowired
    private SocialServiceImpl socialService;

    @RequestMapping(value = "/s", method = RequestMethod.GET)
    public @ResponseBody Object getcircle(HttpServletRequest request,
            HttpServletResponse response, @RequestParam("id") Integer id) {
        try {
            TCircleDTO bean = socialService.getCircleInfoById(id);
            return JSON.toJSONString(bean);
        } catch (ServiceException e1) {
            log.error("get share info error");
            return "share/error";
        }
    }

    @RequestMapping(value = "/h", method = RequestMethod.GET)
    public @ResponseBody Object getHomepageInfo(HttpServletRequest request,
            HttpServletResponse response, Model model,
            @RequestParam("id") String id) {
        try {
            Map<String, Object> bean = (Map<String, Object>) socialService
                    .homepage(id, id);
            return JSON.toJSONString(bean);
        } catch (ServiceException e1) {
            log.error("get share info error");
            return "share/error";
        }
    }

    @RequestMapping(value = "/homepage/{id}", method = RequestMethod.GET)
    public void homepage(HttpServletRequest request,
            HttpServletResponse response, Model model,
            @PathVariable("id") String id)
                    throws ServletException, IOException {
        try {
            Map<String, Object> bean = (Map<String, Object>) socialService
                    .homepage(id, id);
            if (bean != null) {
                TPhone user = (TPhone) bean.get("user");
                if (user != null) {
                    request.getRequestDispatcher("/share/homepage.html")
                            .forward(request, response);
                    return;
                }
            }
            request.getRequestDispatcher("/share/error.html").forward(request,
                    response);
            return;
        } catch (ServiceException e1) {
            log.error("get share info error");
            request.getRequestDispatcher("/share/error.html").forward(request,
                    response);
            return;
        }
    }

    @RequestMapping(value = "/c/{id}", method = RequestMethod.GET)
    public void sharecircle(HttpServletRequest request,
            HttpServletResponse response, @PathVariable("id") Integer id)
                    throws ServletException, IOException {
        try {
            TCircleDTO bean = socialService.getCircleInfoById(id);
            if (bean == null) {
                request.getRequestDispatcher("/share/error.html")
                        .forward(request, response);
            } else {
                if (1==(bean.getType())) {
                    request.getRequestDispatcher("/share/circle.html")
                            .forward(request, response);
                } else if (2==(bean.getType())) {
                    request.getRequestDispatcher("/share/article.html")
                            .forward(request, response);
                } else if (3==(bean.getType())) {
                    request.getRequestDispatcher("/share/service.html")
                            .forward(request, response);
                }
            }

            return;
        } catch (Exception e) {
            log.error("get share info error."+e.getMessage());
            request.getRequestDispatcher("/share/error.html").forward(request,
                    response);
            return;
        }
    }

}
