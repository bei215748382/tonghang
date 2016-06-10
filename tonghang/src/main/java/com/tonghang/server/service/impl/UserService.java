package com.tonghang.server.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tonghang.server.entity.TCity;
import com.tonghang.server.entity.TPhone;
import com.tonghang.server.entity.TProvince;
import com.tonghang.server.entity.TService;
import com.tonghang.server.entity.TTrack;
import com.tonghang.server.entity.TTrade;
import com.tonghang.server.exception.ErrorCode;
import com.tonghang.server.exception.ServiceException;
import com.tonghang.server.mapper.TCityMapper;
import com.tonghang.server.mapper.TPhoneMapper;
import com.tonghang.server.mapper.TProvinceMapper;
import com.tonghang.server.mapper.TServiceMapper;
import com.tonghang.server.mapper.TTrackMapper;
import com.tonghang.server.mapper.TTradeMapper;
import com.tonghang.server.util.SMSUtil;

@Service
public class UserService {

	@Autowired
	private TPhoneMapper userMapper;

	@Autowired
	private TTradeMapper tradeMapper;

	@Autowired
	private TProvinceMapper provinceMapper;

	@Autowired
	private TCityMapper cityMapper;

	@Autowired
	private TTrackMapper trackMapper;

	@Autowired
	private TServiceMapper serviceMap;

	@Autowired
	private TokenServiceImpl tokenService;

	public Map<String, Object> registUser(String mobile, String password, String longitude, String latitude)
			throws ServiceException {
		TPhone user = userMapper.selectByPhone(mobile);
		if (user != null) {
			throw new ServiceException(ErrorCode.code100.getCode(), ErrorCode.code100.getHttpCode(),
					ErrorCode.code100.getDesc());
		}
		user = new TPhone();
		user.setPhone(mobile);
		user.setPassword(password);
		user.setLatitude(Double.valueOf(latitude));
		user.setLongitude(Double.valueOf(longitude));
		user.setLanguage("zh_CN");
		userMapper.insert(user);
		user = userMapper.selectByPhone(mobile);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("userId", user.getId());
		data.put("token", tokenService.generateAccessToken(user));
		return data;
	}

	public Map<String, Object> login(String mobileNum, String password, String longitude, String latitude)
			throws ServiceException {
		TPhone user = userMapper.selectByPhone(mobileNum);
		boolean flag = false;
		if (user == null) {
			throw new ServiceException(ErrorCode.code102.getCode(), ErrorCode.code102.getHttpCode(),
					ErrorCode.code102.getDesc());
		}
		if (StringUtils.isNotEmpty(latitude)) {
			user.setLatitude(Double.valueOf(latitude));
			flag = true;
		}
		if (StringUtils.isNotEmpty(longitude)) {
			user.setLongitude(Double.valueOf(longitude));
			flag = true;
		}
		if (flag) {
			userMapper.updateByPrimaryKey(user);
		}
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("userId", user.getId());
		data.put("token", tokenService.generateAccessToken(user));
		return data;
	}

	public Map<String, Object> modifyPassword(String mobileNum, String password, String code) throws ServiceException {
		TPhone user = userMapper.selectByPhone(mobileNum);
		if (user == null) {
			throw new ServiceException(ErrorCode.code101.getCode(), ErrorCode.code101.getHttpCode(),
					ErrorCode.code101.getDesc());
		}
		if (!SMSUtil.codes.get(mobileNum).equals(code)) {
			throw new ServiceException(ErrorCode.code70.getCode(), ErrorCode.code70.getHttpCode(),
					ErrorCode.code70.getDesc());
		}
		user.setPassword(password);
		userMapper.updateByPrimaryKey(user);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("userId", user.getId());
		data.put("token", tokenService.generateAccessToken(user));
		return data;
	}

