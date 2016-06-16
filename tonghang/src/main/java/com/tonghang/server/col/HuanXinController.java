package com.tonghang.server.col;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.tonghang.server.common.HTTPMethod;
import com.tonghang.server.common.dto.BasicRequestDTO;
import com.tonghang.server.common.vo.Credentail;
import com.tonghang.server.common.vo.EaseUser;
import com.tonghang.server.common.vo.Token;
import com.tonghang.server.common.vo.TokenCredentail;
import com.tonghang.server.entity.im.EasemobIMUsers;
import com.tonghang.server.exception.ErrorCode;
import com.tonghang.server.exception.ServiceException;
import com.tonghang.server.util.Constants;
import com.tonghang.server.util.HTTPClientUtils;
import com.tonghang.server.util.MD5;

@Controller
@RequestMapping(value = "/api/im")
public class HuanXinController extends AppBaseController {

    private Logger log = LoggerFactory.getLogger(HuanXinController.class);
    private static JsonNodeFactory factory = new JsonNodeFactory(false);

    @RequestMapping(value = "/getuser", method = { RequestMethod.POST,
            RequestMethod.GET })
    public @ResponseBody Object getUser(HttpServletRequest request,
            HttpServletResponse response) throws ServiceException {
        // 用户auid兼容大小写
        BasicRequestDTO baseRequest = (BasicRequestDTO) request
                .getAttribute("requestDTO");
        checkUserLogin(baseRequest);
        String content = baseRequest.getContent();
        Map<String, String> params = (Map<String, String>) JSON.parse(content);
        checkParams(params);
        String username = params.get("username");
        String password = MD5
                .getMD5(MD5.getMD5(username) + Constants.PASSWORD_KEY);
        String jsonStr = EasemobIMUsers.getIMUsersByPrimaryKey(username)
                .toString();
        log.info("getIMUsersByPrimaryKey return result:" + jsonStr);
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        if (!jsonObject.containsKey("entities")) {
            ObjectNode dataNode = JsonNodeFactory.instance.objectNode();
            dataNode.put("username", username);
            dataNode.put("password", password);
            jsonStr = EasemobIMUsers.createNewIMUserSingle(dataNode).toString();
            log.info("createNewIMUserSingle return result:" + jsonStr);
            jsonObject = JSON.parseObject(jsonStr);
        }
        if (!jsonObject.containsKey("entities")) {
            throw new ServiceException(ErrorCode.code880);

        }
        List<EaseUser> users = JSONArray
                .parseArray(jsonObject.getString("entities"), EaseUser.class);
        if (users != null && users.size() > 0) {
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("code", 200);
            result.put("msg", "success");
            Map<String, String> map = new HashMap<String, String>();
            map.put("username", username);
            map.put("password", password);
            result.put("data", map);
            return JSON.toJSONString(result);
        }
        throw new ServiceException(ErrorCode.code880);
    }

    @RequestMapping(value = "/createuser", method = { RequestMethod.POST,
            RequestMethod.PUT })
    public @ResponseBody Object createUserSingle(HttpServletRequest request,
            HttpServletResponse response) throws ServiceException {
        BasicRequestDTO baseRequest = (BasicRequestDTO) request
                .getAttribute("requestDTO");
        checkUserLogin(baseRequest);
        String content = baseRequest.getContent();
        Map<String, String> params = (Map<String, String>) JSON.parse(content);
        checkParams(params);
        String username = params.get("username");

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
                    Map<String, Object> result = new HashMap<String, Object>();
                    result.put("code", 200);
                    result.put("msg", "success");
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("username", username);
                    map.put("password", password);
                    result.put("data", map);
                    return JSON.toJSONString(result);
                } else {
                    throw new ServiceException(ErrorCode.code880);
                }
            }
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.code880);
        }
    }

}
