package com.tonghang.server.vo;

public class TodayIncVo {
    private String device;
    private String num;

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "{device:" + device + ", num:" + num + "}";
    }
}
