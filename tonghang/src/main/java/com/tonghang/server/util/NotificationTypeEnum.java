package com.tonghang.server.util;

public enum NotificationTypeEnum {

    System("系统通知", "1"), Friend("好友申请", "2"), Circle("朋友圈提醒", "3");

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
