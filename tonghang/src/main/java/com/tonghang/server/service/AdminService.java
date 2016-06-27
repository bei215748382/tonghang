package com.tonghang.server.service;

import java.util.List;

import com.tonghang.server.entity.TAdminUser;
import com.tonghang.server.entity.TCircle;
import com.tonghang.server.entity.TCity;
import com.tonghang.server.entity.TPhone;
import com.tonghang.server.entity.TService;
import com.tonghang.server.entity.TTrade;
import com.tonghang.server.vo.ArticleInfo;
import com.tonghang.server.vo.ArticlesVo;
import com.tonghang.server.vo.CheckCommentVo;
import com.tonghang.server.vo.CircleVo;
import com.tonghang.server.vo.ServiceVo;
import com.tonghang.server.vo.UserVo;

public interface AdminService {
	
	List<CircleVo> getCircleUnCheck();//获取同行圈待审核的内容和已审核的内容,check等于null表示待审核

	List<CircleVo> getCircleChecked();//获取同行圈待审核的内容和已审核的内容,1或者2表示已审核
	
	List<TService> getServiceUnCheck();//获取服务待审核的内容和已审核的内容,check等于null表示待审核
	
	List<TService> getServiceChecked();//获取服务待审核的内容和已审核的内容,1或者2表示已审核
	
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

    Boolean checkCircle(Integer id);//设置通过

    List<TAdminUser> getAdminUsers();//查找所有后台账户

    Boolean saveAdminUser(TAdminUser user);//保存管理用户

    TAdminUser getAdminUser(Integer id);//获取管理用户

    Boolean editAdminUser(TAdminUser user);//编辑管理用户

    List<ServiceVo> getUncheckedServices();//获取未审核的服务

    List<ServiceVo> getCheckedServices();//获取审核通过的服务

    Boolean checkService(Integer id);//对服务审核

    Boolean checkComment(Integer id);//对回复审核
    
    

}
