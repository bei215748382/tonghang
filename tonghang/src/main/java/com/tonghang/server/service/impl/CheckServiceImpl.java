package com.tonghang.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tonghang.server.entity.TCircle;
import com.tonghang.server.mapper.TCircleMapper;
import com.tonghang.server.mapper.TCommentMapper;
import com.tonghang.server.mapper.TServiceMapper;
import com.tonghang.server.service.CheckService;

@Service
public class CheckServiceImpl implements CheckService {
	
	@Autowired
	private TCircleMapper tCircleMapper;
	
	@Autowired
	private TServiceMapper tServiceMapper;
	
	@Autowired
	private TCommentMapper tCommentMapper;

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

}
