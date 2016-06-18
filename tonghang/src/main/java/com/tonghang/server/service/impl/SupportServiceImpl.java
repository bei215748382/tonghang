package com.tonghang.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.tonghang.server.entity.TCity;
import com.tonghang.server.entity.TNotification;
import com.tonghang.server.entity.TPhone;
import com.tonghang.server.entity.TProvince;
import com.tonghang.server.entity.TTrade;
import com.tonghang.server.exception.ErrorCode;
import com.tonghang.server.exception.ServiceException;
import com.tonghang.server.mapper.TCityMapper;
import com.tonghang.server.mapper.TNotificationMapper;
import com.tonghang.server.mapper.TPhoneMapper;
import com.tonghang.server.mapper.TProvinceMapper;
import com.tonghang.server.mapper.TTradeMapper;

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

	public List<TCity> getAreaByIp(String ip, Long userId) throws ServiceException {
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
			    TCity city =cityMapper.selectByPrimaryKey(17);
			    city.setEnName(null);
                citys.add(city);
            }
		} else {
			throw new ServiceException(ErrorCode.code102.getCode(), ErrorCode.code102.getHttpCode(),
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
			throw new ServiceException(ErrorCode.code102.getCode(), ErrorCode.code102.getHttpCode(),
					ErrorCode.code102.getDesc());
		}
		return provinces;

	}

	public List<TCity> getCityByProvinceId(Long userId, int provinceId) throws ServiceException {
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
			throw new ServiceException(ErrorCode.code102.getCode(), ErrorCode.code102.getHttpCode(),
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
			throw new ServiceException(ErrorCode.code102.getCode(), ErrorCode.code102.getHttpCode(),
					ErrorCode.code102.getDesc());
		}
		return trades;
	}

    public Object getMessage(Integer userId) throws ServiceException {
        TPhone user = userDao.selectByPrimaryKey(userId.intValue());
        List<TNotification> notifications = new ArrayList<TNotification>();
        if (user != null) {
            notifications = notificationMapper.selectByPid(userId);
            for(TNotification bean :notifications){
                notificationMapper.deleteByPrimaryKey(bean.getId());
            }
        } else {
            throw new ServiceException(ErrorCode.code102.getCode(), ErrorCode.code102.getHttpCode(),
                    ErrorCode.code102.getDesc());
        }
        return notifications;
    }

}
