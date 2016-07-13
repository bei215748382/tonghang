package com.tonghang.server.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tonghang.server.common.dto.TCircleDTO;
import com.tonghang.server.common.dto.TCommentDTO;
import com.tonghang.server.common.dto.TServiceDTO;
import com.tonghang.server.common.dto.TTrackDTO;
import com.tonghang.server.entity.TCircle;
import com.tonghang.server.entity.TCircleLike;
import com.tonghang.server.entity.TCity;
import com.tonghang.server.entity.TComment;
import com.tonghang.server.entity.TFavorite;
import com.tonghang.server.entity.TPhone;
import com.tonghang.server.entity.TProvince;
import com.tonghang.server.entity.TService;
import com.tonghang.server.entity.TTrack;
import com.tonghang.server.entity.TTrade;
import com.tonghang.server.exception.ErrorCode;
import com.tonghang.server.exception.ServiceException;
import com.tonghang.server.mapper.TCircleLikeMapper;
import com.tonghang.server.mapper.TCircleMapper;
import com.tonghang.server.mapper.TCityMapper;
import com.tonghang.server.mapper.TCommentMapper;
import com.tonghang.server.mapper.TFavoriteMapper;
import com.tonghang.server.mapper.TPhoneMapper;
import com.tonghang.server.mapper.TProvinceMapper;
import com.tonghang.server.mapper.TServiceMapper;
import com.tonghang.server.mapper.TTrackMapper;
import com.tonghang.server.mapper.TTradeMapper;
import com.tonghang.server.util.DESUtil;
import com.tonghang.server.util.OSSUtil;
import com.tonghang.server.util.SMSUtil;

@Service
public class UserService {

    private static Logger log = org.slf4j.LoggerFactory
            .getLogger(UserService.class);

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