	public Map<String, Object> modifyInfo(Long userId, Map<String, String> params) throws ServiceException {
		TPhone user = userMapper.selectByPrimaryKey(userId.intValue());
		if (user == null) {
			throw new ServiceException(ErrorCode.code101.getCode(), ErrorCode.code101.getHttpCode(),
					ErrorCode.code101.getDesc());
		}
		String name = params.get("name");
		if (StringUtils.isNotBlank(name)) {
			user.setName(name);
		}
		String sex = params.get("sex");
		if (StringUtils.isNotBlank(sex)) {
			user.setSex(sex);
		} else {
			user.setSex("4");
		}
		String college = params.get("college");
		if (StringUtils.isNoneBlank(college)) {
			user.setCollege(college);
		}
		String position = params.get("position");
		if (StringUtils.isNoneBlank(position)) {
			user.setPosition(position);
		}
		String tradeId = params.get("tradeId");
		if (StringUtils.isNoneBlank(tradeId)) {
			user.setTradeId(Integer.valueOf(tradeId));
		}
		String provinceId = params.get("provinceId");
		if (StringUtils.isNoneBlank(tradeId)) {
			user.setProvinceId(Integer.valueOf(provinceId));
		}
		String cityId = params.get("cityId");
		if (StringUtils.isNoneBlank(cityId)) {
			user.setCityId(Integer.valueOf(cityId));
		}
		String tags = params.get("tags");
		if (StringUtils.isEmpty(tags)) {
			user.setRemark(tags);
		}
		userMapper.updateByPrimaryKey(user);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("user", user);
		return data;
	}

	public Map<String, Object> getInfo(String targetUserId) throws ServiceException {
		TPhone user = userMapper.selectByPrimaryKey(Integer.valueOf(targetUserId));
		if (user == null) {
			throw new ServiceException(ErrorCode.code101.getCode(), ErrorCode.code101.getHttpCode(),
					ErrorCode.code101.getDesc());
		}
		String lang = "zh_CN";
		if (StringUtils.isNotEmpty(user.getLanguage())) {
			lang = user.getLanguage();
		}
		TTrade trade = tradeMapper.selectByPrimaryKey(user.getTradeId());
		TProvince province = provinceMapper.selectByPrimaryKey(user.getProvinceId());
		TCity city = cityMapper.selectByPrimaryKey(user.getCityId());
		if ("zh_CN".equals(lang)) {
			if (trade != null) {
				user.setTrade(trade.getName());
			}
			if (province != null) {
				user.setProvince(province.getName());
			}
			if (city != null) {
				user.setCity(city.getName());
			}
		} else {
			if (trade != null) {
				user.setTrade(trade.getEnName());
			}
			if (province != null) {
				user.setProvince(province.getEnName());
			}
			if (city != null) {
				user.setCity(city.getEnName());
			}
		}
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("user", user);
		return data;

	}

	public Map<String, Object> addService(int userId, String name, String describe, MultipartFile[] pictures)
			throws ServiceException {
		// TODO 判断稳健是否为空，不为空上传，病记录文件路径。
		String picturepath = null;// uploadFile();
		TPhone user = userMapper.selectByPrimaryKey(userId);
		if (user == null) {
			throw new ServiceException(ErrorCode.code101.getCode(), ErrorCode.code101.getHttpCode(),
					ErrorCode.code101.getDesc());
		}
		TService service = new TService();
		service.setDescription(describe);
		service.setTitle(name);
		service.setPid(userId);
		service.setPictures(picturepath);
		serviceMap.insert(service);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("services", serviceMap.getServicesByUserId(userId));
		return data;

	}

	public Map<String, Object> getService(int userId, String targetUserId) throws ServiceException {
		TPhone user = userMapper.selectByPrimaryKey(userId);
		if (user == null) {
			throw new ServiceException(ErrorCode.code101.getCode(), ErrorCode.code101.getHttpCode(),
					ErrorCode.code101.getDesc());
		}
		TPhone targetUser = userMapper.selectByPrimaryKey(Integer.valueOf(targetUserId));
		if (targetUser == null) {
			throw new ServiceException(ErrorCode.code101.getCode(), ErrorCode.code101.getHttpCode(), "查询的目标用户不存在");
		}
		TTrack track = new TTrack();
		track.setPid(userId);
		track.setTargetPid(targetUser.getId());
		trackMapper.insert(track);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("services", serviceMap.getServicesByUserId(targetUser.getId()));
		return data;
	}

	public Map<String, Object> getTrack(int userId) throws ServiceException {
		TPhone user = userMapper.selectByPrimaryKey(userId);
		if (user == null) {
			throw new ServiceException(ErrorCode.code101.getCode(), ErrorCode.code101.getHttpCode(),
					ErrorCode.code101.getDesc());
		}

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("tracks", trackMapper.findOneBeenTrack(userId));
		return data;
	}

}
