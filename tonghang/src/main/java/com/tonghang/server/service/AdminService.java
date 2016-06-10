package com.tonghang.server.service;

import java.util.List;

import com.tonghang.server.entity.TCircle;
import com.tonghang.server.entity.TService;
import com.tonghang.server.vo.ServiceVo;
import com.tonghang.server.vo.UserVo;

public interface AdminService {
	
	List<TCircle> getCircleUnCheck();//获取同行圈待审核的内容和已审核的内容,check等于2表示待审核

	List<TCircle> getCircleChecked();//获取同行圈待审核的内容和已审核的内容,0或者1表示已审核
	
	List<TService> getServiceUnCheck();//获取服务待审核的内容和已审核的内容,check等于2表示待审核
	
	List<TService> getServiceChecked();//获取服务待审核的内容和已审核的内容,0或者1表示已审核
	
	List<TCircle> getCommentUnCheck();//获取回复待审核的内容和已审核的内容,check等于2表示待审核
	
	List<TCircle> getCommentChecked();//获取回复待审核的内容和已审核的内容,0或者1表示已审核

	List<UserVo> getUsers();//获取所有用户信息

    List<ServiceVo> getServices();//获取所有服务信息

    List<TCircle> getArticles();//获取文章列表成功

}
