package com.tonghang.server.vo;

import java.util.Date;

public class IncVo {
    
    private Date date;// 日期
    private Integer android;// android用户
    private Integer ios;// ios用户
    private Integer other;// 其他用户

    private double android_rate;
    private double ios_rate;
    private double other_rate;
    
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getAndroid() {
        return android;
    }

    public void setAndroid(Integer android) {
        this.android = android;
    }

    public Integer getIos() {
        return ios;
    }

    public void setIos(Integer ios) {
        this.ios = ios;
    }

    public Integer getOther() {
        return other;
    }

    public void setOther(Integer other) {
        this.other = other;
    }

    public double getAndroid_rate() {
        return (double)android/(android+ios+other)*100;
    }

    public void setAndroid_rate(double android_rate) {
        this.android_rate = android_rate;
    }

    public double getIos_rate() {
        return (double)ios/(android+ios+other)*100;
    }

    public void setIos_rate(double ios_rate) {
        this.ios_rate = ios_rate;
    }

    public double getOther_rate() {
        return (double)other/(android+ios+other)*100;
    }

    public void setOther_rate(double other_rate) {
        this.other_rate = other_rate;
    }

    @Override
    public String toString() {
        return "IncVo [date=" + date + ", android=" + android + ", ios=" + ios
                + ", other=" + other + ", android_rate=" + android_rate
                + ", ios_rate=" + ios_rate + ", other_rate=" + other_rate + "]";
    }

}
