package com.tonghang.server.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tonghang.server.common.dto.TServiceDTO;
import com.tonghang.server.entity.TCircle;
import com.tonghang.server.entity.TCity;
import com.tonghang.server.entity.TPhone;
import com.tonghang.server.entity.TProvince;
import com.tonghang.server.entity.TService;
import com.tonghang.server.entity.TTrack;
import com.tonghang.server.entity.TTrade;
import com.tonghang.server.exception.ErrorCode;
import com.tonghang.server.exception.ServiceException;
import com.tonghang.server.mapper.TCircleMapper;
import com.tonghang.server.mapper.TCityMapper;
import com.tonghang.server.mapper.TPhoneMapper;
import com.tonghang.server.mapper.TProvinceMapper;
import com.tonghang.server.mapper.TServiceMapper;
import com.tonghang.server.mapper.TTrackMapper;
import com.tonghang.server.mapper.TTradeMapper;
import com.tonghang.server.util.OSSUtil;
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
    private TCircleMapper circleMapper;

    @Autowired
    private TServiceMapper serviceMap;

    @Autowired
    private TokenServiceImpl tokenService;

    @Autowired
    private HuanXinServiceImpl huanXinService;

    public Map<String, Object> registUser(String mobile, String password,
            String longitude, String latitude, String device)
                    throws ServiceException {
        TPhone user = userMapper.selectByPhone(mobile);
        if (user != null) {
            throw new ServiceException(ErrorCode.code100.getCode(),
                    ErrorCode.code100.getHttpCode(),
                    ErrorCode.code100.getDesc());
        }
        user = new TPhone();
        user.setPhone(mobile);
        user.setPassword(password);
        if (StringUtils.isNotBlank(latitude))
            user.setLatitude(Double.valueOf(latitude));
        if (StringUtils.isNotBlank(longitude))
            user.setLongitude(Double.valueOf(longitude));
        user.setLanguage("zh_CN");
        user.setDevice(device);
        userMapper.insert(user);
        user = userMapper.selectByPhone(mobile);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("userId", user.getId());
        data.put("token", tokenService.generateAccessToken(user));
        data.put("impassword", huanXinService.createUser(mobile));
        return data;
    }

    public Map<String, Object> login(String mobileNum, String password,
            String longitude, String latitude) throws ServiceException {
        TPhone user = userMapper.selectByPhone(mobileNum);
        boolean flag = false;
        if (user == null) {
            throw new ServiceException(ErrorCode.code102.getCode(),
                    ErrorCode.code102.getHttpCode(),
                    ErrorCode.code102.getDesc());
        }
        // if (!user.getPassword().equals(password)) {
        // throw new ServiceException(ErrorCode.code103);
        // }
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
        data.put("impassword", huanXinService.getUser(user.getPhone()));
        return data;
    }

    public Map<String, Object> modifyPassword(String mobileNum, String password,
            String code) throws ServiceException {
        TPhone user = userMapper.selectByPhone(mobileNum);
        if (user == null) {
            throw new ServiceException(ErrorCode.code101.getCode(),
                    ErrorCode.code101.getHttpCode(),
                    ErrorCode.code101.getDesc());
        }
        String locationCode = SMSUtil.codes.get(mobileNum);
        Map<String, Object> data = new HashMap<String, Object>();
        if (StringUtils.isNoneEmpty(locationCode)
                && locationCode.equals(code)) {
            user.setPassword(password);
            userMapper.updateByPrimaryKey(user);
            data.put("userId", user.getId());
            data.put("token", tokenService.generateAccessToken(user));
        } else {
            throw new ServiceException(ErrorCode.code70.getCode(),
                    ErrorCode.code70.getHttpCode(), ErrorCode.code70.getDesc());
        }
        return data;
    }

    public Object modifyInfo(Long userId, Map<String, String> params)
            throws ServiceException {
        TPhone user = userMapper.selectByPrimaryKey(userId.intValue());
        if (user == null) {
            throw new ServiceException(ErrorCode.code101.getCode(),
                    ErrorCode.code101.getHttpCode(),
                    ErrorCode.code101.getDesc());
        }
        user.setPassword(null);
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
        String language = params.get("language");
        if (StringUtils.isNoneBlank(language)) {
            user.setLanguage(language);
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
        user.setPassword(null);
        return user;
    }

    public Object getInfo(String targetUserId) throws ServiceException {
        if (StringUtils.isBlank(targetUserId)) {
            throw new ServiceException(ErrorCode.code22.getCode(),
                    ErrorCode.code22.getHttpCode(),
                    "userId:" + targetUserId + " is  illegal");
        }
        TPhone user = userMapper
                .selectByPrimaryKey(Integer.valueOf(targetUserId));
        if (user == null) {
            throw new ServiceException(ErrorCode.code101.getCode(),
                    ErrorCode.code101.getHttpCode(),
                    ErrorCode.code101.getDesc());
        }
        user.setPassword(null);
        String lang = "zh_CN";
        if (StringUtils.isNotEmpty(user.getLanguage())) {
            lang = user.getLanguage();
        }
        TTrade trade = tradeMapper.selectByPrimaryKey(user.getTradeId());
        TProvince province = provinceMapper
                .selectByPrimaryKey(user.getProvinceId());
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
        user.setPassword(null);
        return user;

    }

    public Object addService(int userId, String name, String describe,
            String pictures) throws ServiceException {

        TPhone user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            throw new ServiceException(ErrorCode.code101.getCode(),
                    ErrorCode.code101.getHttpCode(),
                    ErrorCode.code101.getDesc());
        }
        if (StringUtils.isNotBlank(pictures)) {
            String[] filepaths = pictures.split(",");
            pictures = "";
            for (String filepath : filepaths) {
                try {
                    filepath = OSSUtil.instance().uploadOss(filepath,
                            String.valueOf(userId));
                } catch (IOException e) {
                    filepath = null;
                    throw new ServiceException(ErrorCode.code601.getCode(),
                            ErrorCode.code601.getHttpCode(),
                            ErrorCode.code601.getDesc());
                }
                pictures += filepath + ",";
            }
        }
        TService service = new TService();
        service.setDescription(describe);
        service.setTitle(name);
        service.setPid(userId);
        service.setPictures(pictures);
        serviceMap.insert(service);

        TCircle circle = new TCircle();
        circle.setContent("我发布了新服务 \"" + name + "\"");
        circle.setPid(userId);
        circle.setType(1);
        circle.setDatetime(new Date());
        circle.setTradeId(user.getTradeId());
        circle.setPics(pictures);
        circle.setArea("");// TODO 地区需修改成省市id
        circleMapper.insert(circle);
        TServiceDTO result = new TServiceDTO(service);
        return result;

    }

    public Object getService(int userId, String targetUserId)
            throws ServiceException {
        TPhone user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            throw new ServiceException(ErrorCode.code101.getCode(),
                    ErrorCode.code101.getHttpCode(),
                    ErrorCode.code101.getDesc());
        }
        TPhone targetUser = userMapper
                .selectByPrimaryKey(Integer.valueOf(targetUserId));
        if (targetUser == null) {
            throw new ServiceException(ErrorCode.code101.getCode(),
                    ErrorCode.code101.getHttpCode(), "查询的目标用户不存在");
        }
        if (user.getId() != targetUser.getId()) {
            TTrack track = new TTrack();
            track.setPid(userId);
            track.setTargetPid(targetUser.getId());
            trackMapper.insert(track);
        }
        List<TServiceDTO> result = new ArrayList<TServiceDTO>();
        List<TService> services = serviceMap
                .getServicesByUserId(targetUser.getId());
        for (TService bean : services) {
            TServiceDTO service = new TServiceDTO(bean);
            result.add(service);
        }
        return result;
    }

    public Map<String, Object> getTrack(int userId) throws ServiceException {
        TPhone user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            throw new ServiceException(ErrorCode.code101.getCode(),
                    ErrorCode.code101.getHttpCode(),
                    ErrorCode.code101.getDesc());
        }

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("tracks", trackMapper.findOneBeenTrack(userId));
        return data;
    }

    public Object modifyIcon(int userId, String icon) throws ServiceException {

        TPhone user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            throw new ServiceException(ErrorCode.code101.getCode(),
                    ErrorCode.code101.getHttpCode(),
                    ErrorCode.code101.getDesc());
        }
        if (StringUtils.isNotBlank(icon)) {
            String[] filepaths = icon.split(",");
            if (filepaths != null && filepaths.length >= 1) {
                icon = filepaths[0];
                try {
                    icon = OSSUtil.instance().uploadOss(icon,
                            String.valueOf(userId));
                } catch (IOException e) {
                    icon = null;
                    throw new ServiceException(ErrorCode.code601.getCode(),
                            ErrorCode.code601.getHttpCode(),
                            ErrorCode.code601.getDesc());
                }
                user.setPic(icon);//
            }
            userMapper.updateByPrimaryKey(user);
        }
        user.setPassword(null);
        return user;
    }

    public Object updateService(int userId, String id, String name,
            String describe, String pictures) throws ServiceException {

        TPhone user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            throw new ServiceException(ErrorCode.code101.getCode(),
                    ErrorCode.code101.getHttpCode(),
                    ErrorCode.code101.getDesc());
        }
        TService service = serviceMap.selectByPrimaryKey(Integer.valueOf(id));
        if(service == null){
            throw new ServiceException(ErrorCode.code200);
        }
        if (StringUtils.isNotBlank(pictures)) {
            String[] filepaths = pictures.split(",");
            pictures = "";
            for (String filepath : filepaths) {
                try {
                    filepath = OSSUtil.instance().uploadOss(filepath,
                            String.valueOf(userId));
                } catch (IOException e) {
                    filepath = null;
                    throw new ServiceException(ErrorCode.code601.getCode(),
                            ErrorCode.code601.getHttpCode(),
                            ErrorCode.code601.getDesc());
                }
                pictures += filepath + ",";
            }
        }
        
        service.setDescription(describe);
        service.setTitle(name);
        service.setPid(userId);
        service.setPictures(pictures);
        serviceMap.updateByPrimaryKey(service);

        TCircle circle = new TCircle();
        circle.setContent("我发布了新服务 \"" + name + "\"");
        circle.setPid(userId);
        circle.setType(1);
        circle.setDatetime(new Date());
        circle.setTradeId(user.getTradeId());
        circle.setPics(pictures);
        circle.setArea("");// TODO 地区需修改成省市id
        circleMapper.insert(circle);
        TServiceDTO result = new TServiceDTO(service);
        return result;

    }

}
