package com.tonghang.server.util;

public class Constants {

    /**
     * 网建短信平台 uid
     */
    public static final String WANG_JIAN_UID = "kash520ybb";
    
    /**
     * 阿里云Oss
     */
    public final static String accessKeyId = "PlQXvaWcQFfnraoC";
    public final static String accessKeySecret = "yHr0DLbxZ71BCu4KHADhGUKdZGMUMA";
    public final static String IMG_BUCKET_NAME = "tonghangimg";
    public final static String DOMAIN = "http://tonghangimg.oss-cn-shanghai.aliyuncs.com/";// 
    
    
    /**
     * 
     * 环信*/
 // API_HTTP_SCHEMA
    public static String API_HTTP_SCHEMA = PropertiesUtils.getProperties().getProperty("API_HTTP_SCHEMA");
    // API_SERVER_HOST
    public static String API_SERVER_HOST = PropertiesUtils.getProperties().getProperty("API_SERVER_HOST");
    // API_SERVER_PORT
    public static String API_SERVER_PORT = PropertiesUtils.getProperties().getProperty("API_SERVER_PORT");
    // APPKEY
    public static String APPKEY = PropertiesUtils.getProperties().getProperty("APPKEY");
    // APP_CLIENT_ID
    public static String APP_CLIENT_ID = PropertiesUtils.getProperties().getProperty("APP_CLIENT_ID");
    // APP_CLIENT_SECRET
    public static String APP_CLIENT_SECRET = PropertiesUtils.getProperties().getProperty("APP_CLIENT_SECRET");
    // APP_ADMIN_USERNAME
    public static String APP_ADMIN_USERNAME = PropertiesUtils.getProperties().getProperty("APP_ADMIN_USERNAME");
    // APP_ADMIN_PASSWORD
    public static String APP_ADMIN_PASSWORD = PropertiesUtils.getProperties().getProperty("APP_ADMIN_PASSWORD");
    // DEFAULT_PASSWORD
    public static String DEFAULT_PASSWORD = "1234456";
    public static String PASSWORD_KEY = PropertiesUtils.getProperties().getProperty("PASSWORD_KEY");
    
}
