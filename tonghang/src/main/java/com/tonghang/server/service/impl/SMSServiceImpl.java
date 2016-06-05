package com.tonghang.server.service.impl;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.tonghang.server.exception.ServiceException;
import com.tonghang.server.util.SMSUtil;

@Service
public class SMSServiceImpl {

    private static Logger log = org.slf4j.LoggerFactory
            .getLogger(SMSServiceImpl.class);


    /**
     * 发送注册验证码
     * @param mobile 手机号
     * @throws ServiceException
     */
    public void sendRegCode(String mobile) throws ServiceException {
        String content = "您正在注册同行账号。验证码是：" ;

        SMSUtil.sendSMS(mobile, content);
    }

    /**
     * 重置密码
     * @param mobile
     * @throws ServiceException
     */
    public void sendRetPasswordCode(String mobile) throws ServiceException {
        String content = "您正在重置密码。验证码是：" ;

        SMSUtil.sendSMS(mobile, content);
    }

    /**
     * 验证验证码是否正确
     * @param mobile
     * @param code
     * @return
     */
    public boolean verifyCode(String mobile, String code) {
        boolean flag = false;
        flag = SMSUtil.codes.get(mobile).equals(code) ? true : false;
        return flag;

    }


}