package com.tonghang.server.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tonghang.server.common.dto.IpInfoDetail;
import com.tonghang.server.entity.TCity;
import com.tonghang.server.entity.TPhone;
import com.tonghang.server.entity.TProvince;
import com.tonghang.server.exception.ErrorCode;
import com.tonghang.server.exception.ServiceException;
import com.tonghang.server.mapper.TCityMapper;
import com.tonghang.server.mapper.TPhoneMapper;
import com.tonghang.server.mapper.TProvinceMapper;

@Service
public class SupportServiceImpl {

    @Autowired
    private IpInfoDetailServiceImpl ipService;

    @Autowired
    private TPhoneMapper userDao;
    @Autowired
    private TProvinceMapper provinceMapper;
    @Autowired
    private TCityMapper cityMapper;

    public Map<String, Object> getAreaByIp(String ip, Long userId)
            throws ServiceException {
        IpInfoDetail ipInfo = ipService.getInfoByIp(ip);
        Map<String, Object> result = new HashMap<String, Object>();

        if (ipInfo != null) {
            TPhone user = userDao.selectByPrimaryKey(userId.intValue());
            String lang = user.getLanguage();
            if (StringUtils.isEmpty(lang)) {
                lang = "zh_CN";
            }
            if (user != null && StringUtils.isNotEmpty(ipInfo.getProvince())) {
                TProvince province = provinceMapper
                        .selectByName(ipInfo.getProvince());
                if (province != null) {
                    result.put("provinceId", province.getId());
                    if ("zh_CN".equals(lang)) {
                        result.put("province", province.getName());
                    } else {
                        result.put("province", province.getEnName());
                    }
                    if (StringUtils.isNotEmpty(ipInfo.getCity())) {
                        TCity city = cityMapper.selectByName(ipInfo.getCity());
                        if (city != null) {
                            result.put("cityId", city.getId());
                            if ("zh_CN".equals(lang)) {
                                result.put("city", city.getName());
                            } else {
                                result.put("city", city.getEnName());
                            }
                        }
                    }
                }
            } else {
                throw new ServiceException(ErrorCode.code102.getCode(),
                        ErrorCode.code102.getHttpCode(),
                        ErrorCode.code102.getDesc());
            }
        }
        return result;
    }

}
