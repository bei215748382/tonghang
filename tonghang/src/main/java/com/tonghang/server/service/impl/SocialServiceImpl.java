package com.tonghang.server.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.tonghang.server.entity.TPhone;
import com.tonghang.server.exception.ErrorCode;
import com.tonghang.server.exception.ServiceException;
import com.tonghang.server.mapper.TCircleMapper;
import com.tonghang.server.mapper.TPhoneMapper;

public class SocialServiceImpl {

	@Autowired
	private TCircleMapper  circleMapper;
	@Autowired
	private TPhoneMapper userMapper;
	
	public Map<String, Object> publishSns(int userId, String txt, MultipartFile[] pictures) throws ServiceException {
		// TODO Auto-generated method stub
		TPhone user = userMapper.selectByPrimaryKey(userId);
		if (user == null) {
			throw new ServiceException(ErrorCode.code101.getCode(), ErrorCode.code101.getHttpCode(),
					ErrorCode.code101.getDesc());
		}
		return null;
	}

}
