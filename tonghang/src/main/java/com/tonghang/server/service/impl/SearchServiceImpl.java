package com.tonghang.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tonghang.server.common.dto.TCircleDTO;
import com.tonghang.server.common.dto.TServiceDTO;
import com.tonghang.server.common.dto.TUserDTO;
import com.tonghang.server.entity.TCircle;
import com.tonghang.server.entity.TCity;
import com.tonghang.server.entity.TPhone;
import com.tonghang.server.entity.TProvince;
import com.tonghang.server.entity.TService;
import com.tonghang.server.entity.TTrade;
import com.tonghang.server.mapper.TCircleMapper;
import com.tonghang.server.mapper.TCityMapper;
import com.tonghang.server.mapper.TPhoneMapper;
import com.tonghang.server.mapper.TProvinceMapper;
import com.tonghang.server.mapper.TServiceMapper;
import com.tonghang.server.mapper.TTradeMapper;

@Service
public class SearchServiceImpl {

    @Autowired
    private TPhoneMapper userMapper;
    @Autowired
    private TServiceMapper serviceMapper;
    @Autowired
    private TCircleMapper circleMapper;
    @Autowired
    private TTradeMapper tradeMapper;

    @Autowired
    private TProvinceMapper provinceMapper;

    @Autowired
    private TCityMapper cityMapper;

    public Object searchUser(String key) {
        List<TUserDTO> result = new ArrayList<TUserDTO>();
        if (StringUtils.isNotBlank(key)) {
            if (StringUtils.isNumeric(key)) {
                TPhone user = userMapper.selectByPhone(key);
                user = complateUserInfo(user);
                if (user != null) {
                    TUserDTO bean = new TUserDTO(user, null, null);
                    List<TService> services = serviceMapper
                            .getServicesByUserId(user.getId());
                    if (CollectionUtils.isNotEmpty(services)) {
                        bean.setService(new TServiceDTO(services.get(0)));
                    }
                    List<TCircle> ciecles = circleMapper
                            .getMyCircles(user.getId());
                    if (CollectionUtils.isNotEmpty(ciecles)) {
                        bean.setCircle(new TCircleDTO(ciecles.get(0)));
                    }
                    result.add(bean);
                }

            }
            List<TPhone> users = userMapper.selectByName(key);
            if (CollectionUtils.isNotEmpty(users)) {
                for (TPhone user : users) {
                    user = complateUserInfo(user);
                    TUserDTO bean = new TUserDTO(user, null, null);
                    List<TService> services = serviceMapper
                            .getServicesByUserId(user.getId());
                    if (CollectionUtils.isNotEmpty(services)) {
                        bean.setService(new TServiceDTO(services.get(0)));
                    }
                    List<TCircle> ciecles = circleMapper
                            .getMyCircles(user.getId());
                    if (CollectionUtils.isNotEmpty(ciecles)) {
                        bean.setCircle(new TCircleDTO(ciecles.get(0)));
                    }
                    result.add(bean);
                }
            }

        }
        return result;
    }

    private TPhone complateUserInfo(TPhone user) {
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
        return user;
    }
}
