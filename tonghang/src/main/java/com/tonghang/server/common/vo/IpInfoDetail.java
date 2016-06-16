package com.tonghang.server.common.vo;

import java.io.Serializable;

/**
 * Project Name:share.core
 * 
 * Package:com.xiaoying.core.entity.ip
 * 
 * FileName:InfoDetail.java
 * 
 * @author yxx
 * 
 *         Purpose:ip地址信息实体类
 * 
 *         Create Time: 2016年1月18日 下午1:36:04
 * 
 *         Create Specification:
 * 
 *         Modified Time:
 * 
 *         Modified by:
 * 
 *         Modified Specification:
 * 
 *         Version: 1.0
 */
public class IpInfoDetail implements  Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6294080660506788141L;
	// private static final String UNKNOWN = "unknow";
	private String	ip;					// = UNKNOWN;
	private String	country;			// = UNKNOWN;
	private String	province;			// = UNKNOWN;
	private String	city;				// = UNKNOWN;
	private String	serviceProvider;	// = UNKNOWN;
	private String	latitude;			// = UNKNOWN;
	private String	longitude;			// = UNKNOWN;
	private String	timeZone;
	/**
	 * 中国行政区划代码
	 */
	private String	areaCode;
	/**
	 * 国际区号
	 */
	private String	internatinalCode;
	/**
	 * 国家代码
	 */
	private String	countryCode;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public String getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getInternatinalCode() {
		return internatinalCode;
	}

	public void setInternatinalCode(String internatinalCode) {
		this.internatinalCode = internatinalCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@Override
	public String toString() {
		return "InfoDetail [ip=" + ip + ", country=" + country + ", province=" + province + ", city=" + city
				+ ", serviceProvider=" + serviceProvider + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", timeZone=" + timeZone + ", areaCode=" + areaCode + ", internatinalCode=" + internatinalCode
				+ ", countryCode=" + countryCode + "]";
	}

}
