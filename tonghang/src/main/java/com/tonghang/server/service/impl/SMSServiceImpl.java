package com.tonghang.server.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tonghang.server.entity.TPhone;
import com.tonghang.server.exception.ServiceException;
import com.tonghang.server.mapper.TPhoneMapper;
import com.tonghang.server.util.SMSUtil;

@Service
public class SMSServiceImpl {

    private static Logger log = org.slf4j.LoggerFactory
            .getLogger(SMSServiceImpl.class);

    @Autowired
    private TPhoneMapper userMapper;

    /**
     * 发送注册验证码
     * 
     * @param mobile
     *            手机号
     * @return 
     * @throws ServiceException
     */
    public Map<String, Object> sendRegCode(String mobile) throws ServiceException {
        Map<String, Object> result = new HashMap<String, Object>();
        TPhone user = userMapper.selectByPhone(mobile);
        if (user == null) {
            String content = "您正在注册同行账号。验证码是：";
            SMSUtil.sendSMS(mobile, content);
            result.put("isRegist", "false");
            result.put("success", "true");
        } else {
            result.put("isRegist", "true");
            result.put("success", "false");
        }
        return (result);
    }

    /**
     * 重置密码
     * 
     * @param mobile
     * @throws ServiceException
     */
    public Map<String, Object> sendRetPasswordCode(String mobile) throws ServiceException {

        Map<String, Object> result = new HashMap<String, Object>();
        TPhone user = userMapper.selectByPhone(mobile);
        if (user != null) {
            String content = "您正在重置密码。验证码是：";
            SMSUtil.sendSMS(mobile, content);
            result.put("isRegist", "true");
            result.put("success", "true");
        } else {
            result.put("isRegist", "false");

            result.put("success", "false");
        }

        return (result);
    }

    /**
     * 验证验证码是否正确
     * 
     * @param mobile
     * @param code
     * @return
     */
    public Map<String, Object> verifyCode(String mobile, String code) {
        boolean flag = false;
        Map<String, Object> result = new HashMap<String, Object>();
        String locationCode = SMSUtil.codes.get(mobile);
        if (StringUtils.isNotBlank(locationCode)) {
            flag = locationCode.equals(code) ? true : false;
            result.put("success", "" + flag);
        } else {
            result.put("success", "false");
        }
        return (result);

    }

}