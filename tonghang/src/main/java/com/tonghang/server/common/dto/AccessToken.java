package com.tonghang.server.common.dto;

public class AccessToken {

    /**
     * APPKEY
     */
    private String appkey;
    /**
     * 用户唯一标识码
     */
    private Integer auid;
    /**
     * 当前时间
     */
    private Long currenttime;
    /**
     * 过期时间
     */
    private Long expriation;
    /**
     * 客户端IMEI码
     */
    private String imei;
    /**
     * 客户端MAC地址
     */
    private String mac;
    /**
     * 平台类型 1 Android 2 iOS
     */
    private int platformType;

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public Integer getAuid() {
        return auid;
    }

    public void setAuid(Integer auid) {
        this.auid = auid;
    }

    public Long getCurrenttime() {
        return currenttime;
    }

    public void setCurrenttime(Long currenttime) {
        this.currenttime = currenttime;
    }

    public Long getExpriation() {
        return expriation;
    }

    public void setExpriation(Long expriation) {
        this.expriation = expriation;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public int getPlatformType() {
        return platformType;
    }

    public void setPlatformType(int platformType) {
        this.platformType = platformType;
    }

    @Override
    public String toString() {
        return auid + "," + expriation + ",";
    }

    public static AccessToken parse(String str) {
        AccessToken token = new AccessToken();
        try {
            if (str != null) {
                String[] strArr = str.split(",");
                if (strArr.length > 0) {
                    token.setAuid(Integer.valueOf(strArr[0]));
                }
                if (strArr.length > 1) {
                    token.setExpriation(Long.valueOf(strArr[1]));
                }
            }
        } catch (Exception e) {

        }
        return token;
    }
}
