package com.tonghang.server.common.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.tonghang.server.exception.ServiceException;

public class ResponseResult implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3790744519135413489L;

    public static String error(ServiceException e) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", e.getCode());
        result.put("msg", e.getMessage());

        return  JSON.toJSONString(result);
    }

    public static Object success(Object result) {
        return JSON.toJSONString(result);
    }

    public static Object success() {
        return "{\"code\":200,\"msg\":\"success\",\"data\":\"\"}";
    }
}
