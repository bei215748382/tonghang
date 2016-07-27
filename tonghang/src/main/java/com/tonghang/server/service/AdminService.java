package com.tonghang.server.service;

import java.util.List;
import java.util.Map;

import com.tonghang.server.entity.TAdminUser;
import com.tonghang.server.entity.TBanner;
import com.tonghang.server.entity.TCircle;
import com.tonghang.server.entity.TCity;
import com.tonghang.server.entity.TComment;
import com.tonghang.server.entity.TPhone;
import com.tonghang.server.entity.TTrade;
import com.tonghang.server.vo.ArticleInfo;
import com.tonghang.server.vo.ArticlesVo;
import com.tonghang.server.vo.CheckCommentVo;
import com.tonghang.server.vo.CircleVo;
import com.tonghang.server.vo.IncVo;
import com.tonghang.server.vo.ServiceVo;
import com.tonghang.server.vo.UserVo;

public interface AdminService {
	
	List<CircleVo> getCircleUnCheck();//获取同行圈待审核的内容和已审核的内容,check等于null表示待审核

	List<CircleVo> getCircleChecked();//获取同行圈待审核的内容和已审核的内容,1或者2表示已审核
	
	List<CheckCommentVo> getCommentUnCheck();//获取回复待审核的内容和已审核的内容,check等于null表示待审核
	
	List<CheckCommentVo> getCommentChecked();//获取回复待审核的内容和已审核的内容,1或者2表示已审核

	List<UserVo> getUsers();//获取所有用户信息

    List<ServiceVo> getServices();//获取所有服务信息

    List<ArticlesVo> getArticles();//获取文章列表成功

    List<TCity> getCities();//获取地区，城市

    List<TTrade> getTrades();//获取行业

    void addArticle(TCircle circle);//添加文章

    ArticlesVo getArticle(Integer id);//获取单个文章编辑

    ArticleInfo getArticleInfo(Integer id);//查看文章

    TPhone getUserById(Integer id);//获取用户信息

    Boolean checkCircle(Integer id, Integer checked);//设置通过

    List<TAdminUser> getAdminUsers();//查找所有后台账户

    Boolean saveAdminUser(TAdminUser user);//保存管理用户

    TAdminUser getAdminUser(Integer id);//获取管理用户

    Boolean editAdminUser(TAdminUser user);//编辑管理用户

    List<CircleVo> getUncheckedServices();//获取未审核的服务

    List<CircleVo> getCheckedServices();//获取审核通过的服务

    Boolean checkService(Integer id,Integer checked);//对服务审核

    Boolean checkComment(Integer id, Integer checked);//对回复审核

    UserVo getUser(Integer id);//根据id获取用户信息

    List<TCircle> getUserCircle(Integer id);//根据用户id获取用户发布的朋友圈

    List<TComment> getUserComment(Integer id);//根据用户id获取用户发表的评论

    TCircle getServiceById(Integer id);//根据服务id获取服务

    Map<String, Object> login(TAdminUser user);//用户登入

    Map<String, Object> registUser(TAdminUser user);//注册用户

    void updateArticle(TCircle circle);//更新文章

    Map<String,Object> getTodayInc();//获取今日新增人数

    List<IncVo> get_inc_data();//获取三十日增长曲线

    IncVo getDistribution();//获取终端分布

    Map<String, Object> getDistributionMap();//获取人群分布数据

    boolean updateUser(TPhone phone);//更新用户

    TCircle getServiceByUid(Integer id);//根据用户id获取用户服务,服务只有一个

    List<TBanner> getBanners();//获取所有横幅资料

    void saveBanner(TBanner banner);//保存banner

    void updateArticleState(Integer id);//更新文章状态

    void deleteBanner(Integer id);//删除banner

	TBanner getBannerById(Integer id);//根据id获取banner

	void updateBanner(TBanner tBanner);//更新banner
    
}
