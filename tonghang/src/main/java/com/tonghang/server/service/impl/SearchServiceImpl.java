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
import com.tonghang.server.entity.TPhone;
import com.tonghang.server.entity.TService;
import com.tonghang.server.mapper.TCircleMapper;
import com.tonghang.server.mapper.TPhoneMapper;
import com.tonghang.server.mapper.TServiceMapper;

@Service
public class SearchServiceImpl {

    @Autowired
    private TPhoneMapper userMapper;
    @Autowired
    private TServiceMapper serviceMapper;
    @Autowired
    private TCircleMapper circleMapper;

    public Object searchUser(String key) {
        List<TUserDTO> result = new ArrayList<TUserDTO>();
        if (StringUtils.isNotBlank(key)) {
            if (StringUtils.isNumeric(key)) {
                TPhone user = userMapper.selectByPhone(key);
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
}
