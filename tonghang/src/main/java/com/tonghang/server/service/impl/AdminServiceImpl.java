package com.tonghang.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tonghang.server.entity.TCircle;
import com.tonghang.server.mapper.TCircleMapper;
import com.tonghang.server.mapper.TCommentMapper;
import com.tonghang.server.mapper.TPhoneMapper;
import com.tonghang.server.mapper.TServiceMapper;
import com.tonghang.server.service.AdminService;
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
	
	@Override
	public List<TCircle> getCircleUnCheck() {
		return tCircleMapper.getCircleUnCheck();
	}

	@Override
	public List<TCircle> getCircleChecked() {
		return  tCircleMapper.getCircleChecked();
	}

	@Override
	public List<TCircle> getServiceUnCheck() {
		return tServiceMapper.getServiceUnCheck();
	}

	@Override
	public List<TCircle> getServiceChecked() {
		return tServiceMapper.getServiceChecked();
	}

	@Override
	public List<TCircle> getCommentUnCheck() {
		return tCommentMapper.getCommentUnCheck();
	}

	@Override
	public List<TCircle> getCommentChecked() {
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
    public List<TCircle> getArticles() {
        return tCircleMapper.selectAll();
    }

}
