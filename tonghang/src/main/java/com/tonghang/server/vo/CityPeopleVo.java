package com.tonghang.server.vo;

public class CityPeopleVo {
    
    private String city;
    
    private String num;
    
    private double city_rate;
    
    private double sum;
    
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getNum() {
        return num;
    }
    public void setNum(String num) {
        this.num = num;
    }
    public double getCity_rate() {
        return city_rate;
    }
    public void setCity_rate(double city_rate) {
        this.city_rate = city_rate;
    }
    public double getSum() {
        return sum;
    }
    public void setSum(double sum) {
        this.sum = sum;
    }
    @Override
    public String toString() {
        return "CityPeopleVo [city=" + city + ", num=" + num + ", city_rate="
                + city_rate + ", sum=" + sum + "]";
    }

}
