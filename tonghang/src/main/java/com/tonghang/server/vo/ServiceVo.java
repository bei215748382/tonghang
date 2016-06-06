package com.tonghang.server.vo;

import java.util.List;

public class ServiceVo {
    
    private String title;// 技能名称
    
    private String description;// 技能说明
    
    private String[] pictures;// 图片列表
    
    private String name;// 姓名
    
    private String phone; // 用户手机
    
    private String trade; // 产业链名称
    
    private String state;// 用户状态，1为激活状态，2为冻结状态

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getPictures() {
        return pictures;
    }

    public void setPictures(String[] pictures) {
        this.pictures = pictures;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}