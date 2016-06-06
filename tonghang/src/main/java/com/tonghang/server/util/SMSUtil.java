package com.tonghang.server.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tonghang.server.exception.ErrorCode;
import com.tonghang.server.exception.ServiceException;

public class SMSUtil {

    private static Logger log = LoggerFactory.getLogger(SMSUtil.class);
    public static Map<String, String> codes = new HashMap<String, String>();

    public static void sendSMS(String mobileNum, String content)
            throws ServiceException {
        String code = getRandNum(6);
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn");
        post.addRequestHeader("Content-Type",
                "application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码
        NameValuePair[] data = {
                new NameValuePair("Uid", Constants.WANG_JIAN_UID),
                new NameValuePair("Key", "e67d6add9007371bd1e9"),
                new NameValuePair("smsMob", mobileNum),
                new NameValuePair("smsText", content + code) };
        post.setRequestBody(data);

        try {
            client.executeMethod(post);
            String result = new String(
                    post.getResponseBodyAsString().getBytes("gbk"));
            log.info("mobile number:{},content:{},result{}", mobileNum, content,
                    result);
            int resultCode = Integer.valueOf(result);
            if (resultCode < 0) {
                throw new ServiceException(ErrorCode.code10.getCode(),
                        ErrorCode.code10.getHttpCode(),
                        "error happen when send sms");
            }
            codes.put(mobileNum, code);
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

    private static String getRandNum(int charCount) {
        String charValue = "";
        for (int i = 0; i < charCount; i++) {
            char c = (char) (randomInt(0, 10) + '0');
            charValue += String.valueOf(c);
        }
        return charValue;

    }

    private static int randomInt(int from, int to) {
        Random r = new Random();
        return from + r.nextInt(to - from);
    }

}
