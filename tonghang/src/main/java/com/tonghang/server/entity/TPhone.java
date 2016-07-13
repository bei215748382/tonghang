package com.tonghang.server.entity;

import java.util.Date;

public class TPhone {
    private Integer id;

    private String phone;

    private String password;

    private Date timestamp;

    private String name;

    private String pic;

    private String sex;

    private String position;

    private String college;

    private String education;

    private Integer tradeId;

    private Integer provinceId;

    private Integer cityId;

    private String remark;

    private String device;

    private Double longitude;

    private Double latitude;

    private Integer state;

    private String language;

    private Integer groupId;

    private String trade;// 行业名称

    private String province;// 省份名称

    private String city;// 城市名称

    private String company;

    public TPhone() {
    }

    public TPhone(TPhone user) {
        this.id = user.getId();
        this.phone = user.getPhone();
        this.name = user.getName();
        this.pic = user.getPic();
        this.sex = user.getSex();
        this.position = user.getPosition();
        this.college = user.getCollege();
        this.education = user.getEducation();
        this.tradeId = user.getTradeId();
        this.provinceId = user.getTradeId();
        this.cityId = user.getCityId();
        this.remark = user.getRemark();
        this.longitude = user.getLongitude();
        this.language = user.getLanguage();
        this.groupId = user.getGroupId();
        this.trade = user.getTrade();
        this.province = user.getProvince();
        this.city = user.getCity();
        this.company = user.getCompany();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @Override
    public String toString() {
        return "TPhone [id=" + id + ", phone=" + phone + ", timestamp="
                + timestamp + ", name=" + name + ", pic=" + pic + ", sex=" + sex
                + ", position=" + position + ", college=" + college
                + ", education=" + education + ", tradeId=" + tradeId
                + ", provinceId=" + provinceId + ", cityId=" + cityId
                + ", remark=" + remark + ", device=" + device + ", longitude="
                + longitude + ", latitude=" + latitude + ", state=" + state
                + ", language=" + language + ", groupId=" + groupId + ", trade="
                + trade + ", province=" + province + ", city=" + city
                + ", company=" + company + "]";
    }

}