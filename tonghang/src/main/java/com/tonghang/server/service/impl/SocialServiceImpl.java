package com.tonghang.server.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tonghang.server.entity.TCircle;
import com.tonghang.server.entity.TComment;
import com.tonghang.server.entity.TFriend;
import com.tonghang.server.entity.TNotification;
import com.tonghang.server.entity.TPhone;
import com.tonghang.server.exception.ErrorCode;
import com.tonghang.server.exception.ServiceException;
import com.tonghang.server.mapper.TCircleMapper;
import com.tonghang.server.mapper.TCommentMapper;
import com.tonghang.server.mapper.TFriendMapper;
import com.tonghang.server.mapper.TNotificationMapper;
import com.tonghang.server.mapper.TPhoneMapper;
import com.tonghang.server.util.NotificationTypeEnum;
import com.tonghang.server.util.OSSUtil;

@Service
public class SocialServiceImpl {

    @Autowired
    private TCircleMapper circleMapper;
    @Autowired
    private TPhoneMapper userMapper;

    @Autowired
    private TFriendMapper friendMap;
    @Autowired
    private TCommentMapper commentMap;
    @Autowired
    private TNotificationMapper notificationMapper;

    public Map<String, Object> publishSns(int userId, String txt,
            String pictures) throws ServiceException {
        TPhone user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            throw new ServiceException(ErrorCode.code101.getCode(),
                    ErrorCode.code101.getHttpCode(),
                    ErrorCode.code101.getDesc());
        }
        if (StringUtils.isNotBlank(pictures)) {
            String[] filepaths = pictures.split(",");
            pictures = "";
            for (String filepath : filepaths) {
                try {
                    filepath = OSSUtil.instance().uploadOss(filepath);
                } catch (IOException e) {
                    filepath = null;
                    throw new ServiceException(ErrorCode.code601.getCode(),
                            ErrorCode.code601.getHttpCode(),
                            ErrorCode.code601.getDesc());
                }
                pictures += filepath + ",";
            }
        }
        TCircle circle = new TCircle();
        circle.setContent(txt);
        circle.setPid(userId);
        circle.setType(1);
        circle.setDatetime(new Date());
        circle.setTradeId(user.getTradeId());
        circle.setPics(pictures);
        circle.setArea("");// TODO 地区需修改成省市id
        circleMapper.insert(circle);
        List<TCircle> circles = circleMapper.getMyCircles(user.getId());
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("circles", circles);
        return result;
    }

    public Object browseSns(int userId, String targetUserId, String pageNo,
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
        List<TCircle> circles = new ArrayList<TCircle>();
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
                circles = circleMapper.getMyCircles(targetUser.getId());
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
            circles = circleMapper.getFriendCircles(firendsId);
            // TODO 加用户信息
        }
        return circles;
    }

    public Map<String, Object> applyFriend(int userId, String targetUserId)
            throws ServiceException {

        if (StringUtils.isNoneBlank(targetUserId)
                && targetUserId.equals(userId)) {
            return null;
        }
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
        TNotification notification = new TNotification();
        notification.setPid(targetUser.getId());
        notification.setContent("有新的好友申请");
        notification.setTitle("好友通知");
        notification.setDatetime(new Date());
        notification.setType(NotificationTypeEnum.Friend.getCode());
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("friend",
                friendMap.isFriends(user.getId(), targetUser.getId()));
        return null;
    }

    public Object friends(int userId) throws ServiceException {
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
        return friends;
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

    public Map<String, Object> comment(int userId, Integer circleId, String txt,
            String commentId) throws ServiceException {
        TPhone user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            throw new ServiceException(ErrorCode.code101.getCode(),
                    ErrorCode.code101.getHttpCode(),
                    ErrorCode.code101.getDesc());
        }
        Map<String, Object> result = new HashMap<String, Object>();
        TCircle circle = circleMapper.selectByPrimaryKey(circleId);
        if (circle == null) {
            throw new ServiceException(ErrorCode.code300);
        }
        TComment comment = new TComment();
        comment.setCircleId(circleId);
        comment.setContent(txt);
        comment.setPidId(user.getId());
        comment.setDatetime(new Date());
        if (StringUtils.isNoneBlank(commentId)
                && StringUtils.isNumeric(commentId)) {
            comment.setReplyId(Integer.valueOf(commentId));

            TNotification notification = new TNotification();
            notification.setContent(txt);
            notification.setDatetime(new Date());
            notification.setContentId(circleId);
            notification.setPid(commentMap
                    .selectByPrimaryKey(Integer.valueOf(commentId)).getPidId());
            notification.setTitle("有人评论你的评论");
            notification.setType("1");
        }
        commentMap.insert(comment);
        Integer pid = circleMapper.selectByPrimaryKey(circleId).getPid();
        TNotification notification = new TNotification();
        notification.setContent(txt);
        notification.setDatetime(new Date());
        notification.setContentId(circleId);
        notification.setPid(pid);
        notification.setTitle("你的同行圈收到评论");
        notification.setType("1");
        notificationMapper.insert(notification);
        List<TComment> comments = commentMap.selectByCircleId(circleId);
        result.put("comments", comments);
        return result;
    }

    public Object like(int userId, Integer circleId) throws ServiceException {
        TPhone user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            throw new ServiceException(ErrorCode.code101.getCode(),
                    ErrorCode.code101.getHttpCode(),
                    ErrorCode.code101.getDesc());
        }
        Map<String, Object> result = new HashMap<String, Object>();
        TCircle circle = circleMapper.selectByPrimaryKey(circleId);
        if (circle == null) {
            throw new ServiceException(ErrorCode.code300);
        }
        circle.setFavour(circle.getFavour() + 1);
        return "success";
    }

    public Object confirmFriend(int userId, String targetUserId)
            throws ServiceException {
        TPhone user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            throw new ServiceException(ErrorCode.code101.getCode(),
                    ErrorCode.code101.getHttpCode(),
                    ErrorCode.code101.getDesc());
        }
        TFriend friend = friendMap.friendNotConfirm(user.getId(),
                Integer.valueOf(targetUserId));
        if (friend != null) {
            friend.setConfirm(1);
            friendMap.updateByPrimaryKey(friend);
        } else {
            throw new ServiceException(ErrorCode.code22);
        }
        return friend;
    }

}
