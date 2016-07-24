package com.tonghang.server.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tonghang.server.entity.TAdminUser;
import com.tonghang.server.entity.TBanner;
import com.tonghang.server.entity.TCircle;
import com.tonghang.server.entity.TCity;
import com.tonghang.server.entity.TComment;
import com.tonghang.server.entity.TPhone;
import com.tonghang.server.entity.TTrade;
import com.tonghang.server.mapper.TAdminUserMapper;
import com.tonghang.server.mapper.TBannerMapper;
import com.tonghang.server.mapper.TCircleMapper;
import com.tonghang.server.mapper.TCityMapper;
import com.tonghang.server.mapper.TCommentMapper;
import com.tonghang.server.mapper.TPhoneMapper;
import com.tonghang.server.mapper.TTradeMapper;
import com.tonghang.server.service.AdminService;
import com.tonghang.server.util.EnumCollection.ResponseCode;
import com.tonghang.server.util.MD5Util;
import com.tonghang.server.util.StringUtil;
import com.tonghang.server.vo.ArticleInfo;
import com.tonghang.server.vo.ArticlesVo;
import com.tonghang.server.vo.CheckCommentVo;
import com.tonghang.server.vo.CircleVo;
import com.tonghang.server.vo.IncVo;
import com.tonghang.server.vo.ServiceVo;
import com.tonghang.server.vo.TodayIncVo;
import com.tonghang.server.vo.UserVo;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private TCircleMapper tCircleMapper;

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

    @Autowired
    private TBannerMapper tBannerMapper;

    @Override
    public List<CircleVo> getCircleUnCheck() {
        return tCircleMapper.getCircleUnCheck();
    }

    @Override
    public List<CircleVo> getCircleChecked() {
        return tCircleMapper.getCircleChecked();
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
        return tCircleMapper.getServices();
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
    public List<CircleVo> getUncheckedServices() {
        return tCircleMapper.getUncheckedServices();
    }

    @Override
    public List<CircleVo> getCheckedServices() {
        return tCircleMapper.getCheckedServices();
    }

    @Override
    public Boolean checkService(Integer id) {
        return tCircleMapper.checkService(id);
    }

    @Override
    public Boolean checkComment(Integer id) {
        return tCommentMapper.checkComment(id);
    }

    @Override
    public UserVo getUser(Integer id) {
        return tPhoneMapper.getUser(id);
    }

    @Override
    public List<TCircle> getUserCircle(Integer id) {
        return tCircleMapper.getCircleByUserId(id);
    }

    @Override
    public List<TComment> getUserComment(Integer id) {
        return tCommentMapper.getCommentByUserId(id);
    }

    @Override
    public Map<String, Object> login(TAdminUser user) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            TAdminUser u = tAdminUserMapper
                    .selectByUsername(user.getUsername());
            if (u != null) {// 验证用户是否存在
                String passwordMD5 = MD5Util.string2MD5(user.getPassword());
                if (u.getPassword().equals(passwordMD5)) {// 验证密码是否正确
                    if (StringUtil.adminRole.equals(u.getRole())) {// 验证用户是否有权限登入
                        map.put(StringUtil.responseCode,
                                ResponseCode.LOGIN_SUCCESS.getCode());
                        map.put(StringUtil.responseMsg,
                                ResponseCode.LOGIN_SUCCESS.getMsg());
                        map.put(StringUtil.responseObj, u);
                    } else {
                        map.put(StringUtil.responseCode,
                                ResponseCode.LOGIN_ROLE_FAILED.getCode());
                        map.put(StringUtil.responseMsg,
                                ResponseCode.LOGIN_ROLE_FAILED.getMsg());
                    }
                } else {
                    map.put(StringUtil.responseCode,
                            ResponseCode.LOGIN_PASSWORD_ERROR.getCode());
                    map.put(StringUtil.responseMsg,
                            ResponseCode.LOGIN_PASSWORD_ERROR.getMsg());
                }
            } else {
                map.put(StringUtil.responseCode,
                        ResponseCode.LOGIN_USER_NOT_EXIST.getCode());
                map.put(StringUtil.responseMsg,
                        ResponseCode.LOGIN_USER_NOT_EXIST.getMsg());
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put(StringUtil.responseCode,
                    ResponseCode.LOGIN_ERROR.getCode());
            map.put(StringUtil.responseMsg, ResponseCode.LOGIN_ERROR.getMsg());
        }
        return map;
    }

    @Override
    public Map<String, Object> registUser(TAdminUser user) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            TAdminUser u = tAdminUserMapper
                    .selectByUsername(user.getUsername());
            if (u != null) {
                map.put(StringUtil.responseCode,
                        ResponseCode.REGIST_EXIST.getCode());
                map.put(StringUtil.responseCode,
                        ResponseCode.REGIST_EXIST.getMsg());
            } else {
                String passwordMD5 = MD5Util.string2MD5(user.getPassword());
                user.setPassword(passwordMD5);
                tAdminUserMapper.insert(user);
                map.put(StringUtil.responseCode,
                        ResponseCode.REGIST_SUCCESS.getCode());
                map.put(StringUtil.responseCode,
                        ResponseCode.REGIST_SUCCESS.getMsg());
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put(StringUtil.responseCode,
                    ResponseCode.REGIST_ERROR.getCode());
            map.put(StringUtil.responseCode,
                    ResponseCode.REGIST_ERROR.getMsg());
        }
        return map;
    }

    @Override
    public TCircle getServiceById(Integer id) {
        return tCircleMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateArticle(TCircle circle) {
        tCircleMapper.updateByPrimaryKey(circle);

    }

    @Override
    public Map<String, Object> getTodayInc() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<TodayIncVo> list = tPhoneMapper.getTodayNum();
        for (TodayIncVo l : list) {
            if ("1".equals(l.getDevice())) {
                map.put("android", l.getNum());
            } else if ("2".equals(l.getDevice())) {
                map.put("ios", l.getNum());
            } else {
                map.put("other", l.getNum());
            }
        }
        return map;
    }

    @Override
    public List<IncVo> get_inc_data() {
        return tPhoneMapper.get_inc_data();
    }

    @Override
    public IncVo getDistribution() {
        return tPhoneMapper.getDistribution();
    }

    @Override
    public Map<String, Object> getDistributionMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        int peopleS = tPhoneMapper.getServicePeople();// 有服务人数
        int peopleN = tPhoneMapper.getNoServicePeople();// 没服务人数
        List<TodayIncVo> list = tPhoneMapper.getCityPeople();// 获取城市分布
        map.put("peopleS", peopleS);
        map.put("peopleN", peopleN);
        map.put("peopleC", list);
        return map;
    }

    @Override
    public boolean updateUser(TPhone phone) {
        tPhoneMapper.updateByPrimaryKey(phone);
        return true;
    }

    @Override
    public TCircle getServiceByUid(Integer id) {
        return tCircleMapper.getServiceByUid(id);
    }

    @Override
    public List<TBanner> getBanners() {
        return tBannerMapper.selectAll();
    }

    @Override
    public void saveBanner(TBanner banner) {
        tBannerMapper.insert(banner);

    }

    @Override
    public void updateArticleState(Integer id) {
        TCircle circle = tCircleMapper.selectByPrimaryKey(id);
        if (circle != null) {
            if (circle.getChecked() == null || circle.getChecked() == 1) {
                circle.setChecked(2);
            } else {
                circle.setChecked(1);
            }
        }
        tCircleMapper.updateByPrimaryKey(circle);

    }

    @Override
    public void deleteBanner(Integer id) {
        TBanner banner = tBannerMapper.findById(id);
        if (banner != null)
            tBannerMapper.deleteByPrimaryKey(banner.getId());
    }

}
