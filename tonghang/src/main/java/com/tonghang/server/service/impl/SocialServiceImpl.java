package com.tonghang.server.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.model.ObjectMetadata;
import com.tonghang.server.entity.TCircle;
import com.tonghang.server.entity.TFriend;
import com.tonghang.server.entity.TPhone;
import com.tonghang.server.exception.ErrorCode;
import com.tonghang.server.exception.ServiceException;
import com.tonghang.server.mapper.TCircleMapper;
import com.tonghang.server.mapper.TFriendMapper;
import com.tonghang.server.mapper.TPhoneMapper;
import com.tonghang.server.util.OSSUtil;

@Service
public class SocialServiceImpl {

    @Autowired
    private TCircleMapper circleMapper;
    @Autowired
    private TPhoneMapper userMapper;

    @Autowired
    private TFriendMapper friendMap;

    public Map<String, Object> publishSns(int userId, String txt,
            MultipartFile[] pictures) throws ServiceException {
        TPhone user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            throw new ServiceException(ErrorCode.code101.getCode(),
                    ErrorCode.code101.getHttpCode(),
                    ErrorCode.code101.getDesc());
        }
        String pics = "";
        if (pictures != null && pictures.length > 0) {
            for (int i = 0; i < pictures.length; i++) {
                ObjectMetadata metadata = new ObjectMetadata();
                String filepath = "icon" + File.separatorChar + userId
                        + File.separatorChar + pictures[i].getName();
                try {
                    metadata.setContentLength(pictures[i].getBytes().length);
                    filepath = OSSUtil.instance().uploadOss(
                            pictures[i].getInputStream(), metadata, filepath);
                } catch (IOException e) {
                    filepath = null;
                    throw new ServiceException(ErrorCode.code601.getCode(),
                            ErrorCode.code601.getHttpCode(),
                            ErrorCode.code601.getDesc());
                }
                pics += filepath + ",";
            }
        }
        TCircle circle = new TCircle();
        circle.setContent(txt);
        circle.setPid(userId);
        circle.setType(1);
        circle.setDatetime(new Date());
        circle.setTradeId(user.getTradeId());
        circle.setPics(pics);
        circle.setArea("");// TODO 地区需修改成省市id
        circleMapper.insert(circle);
        List<TCircle> circles = circleMapper.getMyCircles(user.getId());
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("circles", circles);
        return result;
    }

    public Map<String, Object> browseSns(int userId, String targetUserId,
            String pageNo, String pageSize) throws ServiceException {
        TPhone user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            throw new ServiceException(ErrorCode.code101.getCode(),
                    ErrorCode.code101.getHttpCode(),
                    ErrorCode.code101.getDesc());
        }
        Map<String, Object> result = new HashMap<String, Object>();
        if (StringUtils.isBlank(pageSize) || StringUtils.isNumeric(pageSize)
                || Integer.valueOf(pageSize) <= 0) {
            pageSize = "10";
        }
        if (StringUtils.isBlank(pageNo) || StringUtils.isNumeric(pageNo)
                || Integer.valueOf(pageNo) <= 0) {
            pageNo = "1";
        }
        if (StringUtils.isNotBlank(targetUserId)) {
            TPhone targetUser = userMapper
                    .selectByPrimaryKey(Integer.valueOf(targetUserId));
            if (targetUser == null) {
                throw new ServiceException(ErrorCode.code101.getCode(),
                        ErrorCode.code101.getHttpCode(),
                        "user  been  look up  not   exist");
            }

            TFriend friend = friendMap.isFriends(user.getId(),
                    targetUser.getId());
            if (friend != null) {
                List<TCircle> circles = circleMapper
                        .getMyCircles(targetUser.getId());
                result.put("sns", circles);
            } else {
                throw new ServiceException(ErrorCode.code118.getCode(),
                        ErrorCode.code118.getHttpCode(),
                        ErrorCode.code118.getDesc());
            }
        } else {
            List<Integer> firendsId = friendMap
                    .selectAllFriendsId(user.getId());
            if (firendsId == null) {
                firendsId = new ArrayList<Integer>();
            }
            List<TCircle> circles = circleMapper.getFriendCircles(firendsId);
            result.put("sns", circles);
        }
        return result;
    }

    public Map<String, Object> applyFriend(int userId, String targetUserId)
            throws ServiceException {

        TPhone user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            throw new ServiceException(ErrorCode.code101.getCode(),
                    ErrorCode.code101.getHttpCode(),
                    ErrorCode.code101.getDesc());
        }
        TPhone targetUser = userMapper
                .selectByPrimaryKey(Integer.valueOf(targetUserId));
        if (targetUser == null) {
            throw new ServiceException(ErrorCode.code101.getCode(),
                    ErrorCode.code101.getHttpCode(),
                    "user  been  look up  not   exist");
        }

        TFriend friend = new TFriend();
        friend.setPid(user.getId());
        friend.setFid(targetUser.getId());
        friendMap.insert(friend);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("friend",
                friendMap.isFriends(user.getId(), targetUser.getId()));
        return result;
    }

    public Map<String, Object> friends(int userId) throws ServiceException {
        TPhone user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            throw new ServiceException(ErrorCode.code101.getCode(),
                    ErrorCode.code101.getHttpCode(),
                    ErrorCode.code101.getDesc());
        }
        List<Integer> friendsId = friendMap.selectAllFriendsId(user.getId());
        if (friendsId == null) {
            friendsId = new ArrayList<Integer>();
        }
        List<TPhone> friends = userMapper.selectByIds(friendsId);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("friends", friends);
        return result;
    }

    public Map<String, Object> browseLikeSns(int userId, String pageNo,
            String pageSize) throws ServiceException {
        TPhone user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            throw new ServiceException(ErrorCode.code101.getCode(),
                    ErrorCode.code101.getHttpCode(),
                    ErrorCode.code101.getDesc());
        }
        Map<String, Object> result = new HashMap<String, Object>();
        if (StringUtils.isBlank(pageSize) || StringUtils.isNumeric(pageSize)
                || Integer.valueOf(pageSize) <= 0) {
            pageSize = "10";
        }
        if (StringUtils.isBlank(pageNo) || StringUtils.isNumeric(pageNo)
                || Integer.valueOf(pageNo) <= 0) {
            pageNo = "1";
        }
        if ((user.getCityId() == null || user.getCityId() == 0)
                && (user.getTradeId() == null || user.getTradeId() == 0)) {
            throw new ServiceException(ErrorCode.code119.getCode(),
                    ErrorCode.code119.getHttpCode(),
                    ErrorCode.code119.getDesc());
        }
        List<Integer> usersId = userMapper
                .getUserIdByCityAndTrade(user.getCityId(), user.getTradeId());
        if (usersId == null) {
            usersId = new ArrayList<Integer>();
        }
        List<TCircle> circles = circleMapper.getFriendCircles(usersId);
        result.put("sns", circles);
        return result;
    }

    public Map<String, Object> browseArticle(int userId, String tradeId,
            String pageSize, String pageNo) throws ServiceException {
        TPhone user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            throw new ServiceException(ErrorCode.code101.getCode(),
                    ErrorCode.code101.getHttpCode(),
                    ErrorCode.code101.getDesc());
        }
        Map<String, Object> result = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(tradeId) || StringUtils.isNumeric(tradeId)) {
            result.put("articles",
                    circleMapper.getTradeArticles(Integer.valueOf(tradeId)));
        } else {
            result.put("articles", circleMapper.getArticles());
        }
        return result;
    }

}
