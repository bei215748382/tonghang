package com.tonghang.server.common.dto;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.tonghang.server.exception.ServiceException;

public class ResponseResult implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3790744519135413489L;

    public static String error(String message) {
        return "{\"result\":false,\"data\":" + message + "}";
    }

    public static String error(ServiceException e) {
        return error(e.getMessage());
    }

    public static Object success(Object result) {
        return JSON.toJSONString(result);
    }

    public static Object success() {
        return "{\"result\":true}";
    }
}
