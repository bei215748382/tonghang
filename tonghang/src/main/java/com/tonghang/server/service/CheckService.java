package com.tonghang.server.service;

import java.util.List;

import com.tonghang.server.entity.TCircle;

public interface CheckService {
	
	List<TCircle> getCircleUnCheck();//获取同行圈待审核的内容和已审核的内容,check等于2表示待审核

	List<TCircle> getCircleChecked();//获取同行圈待审核的内容和已审核的内容,0或者1表示已审核
	
	List<TCircle> getServiceUnCheck();//获取服务待审核的内容和已审核的内容,check等于2表示待审核
	
	List<TCircle> getServiceChecked();//获取服务待审核的内容和已审核的内容,0或者1表示已审核
	
	List<TCircle> getCommentUnCheck();//获取回复待审核的内容和已审核的内容,check等于2表示待审核
	
	List<TCircle> getCommentChecked();//获取回复待审核的内容和已审核的内容,0或者1表示已审核

}
