package com.tonghang.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.tonghang.server.common.dto.TNotificationDTO;
import com.tonghang.server.entity.TCity;
import com.tonghang.server.entity.TNotification;
import com.tonghang.server.entity.TPhone;
import com.tonghang.server.entity.TProvince;
import com.tonghang.server.entity.TTrade;
import com.tonghang.server.exception.ErrorCode;
import com.tonghang.server.exception.ServiceException;
import com.tonghang.server.mapper.TBannerMapper;
import com.tonghang.server.mapper.TCityMapper;
import com.tonghang.server.mapper.TNotificationMapper;
import com.tonghang.server.mapper.TPhoneMapper;
import com.tonghang.server.mapper.TProvinceMapper;
import com.tonghang.server.mapper.TTradeMapper;
import com.tonghang.server.util.NotificationTypeEnum;

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
    @Autowired
    private TTradeMapper tradeMapper;
    @Autowired
    private TNotificationMapper notificationMapper;
    @Autowired
    private TBannerMapper bannerMapper;

    private static Logger log = org.slf4j.LoggerFactory
            .getLogger(SupportServiceImpl.class);

    public List<TCity> getAreaByIp(String ip, Long userId)
            throws ServiceException {
        List<TCity> citys = new ArrayList<TCity>();
        TPhone user = userDao.selectByPrimaryKey(userId.intValue());
        if (user != null) {
            String lang = user.getLanguage();
            if (StringUtils.isEmpty(lang)) {
                lang = "zh_CN";
            }
            if ("zh_CN".equals(lang)) {

                citys = cityMapper.selectMostUsed();
            } else {
                citys = cityMapper.selectMostUsedEn();
            }
            if (CollectionUtils.isEmpty(citys)) {
                TCity city = cityMapper.selectByPrimaryKey(17);
                city.setEnName(null);
                citys.add(city);
            }
        } else {
            throw new ServiceException(ErrorCode.code102.getCode(),
                    ErrorCode.code102.getHttpCode(),
                    ErrorCode.code102.getDesc());
        }
        return citys;
    }

    public List<TProvince> getProvince(Long userId) throws ServiceException {
        TPhone user = userDao.selectByPrimaryKey(userId.intValue());
        List<TProvince> provinces = new ArrayList<TProvince>();
        if (user != null) {
            String lang = user.getLanguage();
            if (StringUtils.isEmpty(lang)) {
                lang = "zh_CN";
            }
            if ("zh_CN".equals(lang)) {

                provinces = provinceMapper.selectAllZh();
            } else {
                provinces = provinceMapper.selectAllEn();
            }
        } else {
            throw new ServiceException(ErrorCode.code102.getCode(),
                    ErrorCode.code102.getHttpCode(),
                    ErrorCode.code102.getDesc());
        }
        return provinces;

    }

    public List<TCity> getCityByProvinceId(Long userId, int provinceId)
            throws ServiceException {
        TPhone user = userDao.selectByPrimaryKey(userId.intValue());
        List<TCity> citys = new ArrayList<TCity>();
        if (user != null) {
            String lang = user.getLanguage();
            if (StringUtils.isEmpty(lang)) {
                lang = "zh_CN";
            }
            if ("zh_CN".equals(lang)) {

                citys = cityMapper.selectByProvinceIdZh(provinceId);
            } else {
                citys = cityMapper.selectByProvinceIdEn(provinceId);
            }
        } else {
            throw new ServiceException(ErrorCode.code102.getCode(),
                    ErrorCode.code102.getHttpCode(),
                    ErrorCode.code102.getDesc());
        }
        return citys;

    }

    public List<TTrade> getTrade(Long userId) throws ServiceException {
        TPhone user = userDao.selectByPrimaryKey(userId.intValue());
        List<TTrade> trades = new ArrayList<TTrade>();
        if (user != null) {
            String lang = user.getLanguage();
            if (StringUtils.isEmpty(lang)) {
                lang = "zh_CN";
            }
            if ("zh_CN".equals(lang)) {

                trades = tradeMapper.selectAllZh();
            } else {
                trades = tradeMapper.selectAllEn();
            }
        } else {
            throw new ServiceException(ErrorCode.code102.getCode(),
                    ErrorCode.code102.getHttpCode(),
                    ErrorCode.code102.getDesc());
        }
        return trades;
    }

    public Object getMessage(Integer userId) throws ServiceException {
        TPhone user = userDao.selectByPrimaryKey(userId);
        List<TNotification> notifications = new ArrayList<TNotification>();
        List<TNotificationDTO> result = new ArrayList<TNotificationDTO>();
        if (user != null) {
            notifications = notificationMapper.selectByPid(userId);
            for (TNotification bean : notifications) {
                if (NotificationTypeEnum.Circle.getCode().equals(bean.getType())
                        || NotificationTypeEnum.Article.getCode()
                                .equals(bean.getType())) {
                    TPhone userinfo = userDao.getUserInfoById(bean.getProId());
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
                    result.add(new TNotificationDTO(bean, userinfo));
                } else {
                    result.add(new TNotificationDTO(bean));
                }
                // notificationMapper.deleteByPrimaryKey(bean.getId());
            }
        } else {
            throw new ServiceException(ErrorCode.code102.getCode(),
                    ErrorCode.code102.getHttpCode(),
                    ErrorCode.code102.getDesc());
        }
        return result;
    }

    public Object readMessage(Integer userId, String id)
            throws ServiceException {
        TPhone user = userDao.selectByPrimaryKey(userId);
        if (user != null) {
            notificationMapper.deleteByPrimaryKey(Integer.valueOf(id));
        } else {
            throw new ServiceException(ErrorCode.code102.getCode(),
                    ErrorCode.code102.getHttpCode(),
                    ErrorCode.code102.getDesc());
        }
        return "success";
    }

    public Object banner() {
        return bannerMapper.selectAllEnable();
    }

}
