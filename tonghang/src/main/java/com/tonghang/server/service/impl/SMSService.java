package com.tonghang.server.service.impl;

import java.io.IOException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;

import com.tonghang.server.exception.ErrorCode;
import com.tonghang.server.exception.ServiceException;
import com.tonghang.server.util.Constants;

public class SMSService {

    private static Logger log = org.slf4j.LoggerFactory
            .getLogger(SMSService.class);

    public void sendSMS(String mobileNum, String content)
            throws ServiceException {
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn");
        post.addRequestHeader("Content-Type",
                "application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码
        NameValuePair[] data = {
                new NameValuePair("Uid", Constants.WANG_JIAN_UID),
                new NameValuePair("Key", "e67d6add9007371bd1e9"),
                new NameValuePair("smsMob", mobileNum),
                new NameValuePair("smsText", content) };
        post.setRequestBody(data);

        try {
            client.executeMethod(post);
            String result = new String(
                    post.getResponseBodyAsString().getBytes("gbk"));
            log.info("mobile number:{},content:{},result{}", mobileNum, content,
                    result);
            int code = Integer.valueOf(result);
            if (code < 0) {
                throw new ServiceException(ErrorCode.code10.getCode(),
                        ErrorCode.code10.getHttpCode(),
                        "error happen when send sms");
            }
            // TODO 根据短信服务商的反馈不同有不同的结果。
        } catch (HttpException e) {
            log.error(
                    "HttpException happened when post http://gbk.sms.webchinese.cn ."
                            + e.getMessage());
            throw new ServiceException(ErrorCode.code10.getCode(),
                    ErrorCode.code10.getHttpCode(),
                    "error happen when send sms");
        } catch (IOException e) {
            log.error(
                    "IOException happened when receive response from http://gbk.sms.webchinese.cn ."
                            + e.getMessage());
            throw new ServiceException(ErrorCode.code10.getCode(),
                    ErrorCode.code10.getHttpCode(),
                    "error happen when send sms");
        }
        post.releaseConnection();

    }

}