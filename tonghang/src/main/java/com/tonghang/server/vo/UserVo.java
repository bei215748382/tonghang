package com.tonghang.server.vo;

import java.util.List;

import com.tonghang.server.entity.TService;
import com.tonghang.server.entity.TTrade;

public class UserVo {
    
    private String phone;
    
    private String name;
    
    private TTrade trade;
    
    private List<TService> services;
    
    private Integer groupId;// 0为普通组，1为重要组
    
    private String state;// 用户状态，1为激活状态，2为冻结状态
    
    private Integer sum;// 活跃度，未定义如何计算

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TTrade getTrade() {
        return trade;
    }

    public void setTrade(TTrade trade) {
        this.trade = trade;
    }

    public List<TService> getServices() {
        return services;
    }

    public void setServices(List<TService> services) {
        this.services = services;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "UserVo [phone=" + phone + ", name=" + name + ", trade=" + trade
                + ", services=" + services + ", groupId=" + groupId
                + ", state=" + state + ", sum=" + sum + "]";
    }

}
