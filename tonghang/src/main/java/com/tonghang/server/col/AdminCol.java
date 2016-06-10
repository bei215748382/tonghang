package com.tonghang.server.col;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tonghang.server.entity.TCircle;
import com.tonghang.server.entity.TService;
import com.tonghang.server.service.AdminService;
import com.tonghang.server.vo.ServiceVo;
import com.tonghang.server.vo.UserVo;

@Controller
@RequestMapping(value = "/admin")
public class AdminCol {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "index")
    public String index() {
        return "admin/index";
    }

    @RequestMapping(value = "index_info")
    public ModelAndView index_info(HttpServletRequest request,
            HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("admin/ajax/index_info");
        List<TCircle> list = adminService.getCircleUnCheck();
        mav.addObject("dataList", list);
        return mav;
    }

    @RequestMapping(value = "get_circle_checked")
    public ModelAndView get_circle_checked(HttpServletRequest request,
            HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("admin/ajax/index_info");
        List<TCircle> list = adminService.getCircleChecked();
        mav.addObject("dataList", list);
        return mav;
    }

    @RequestMapping(value = "get_service_unchecked")
    public ModelAndView get_service_unchecked(HttpServletRequest request,
            HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("admin/ajax/index_info");
        List<TService> list = adminService.getServiceUnCheck();
        mav.addObject("dataList", list);
        return mav;
    }

    @RequestMapping(value = "get_service_checked")
    public ModelAndView get_service_checked(HttpServletRequest request,
            HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("admin/ajax/index_info");
        List<TService> list = adminService.getServiceChecked();
        mav.addObject("dataList", list);
        return mav;
    }

    @RequestMapping(value = "get_comment_unchecked")
    public ModelAndView get_comment_unchecked(HttpServletRequest request,
            HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("admin/ajax/index_info");
        List<TCircle> list = adminService.getCommentUnCheck();
        mav.addObject("dataList", list);
        return mav;
    }

    @RequestMapping(value = "get_comment_checked")
    public ModelAndView get_comment_checked(HttpServletRequest request,
            HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("admin/ajax/index_info");
        List<TCircle> list = adminService.getCommentUnCheck();
        mav.addObject("dataList", list);
        return mav;
    }

    @RequestMapping(value = "users_info")
    public ModelAndView users_info() {
        ModelAndView mav = new ModelAndView("admin/ajax/users_info");
         List<UserVo> list = adminService.getUsers();
         mav.addObject("users", list);
        return mav;
    }
    
    @RequestMapping(value = "services_info")
    public ModelAndView services_info() {
        ModelAndView mav = new ModelAndView("admin/ajax/services_info");
         List<ServiceVo> list = adminService.getServices();
         mav.addObject("services", list);
        return mav;
    }
    
    @RequestMapping(value = "articles_info")
    public ModelAndView articles_info() {
        ModelAndView mav = new ModelAndView("admin/ajax/articles_info");
         List<TCircle> list = adminService.getArticles();
         mav.addObject("articles", list);
        return mav;
    }
    
    @RequestMapping(value = "article_add")
    public String article_add() {
        return "admin/ajax/article_add";
    }
    
    @RequestMapping(value = "article_add_json")
    public ModelAndView article_add_json(MultipartFile file,TCircle circle) {
        ModelAndView mav = new ModelAndView("admin/ajax/articles_info");
        List<TCircle> list = adminService.getArticles();
        mav.addObject("articles", list);
        return mav;
    }
}
