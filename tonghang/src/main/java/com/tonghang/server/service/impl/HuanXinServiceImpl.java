package com.tonghang.server.service.impl;

import java.net.URL;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.tonghang.server.common.HTTPMethod;
import com.tonghang.server.common.vo.Credentail;
import com.tonghang.server.common.vo.EaseUser;
import com.tonghang.server.common.vo.Token;
import com.tonghang.server.common.vo.TokenCredentail;
import com.tonghang.server.exception.ErrorCode;
import com.tonghang.server.exception.ServiceException;
import com.tonghang.server.util.Constants;
import com.tonghang.server.util.HTTPClientUtils;
import com.tonghang.server.util.MD5;


@Service
public class HuanXinServiceImpl {

    private static Logger log = LoggerFactory
            .getLogger(HuanXinServiceImpl.class);

    private static JsonNodeFactory factory = new JsonNodeFactory(false);

    public String getUser(String username) throws ServiceException {

        
        
        
        URL tokenUrl = HTTPClientUtils
                .getURL(Constants.APPKEY.replace("#", "/") + "/token");
        ObjectNode dataNode = factory.objectNode();
        dataNode.put("grant_type", "client_credentials");
        dataNode.put("client_id", Constants.APP_CLIENT_ID);
        dataNode.put("client_secret", Constants.APP_CLIENT_SECRET);
        ObjectNode resultNode = HTTPClientUtils.sendHTTPRequest(tokenUrl, null,
                dataNode, HTTPMethod.METHOD_POST);
        String jsonStr = resultNode.toString();
        log.info("getToken jsonString: " + jsonStr);
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        if (!jsonObject.containsKey("access_token")) {
            log.error(jsonObject.toJSONString());
            throw new ServiceException(ErrorCode.code880);
        }
        String token = jsonObject.getString("access_token");
        URL userUrl = HTTPClientUtils
                .getURL(Constants.APPKEY.replace("#", "/") + "/users/" + username);
        String password = MD5
                .getMD5(MD5.getMD5(username) + Constants.PASSWORD_KEY);
        Token creditToken = new Token(token);
        Credentail credentail = new TokenCredentail(creditToken);
        resultNode =null;
        try {
             resultNode = HTTPClientUtils.sendHTTPRequest(userUrl, credentail, null,
                    HTTPMethod.METHOD_GET);
            jsonStr = resultNode.toString();
            jsonObject = JSON.parseObject(jsonStr);
            if (!jsonObject.containsKey("entities")) {
                return createUser(username);
            }
            List<EaseUser> users =
                    JSONArray.parseArray(jsonObject.getString("entities"), EaseUser.class);
            if (users != null && users.size() > 0) {
                return password;
            }
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.code880);
        }
        return null;
        
        
        
        
        
    }

    public String createUser(String username) throws ServiceException {

        URL tokenUrl = HTTPClientUtils
                .getURL(Constants.APPKEY.replace("#", "/") + "/token");
        ObjectNode dataNode = factory.objectNode();
        dataNode.put("grant_type", "client_credentials");
        dataNode.put("client_id", Constants.APP_CLIENT_ID);
        dataNode.put("client_secret", Constants.APP_CLIENT_SECRET);
        ObjectNode resultNode = HTTPClientUtils.sendHTTPRequest(tokenUrl, null,
                dataNode, HTTPMethod.METHOD_POST);
        String jsonStr = resultNode.toString();
        log.info("getToken jsonString: " + jsonStr);
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        if (!jsonObject.containsKey("access_token")) {
            log.error(jsonObject.toJSONString());
            throw new ServiceException(ErrorCode.code880);
        }
        String token = jsonObject.getString("access_token");

        URL userUrl = HTTPClientUtils
                .getURL(Constants.APPKEY.replace("#", "/") + "/users");
        String password = MD5
                .getMD5(MD5.getMD5(username) + Constants.PASSWORD_KEY);
        dataNode = null;
        dataNode = factory.objectNode();
        dataNode.put("username", username);
        dataNode.put("password", password);
        Token creditToken = new Token(token);
        Credentail credentail = new TokenCredentail(creditToken);
        resultNode = null;
        jsonObject = null;
        try {
            resultNode = HTTPClientUtils.sendHTTPRequest(userUrl, credentail,
                    dataNode, HTTPMethod.METHOD_POST);
            jsonObject = JSON.parseObject(resultNode.toString());
            if (!jsonObject.containsKey("entities")) {
                throw new ServiceException(ErrorCode.code880);
            } else {
                List<EaseUser> easeUsers = JSONArray.parseArray(
                        jsonObject.getString("entities"), EaseUser.class);
                if (easeUsers != null && easeUsers.size() > 0) {
                    return password;
                } else {
                    throw new ServiceException(ErrorCode.code880);
                }
            }
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.code880);
        }
    }

}
