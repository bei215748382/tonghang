package com.tonghang.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tonghang.server.entity.TAdminUser;
import com.tonghang.server.entity.TCircle;
import com.tonghang.server.entity.TCity;
import com.tonghang.server.entity.TPhone;
import com.tonghang.server.entity.TService;
import com.tonghang.server.entity.TTrade;
import com.tonghang.server.mapper.TAdminUserMapper;
import com.tonghang.server.mapper.TCircleMapper;
import com.tonghang.server.mapper.TCityMapper;
import com.tonghang.server.mapper.TCommentMapper;
import com.tonghang.server.mapper.TPhoneMapper;
import com.tonghang.server.mapper.TServiceMapper;
import com.tonghang.server.mapper.TTradeMapper;
import com.tonghang.server.service.AdminService;
import com.tonghang.server.vo.ArticleInfo;
import com.tonghang.server.vo.ArticlesVo;
import com.tonghang.server.vo.CheckCommentVo;
import com.tonghang.server.vo.CircleVo;
import com.tonghang.server.vo.ServiceVo;
import com.tonghang.server.vo.UserVo;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private TCircleMapper tCircleMapper;

    @Autowired
    private TServiceMapper tServiceMapper;

    @Autowired
    private TCommentMapper tCommentMapper;

    @Autowired
    private TPhoneMapper tPhoneMapper;

    @Autowired
    private TCityMapper tCityMapper;

    @Autowired
    private TTradeMapper tTradeMapper;

    @Autowired
    private TAdminUserMapper tAdminUserMapper;

    @Override
    public List<CircleVo> getCircleUnCheck() {
        return tCircleMapper.getCircleUnCheck();
    }

    @Override
    public List<CircleVo> getCircleChecked() {
        return tCircleMapper.getCircleChecked();
    }

    @Override
    public List<TService> getServiceUnCheck() {
        return tServiceMapper.getServiceUnCheck();
    }

    @Override
    public List<TService> getServiceChecked() {
        return tServiceMapper.getServiceChecked();
    }

    @Override
    public List<CheckCommentVo> getCommentUnCheck() {
        return tCommentMapper.getCommentUnCheck();
    }

    @Override
    public List<CheckCommentVo> getCommentChecked() {
        return tCommentMapper.getCommentChecked();
    }

    @Override
    public List<UserVo> getUsers() {
        return tPhoneMapper.getUsers();
    }

    @Override
    public List<ServiceVo> getServices() {
        return tServiceMapper.getServices();
    }

    @Override
    public List<ArticlesVo> getArticles() {
        return tCircleMapper.getArticles();
    }

    @Override
    public List<TCity> getCities() {
        return tCityMapper.selectAll();
    }

    @Override
    public List<TTrade> getTrades() {
        return tTradeMapper.selectAll();
    }

    @Override
    public void addArticle(TCircle circle) {
        tCircleMapper.insert(circle);
    }

    @Override
    public ArticlesVo getArticle(Integer id) {
        return tCircleMapper.getArticle(id);
    }

    @Override
    public ArticleInfo getArticleInfo(Integer id) {
        return tCircleMapper.getArticleInfo(id);
    }

    @Override
    public TPhone getUserById(Integer id) {
        return tPhoneMapper.getUserById(id);
    }

    @Override
    public Boolean checkCircle(Integer id) {
        return tCircleMapper.checkCircle(id);
    }

    @Override
    public List<TAdminUser> getAdminUsers() {
        return tAdminUserMapper.selectAll();
    }

    @Override
    public Boolean saveAdminUser(TAdminUser user) {
        int a = tAdminUserMapper.insert(user);
        System.out.println(a);
        return true;
    }

    @Override
    public TAdminUser getAdminUser(Integer id) {
        return tAdminUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean editAdminUser(TAdminUser user) {
        tAdminUserMapper.updateByPrimaryKey(user);
        return true;
    }

    @Override
    public List<ServiceVo> getUncheckedServices() {
        return tServiceMapper.getUncheckedServices();
    }

    @Override
    public List<ServiceVo> getCheckedServices() {
        return tServiceMapper.getCheckedServices();
    }

    @Override
    public Boolean checkService(Integer id) {
        return tServiceMapper.checkService(id);
    }

    @Override
    public Boolean checkComment(Integer id) {
        return tCommentMapper.checkComment(id);
    }

}
