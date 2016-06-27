package com.tonghang.server.col;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tonghang.server.entity.TAdminUser;
import com.tonghang.server.entity.TCircle;
import com.tonghang.server.entity.TCity;
import com.tonghang.server.entity.TPhone;
import com.tonghang.server.entity.TService;
import com.tonghang.server.entity.TTrade;
import com.tonghang.server.service.AdminService;
import com.tonghang.server.util.FileUtil;
import com.tonghang.server.vo.ArticleInfo;
import com.tonghang.server.vo.ArticlesVo;
import com.tonghang.server.vo.CheckCommentVo;
import com.tonghang.server.vo.CircleVo;
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
        List<CircleVo> list = adminService.getCircleUnCheck();
        mav.addObject("dataList", list);
        return mav;
    }

    @RequestMapping(value = "getUserById")
    @ResponseBody
    public TPhone getUserById(Integer id) {
        return adminService.getUserById(id);
    }

    @RequestMapping(value = "checkCircle")
    @ResponseBody
    public Boolean checkCircle(Integer id) {
        return adminService.checkCircle(id);
    }

    @RequestMapping(value = "get_circle_checked")
    public ModelAndView get_circle_checked(HttpServletRequest request,
            HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("admin/ajax/index_info");
        List<CircleVo> list = adminService.getCircleChecked();
        mav.addObject("dataList", list);
        return mav;
    }

    @RequestMapping(value = "checkService")
    @ResponseBody
    public Boolean checkService(Integer id) {
        return adminService.checkService(id);
    }

    
    @RequestMapping(value = "get_service_unchecked")
    public ModelAndView get_service_unchecked(HttpServletRequest request,
            HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("admin/ajax/index_service_info");
        List<ServiceVo> list = adminService.getUncheckedServices();
        mav.addObject("dataList", list);
        return mav;
    }

    @RequestMapping(value = "get_service_checked")
    public ModelAndView get_service_checked(HttpServletRequest request,
            HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("admin/ajax/index_service_info");
        List<ServiceVo> list = adminService.getCheckedServices();
        mav.addObject("dataList", list);
        return mav;
    }
    
    @RequestMapping(value = "checkComment")
    @ResponseBody
    public Boolean checkComment(Integer id) {
        return adminService.checkComment(id);
    }

    @RequestMapping(value = "get_comment_unchecked")
    public ModelAndView get_comment_unchecked(HttpServletRequest request,
            HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("admin/ajax/index_comment_info");
        List<CheckCommentVo> list = adminService.getCommentUnCheck();
        mav.addObject("dataList", list);
        return mav;
    }

    @RequestMapping(value = "get_comment_checked")
    public ModelAndView get_comment_checked(HttpServletRequest request,
            HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("admin/ajax/index_comment_info");
        List<CheckCommentVo> list = adminService.getCommentChecked();
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
        List<ArticlesVo> list = adminService.getArticles();
        mav.addObject("articles", list);
        return mav;
    }

    @RequestMapping(value = "article_add")
    public ModelAndView article_add() {
        ModelAndView mav = new ModelAndView("admin/ajax/article_add");
        List<TCity> cities = adminService.getCities();
        List<TTrade> trades = adminService.getTrades();
        mav.addObject("cities", cities);
        mav.addObject("trades", trades);
        return mav;
    }

    @RequestMapping(value = "article_add_json")
    public void article_add_json(MultipartFile file, TCircle circle,
            HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        if (file != null && file.getOriginalFilename() != "") {
            String pic = FileUtil.savePic(request, file);
            circle.setPic(pic);
        }
        if (circle.getChecked() == 1) {
            circle.setDatetime(new Date());// 如果文章设置为发送，那么发布时间就设定
        }
        adminService.addArticle(circle);
        response.sendRedirect("index#articles_info");
    }

    @RequestMapping(value = "article_edit")
    public ModelAndView article_edit(Integer id) {
        ModelAndView mav = new ModelAndView("admin/ajax/article_edit");
        ArticlesVo article = adminService.getArticle(id);
        List<TCity> cities = adminService.getCities();
        List<TTrade> trades = adminService.getTrades();
        mav.addObject("cities", cities);
        mav.addObject("trades", trades);
        mav.addObject("article", article);
        return mav;
    }

    @RequestMapping(value = "article_info")
    public ModelAndView article_info(Integer id) {
        ModelAndView mav = new ModelAndView("admin/ajax/article_info");
        ArticleInfo article = adminService.getArticleInfo(id);
        mav.addObject("article", article);
        return mav;
    }

    @RequestMapping(value = "user_increase_info")
    public ModelAndView user_increase_info() {
        ModelAndView mav = new ModelAndView("admin/ajax/user_increase_info");
        return mav;
    }
    
    @RequestMapping(value = "user_distribution_info")
    public ModelAndView user_distribution_info() {
        ModelAndView mav = new ModelAndView("admin/ajax/user_distribution_info");
        return mav;
    }
    
    @RequestMapping(value = "admin_user_info")
    public ModelAndView admin_user_info() {
        ModelAndView mav = new ModelAndView("admin/ajax/admin_user_info");
        List<TAdminUser> list = adminService.getAdminUsers();
        mav.addObject("users", list);
        return mav;
    }
    
    @ResponseBody
    @RequestMapping(value = "add_admin_user")
    public Boolean add_admin_user(TAdminUser user) {
        return adminService.saveAdminUser(user);
    }
    
    @ResponseBody
    @RequestMapping(value = "get_admin_user")
    public TAdminUser get_admin_user(Integer id) {
        return adminService.getAdminUser(id);
    }
    
    
    @ResponseBody
    @RequestMapping(value = "edit_admin_user")
    public Boolean edit_admin_user(TAdminUser user) {
        return adminService.editAdminUser(user);
    }
}