    @Autowired
    private TCommentMapper commentMapper;
    @Autowired
    private TCircleLikeMapper likeMapper;
    @Autowired
    private TFavoriteMapper favoriteMapper;

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
        try {
            user.setPassword(new DESUtil("password").encrypt(password));
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.code103);
        }
        if (StringUtils.isNotBlank(latitude))
            user.setLatitude(Double.valueOf(latitude));
        if (StringUtils.isNotBlank(longitude))
            user.setLongitude(Double.valueOf(longitude));
        user.setLanguage("zh_CN");
        user.setDevice(device);
        user.setSex("4");
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
        log.info(user.toString());
        try {
            if (!user.getPassword()
                    .equals(new DESUtil("password").encrypt(password))) {
                throw new ServiceException(ErrorCode.code103);
            }
        } catch (Exception e) {
            log.error("encrypt fail" + e.getMessage());
            throw new ServiceException(ErrorCode.code400);
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
        data.put("impassword", huanXinService.getUser(user.getPhone()));
        if (StringUtils.isBlank(user.getName())
                || StringUtils.isBlank(user.getPic())
                || null == user.getCityId() || null == user.getProvinceId()
                || null == user.getTradeId() || "4".equals(user.getSex())
                || StringUtils.isBlank(user.getSex())) {
            data.put("info", false);
        } else {
            data.put("info", true);
        }
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
            try {
                user.setPassword(new DESUtil("password").encrypt(password));
            } catch (Exception e) {
                throw new ServiceException(ErrorCode.code103);
            }
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
            TTrade trade = tradeMapper
                    .selectByPrimaryKey(Integer.valueOf(tradeId));
            if (StringUtils.isNotBlank(user.getLanguage())
                    && "en_US".equals(user.getLanguage())) {
                user.setTrade(trade.getEnName());
            } else {
                user.setTrade(trade.getName());
            }
        }
        String provinceId = params.get("provinceId");
        if (StringUtils.isNoneBlank(provinceId)) {
            TProvince province = provinceMapper
                    .selectByPrimaryKey(Integer.valueOf(provinceId));
            user.setProvinceId(Integer.valueOf(provinceId));
            if (StringUtils.isNotBlank(user.getLanguage())
                    && "en_US".equals(user.getLanguage())) {
                user.setProvince(province.getEnName());
            } else {
                user.setProvince(province.getName());
            }
        }
        String cityId = params.get("cityId");
        if (StringUtils.isNoneBlank(cityId)) {
            user.setCityId(Integer.valueOf(cityId));
            TCity city = cityMapper.selectByPrimaryKey(Integer.valueOf(cityId));
            if (StringUtils.isNotBlank(user.getLanguage())
                    && "en_US".equals(user.getLanguage())) {
                user.setCity(city.getEnName());
            } else {
                user.setCity(city.getName());
            }
        }
        String company = params.get("company");
        if (StringUtils.isNoneBlank(company)) {
            user.setCompany(company);
        }
        String education = params.get("education");
        if (StringUtils.isNoneBlank(education)) {
            user.setEducation(education);
        }
        String tags = params.get("tags");
        if (StringUtils.isNotBlank(tags)) {
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
        circle.setTitle(name);
        circle.setContent(describe);
        circle.setPid(userId);
        circle.setType(3);
        circle.setDatetime(new Date());
        circle.setTradeId(user.getTradeId());
        circle.setPics(pictures);
        if (user.getCityId() != null) {
            TCity city = cityMapper.selectByPrimaryKey(user.getCityId());
            if ("en_US".equals(user.getLanguage())) {
                circle.setArea(city.getEnName());
            } else {
                circle.setArea(city.getName());
            }
        }
        circle.setComment(0);
        circleMapper.insert(circle);
        circle.setType(1);
        circle.setContent("我发布了新服务[" + name + "]");
        circleMapper.insert(circle);
        TServiceDTO result = new TServiceDTO(service);
        return result;

    }

    public Object getServices(int userId, String targetUserId)
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
        List<TCircleDTO> result = new ArrayList<TCircleDTO>();
        List<TCircle> services = circleMapper.getServicesByUserId(user.getId());
        for (TCircle bean : services) {
            TCircleDTO service = new TCircleDTO(bean);
            result.add(service);
        }
        return result;
    }

    public Object getTrack(int userId) throws ServiceException {
        TPhone user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            throw new ServiceException(ErrorCode.code101.getCode(),
                    ErrorCode.code101.getHttpCode(),
                    ErrorCode.code101.getDesc());
        }

        List<TTrack> tracks = trackMapper.findOneBeenTrack(userId);
        trackMapper.updateState(tracks);
        List<TTrackDTO> result = new ArrayList<TTrackDTO>();
        for (TTrack bean : tracks) {
            TPhone userinfo = userMapper.getUserInfoById(bean.getPid());
            if (userinfo.getCityId() != null
                    && userinfo.getProvinceId() != null) {
                TProvince province = provinceMapper
                        .selectByPrimaryKey(userinfo.getProvinceId());
                TCity city = cityMapper
                        .selectByPrimaryKey(userinfo.getCityId());
                if (StringUtils.isNotBlank(user.getLanguage())
                        && "en_US".equals(user.getLanguage())
                        && province != null && city != null) {
                    user.setProvince(province.getEnName());
                    user.setCity(city.getEnName());
                } else {
                    user.setProvince(province.getName());
                    user.setCity(city.getName());
                }
            }
            result.add(new TTrackDTO(bean, userinfo));
        }
        return result;
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
            String describe, String pictures, String paths)
                    throws ServiceException {

        TPhone user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            throw new ServiceException(ErrorCode.code101.getCode(),
                    ErrorCode.code101.getHttpCode(),
                    ErrorCode.code101.getDesc());
        }
        TCircle service = circleMapper.getServiceById(Integer.valueOf(id));
        if (service == null) {
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
        pictures = paths + pictures;
        service.setContent(describe);
        service.setTitle(name);
        service.setPid(userId);
        service.setPics(pictures);
        service.setDatetime(new Date());
        circleMapper.updateByPrimaryKey(service);

        TCircle circle = circleMapper.selectByPrimaryKey(Integer.valueOf(id));
        if (circle == null) {
            throw new ServiceException(ErrorCode.code200);
        }
        circle.setContent(describe);
        circle.setTitle(name);
        circle.setPid(userId);
        circle.setPics(pictures);
        circle.setDatetime(new Date());
        circleMapper.updateByPrimaryKey(circle);
        TCircleDTO result = new TCircleDTO(service);
        return result;

    }

    public Object getService(int userId, String id) throws ServiceException {
        TPhone user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            throw new ServiceException(ErrorCode.code101.getCode(),
                    ErrorCode.code101.getHttpCode(),
                    ErrorCode.code101.getDesc());
        }
        TCircle service = circleMapper.getServiceById(Integer.valueOf(id));
        if (service == null) {
            throw new ServiceException(ErrorCode.code200);
        }
        if (user.getId() != service.getPid()) {
            TTrack track = new TTrack();
            track.setPid(userId);
            track.setTargetPid(service.getPid());
            trackMapper.insert(track);
        }
        TPhone u = userMapper.getUserInfoById(service.getPid());
        if (u.getCityId() != null && u.getProvinceId() != null) {
            TProvince province = provinceMapper
                    .selectByPrimaryKey(u.getProvinceId());
            TCity city = cityMapper.selectByPrimaryKey(u.getCityId());
            if (StringUtils.isNotBlank(user.getLanguage())
                    && "en_US".equals(user.getLanguage()) && province != null
                    && city != null) {
                user.setProvince(province.getEnName());
                user.setCity(city.getEnName());
            } else {
                user.setProvince(province.getName());
                user.setCity(city.getName());
            }
        }
        TTrade trade = tradeMapper.selectByPrimaryKey(service.getTradeId());
        List<TCommentDTO> commentdto = new ArrayList<TCommentDTO>();
        if (service.getComment() != 0) {
            List<TComment> comments = commentMapper
                    .selectByCircleId(service.getId());
            if (CollectionUtils.isNotEmpty(comments)) {
                for (TComment bean : comments) {
                    TPhone userinfo = userMapper
                            .getUserInfoById(bean.getPidId());
                    commentdto.add(new TCommentDTO(bean, userinfo));
                }
            }
        }
        List<Integer> userids = likeMapper
                .selectAllLikePidByCircleId(service.getId());
        List<TPhone> userinfos = userMapper.selectByIds(userids);
        TCircleDTO dto = TCircleDTO.builder(service, u, commentdto, trade,
                userinfos);
        TCircleLike like = likeMapper.selectByCircleIdAndPid(service.getId(),
                user.getId());
        if (like == null) {
            dto.setLike(false);
        } else {
            dto.setLike(true);
        }
        TFavorite favorite = favoriteMapper.selectByPidFavid("3", userId,
                service.getId());
        dto.setFavorite(favorite);
        return dto;
    }

    public static void main(String[] args) {
        TPhone user = new TPhone();
        user.setName("a");
        user.setSex("1");
        user.setCityId(1);
        user.setProvinceId(1);
        user.setPic("kak");
        user.setTradeId(1);

        if (StringUtils.isBlank(user.getName())
                || StringUtils.isBlank(user.getPic())
                || null == user.getCityId() || null == user.getProvinceId()
                || null == user.getTradeId() || "4".equals(user.getSex())
                || StringUtils.isBlank(user.getSex())) {

            System.out.println("!!!!!!!!!!");
        }
    }

}
