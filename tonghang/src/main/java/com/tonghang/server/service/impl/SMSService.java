package com.tonghang.server.service.impl;

import java.io.IOException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;

import com.tonghang.server.util.Constants;

public class SMSService {

    private static Logger log = org.slf4j.LoggerFactory
            .getLogger(SMSService.class);

    public void sendSMS(String mobileNum, String content) {
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
            System.out.println(result); // 打印返回消息状态
        } catch (HttpException e) {
            log.error(
                    "HttpException happened when post http://gbk.sms.webchinese.cn ."
                            + e.getMessage());
        } catch (IOException e) {
            log.error(
                    "IOException happened when receive response from http://gbk.sms.webchinese.cn ."
                            + e.getMessage());
        }
        post.releaseConnection();

    }

    public static void main(String[] args) throws Exception {

        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn");
        post.addRequestHeader("Content-Type",
                "application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码
        NameValuePair[] data = { new NameValuePair("Uid", "kash520ybb"),
                new NameValuePair("Key", "e67d6add9007371bd1e9"),
                new NameValuePair("smsMob", "15267851229"),
                new NameValuePair("smsText", "验证码：8888") };
        post.setRequestBody(data);

        client.executeMethod(post);
        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();
        String result = new String(
                post.getResponseBodyAsString().getBytes("gbk"));
        System.out.println(result); // 打印返回消息状态

        post.releaseConnection();

    }

}