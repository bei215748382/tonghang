package com.tonghang.server.util;

public enum NotificationTypeEnum {

    System("系统通知", "1"), Article("资讯评论通知", "2"), Circle("同行圈评论通知", "3"), Check("审核通知", "4")
    , Service("服务通知", "5");

    private String name;
    private String code;

    NotificationTypeEnum(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
