package com.tonghang.server.common.dto;

import java.io.Serializable;

public class BasicRequestDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1981511802935727748L;

    private String content;

    /* 需要用户登录接口通用参数之小影用户id */
    private Long userId;

    private String accessToken;

    private String appkey;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    @Override
    public String toString() {
        return "BasicRequestDTO [content=" + content + ", userId=" + userId
                + ", accessToken=" + accessToken + ", appkey=" + appkey + "]";
    }

}