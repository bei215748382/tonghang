package com.tonghang.server.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tonghang.server.common.dto.TCircleDTO;
import com.tonghang.server.common.dto.TCommentDTO;
import com.tonghang.server.common.dto.TFriendDTO;
import com.tonghang.server.entity.TCircle;
import com.tonghang.server.entity.TComment;
import com.tonghang.server.entity.TFriend;
import com.tonghang.server.entity.TNotification;
import com.tonghang.server.entity.TPhone;
import com.tonghang.server.entity.TTrade;
import com.tonghang.server.exception.ErrorCode;
import com.tonghang.server.exception.ServiceException;
import com.tonghang.server.mapper.TCircleMapper;
import com.tonghang.server.mapper.TCommentMapper;
import com.tonghang.server.mapper.TFriendMapper;
import com.tonghang.server.mapper.TNotificationMapper;
import com.tonghang.server.mapper.TPhoneMapper;
import com.tonghang.server.mapper.TTradeMapper;
import com.tonghang.server.util.NotificationTypeEnum;
import com.tonghang.server.util.OSSUtil;

@Service
public class SocialServiceImpl {

    @Autowired
    private TCircleMapper circleMapper;
    @Autowired
    private TPhoneMapper userMapper;

    @Autowired
    private TFriendMapper friendMapper;
    @Autowired
    private TCommentMapper commentMapper;
    @Autowired
    private TTradeMapper tradeMapper;
    @Autowired
    private TNotificationMapper notificationMapper;
    @Autowired
    private UserService userService;

    public Object publishSns(int userId, String txt, String pictures)
            throws ServiceException {
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
                    filepath = OSSUtil.instance().uploadOss(filepath,
                            String.valueOf(userId));
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
        circle.setComment(0);
        circle.setPics(pictures);
        circle.setArea(user.getCity());
        circleMapper.insert(circle);
        List<TCircle> circles = circleMapper.getMyCircles(user.getId());
        List<TCircleDTO> result = new ArrayList<TCircleDTO>();
        for (TCircle bean : circles) {
            TPhone u = userMapper.getUserInfoById(bean.getPid());
            TTrade trade = tradeMapper.selectByPrimaryKey(bean.getTradeId());
            List<TCommentDTO> commentdto = new ArrayList<TCommentDTO>();
            if (bean.getComment() != 0) {
                List<TComment> comments = commentMapper
                        .selectByCircleId(bean.getId());
                if (CollectionUtils.isNotEmpty(comments)) {
                    for (TComment comment : comments) {
                        TPhone userinfo = userMapper
                                .getUserInfoById(comment.getPidId());
                        commentdto.add(new TCommentDTO(comment, userinfo));
                    }
                }
            }
            result.add(TCircleDTO.builder(bean, u, commentdto, trade));

        }

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
        if (StringUtils.isBlank(pageSize) || StringUtils.isNumeric(pageSize)
                || Integer.valueOf(pageSize) <= 0) {
            pageSize = "10";
        }
        if (StringUtils.isBlank(pageNo) || StringUtils.isNumeric(pageNo)
                || Integer.valueOf(pageNo) <= 0) {
            pageNo = "0";
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
            circles = circleMapper.getMyCircles(targetUser.getId());
            // TFriend friend = friendMapper.isFriends(user.getId(),
            // targetUser.getId());
            // if (friend != null) {
            // circles = circleMapper.getMyCircles(targetUser.getId());
            // } else {
            // throw new ServiceException(ErrorCode.code118.getCode(),
            // ErrorCode.code118.getHttpCode(),
            // ErrorCode.code118.getDesc());
            // }
        } else {
            // List<Integer> firendsId = friendMapper
            // .selectAllFriendsId(user.getId());
            // if (firendsId == null) {
            // firendsId = new ArrayList<Integer>();
            // }
            // circles = circleMapper.getFriendCircles(firendsId);
            circles = circleMapper.getAllCircleShow(Integer.valueOf(pageNo),
                    Integer.valueOf(pageSize));
        }
        List<TCircleDTO> result = new ArrayList<TCircleDTO>();
        for (TCircle circle : circles) {
            TPhone u = userMapper.getUserInfoById(circle.getPid());
            TTrade trade = tradeMapper.selectByPrimaryKey(circle.getTradeId());
            List<TCommentDTO> commentdto = new ArrayList<TCommentDTO>();
            if (circle.getComment() != 0) {
                List<TComment> comments = commentMapper
                        .selectByCircleId(circle.getId());
                if (CollectionUtils.isNotEmpty(comments)) {
                    for (TComment bean : comments) {
                        TPhone userinfo = userMapper
                                .getUserInfoById(bean.getPidId());
                        commentdto.add(new TCommentDTO(bean, userinfo));
                    }
                }
            }
            result.add(TCircleDTO.builder(circle, u, commentdto, trade));

        }
        return result;
    }

    public Object applyFriend(int userId, String targetUserId)
            throws ServiceException {
        TPhone user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            throw new ServiceException(ErrorCode.code101.getCode(),
                    ErrorCode.code101.getHttpCode(),
                    ErrorCode.code101.getDesc());
        }
        if (StringUtils.isNotBlank(targetUserId)
                && StringUtils.isNumeric(targetUserId)) {
            TPhone targetUser = userMapper
                    .selectByPrimaryKey(Integer.valueOf(targetUserId));
            if (targetUser == null) {
                throw new ServiceException(ErrorCode.code101.getCode(),
                        ErrorCode.code101.getHttpCode(),
                        "user  been  look up  not   exist");
            }
            if (targetUser.getId() != user.getId()) {
                TFriend friend = new TFriend();
                friend.setPid(user.getId());
                friend.setFid(targetUser.getId());
                friendMapper.insert(friend);
                TNotification notification = new TNotification();
                notification.setPid(targetUser.getId());
                notification.setContent("有新的好友申请");
                notification.setTitle("好友通知");
                notification.setDatetime(new Date());
                notification.setType(NotificationTypeEnum.Friend.getCode());
            }
        }
        List<TFriend> friends = friendMapper.selectApplyNotConfirm(userId);
        List<TFriendDTO> friendresult = new ArrayList<TFriendDTO>();
        for (TFriend bean : friends) {
            TPhone userinfo = userMapper.getUserInfoById(bean.getFid());
            friendresult.add(new TFriendDTO(bean, userinfo));
        }
        return friendresult;
    }

    public Object friends(int userId) throws ServiceException {
        TPhone user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            throw new ServiceException(ErrorCode.code101.getCode(),
                    ErrorCode.code101.getHttpCode(),
                    ErrorCode.code101.getDesc());
        }
        List<Integer> friendsId = friendMapper.selectAllFriendsId(user.getId());
        if (friendsId == null) {
            friendsId = new ArrayList<Integer>();
        }
        List<TPhone> friends = userMapper.selectByIds(friendsId);
        return friends;
    }

    public Object guessLike(int userId, String pageNo, String pageSize)
            throws ServiceException {
        TPhone user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            throw new ServiceException(ErrorCode.code101.getCode(),
                    ErrorCode.code101.getHttpCode(),
                    ErrorCode.code101.getDesc());
        }
        if (StringUtils.isBlank(pageSize) || StringUtils.isNumeric(pageSize)
                || Integer.valueOf(pageSize) <= 0) {
            pageSize = "10";
        }
        if (StringUtils.isBlank(pageNo) || StringUtils.isNumeric(pageNo)
                || Integer.valueOf(pageNo) <= 0) {
            pageNo = "0";
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

        List<TCircleDTO> result = new ArrayList<TCircleDTO>();
        for (TCircle circle : circles) {
            TPhone u = userMapper.getUserInfoById(circle.getPid());
            TTrade trade = tradeMapper.selectByPrimaryKey(circle.getTradeId());
            List<TComment> comments = commentMapper
                    .selectByCircleId(circle.getId());
            List<TCommentDTO> commentdto = new ArrayList<TCommentDTO>();
            if (CollectionUtils.isNotEmpty(comments)) {
                for (TComment bean : comments) {
                    TPhone userinfo = userMapper
                            .getUserInfoById(bean.getPidId());
                    commentdto.add(new TCommentDTO(bean, userinfo));
                }
            }
            result.add(TCircleDTO.builder(circle, u, commentdto, trade));

        }
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

    public Object comment(int userId, Integer circleId, String txt,
            String commentId) throws ServiceException {
        TPhone user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            throw new ServiceException(ErrorCode.code101.getCode(),
                    ErrorCode.code101.getHttpCode(),
                    ErrorCode.code101.getDesc());
        }
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
            notification.setPid(commentMapper
                    .selectByPrimaryKey(Integer.valueOf(commentId)).getPidId());
            notification.setTitle("有人评论你的评论");
            notification.setType("1");
        }
        commentMapper.insert(comment);
        Integer pid = circleMapper.selectByPrimaryKey(circleId).getPid();
        TNotification notification = new TNotification();
        notification.setContent(txt);
        notification.setDatetime(new Date());
        notification.setContentId(circleId);
        notification.setPid(pid);
        notification.setTitle("你的同行圈收到评论");
        notification.setType("1");
        notificationMapper.insert(notification);
        circle.setComment(circle.getComment() + 1);// TODO 存在数据库并发隔离性问题
                                                   // 建议改成查询comment数量
        circleMapper.updateByPrimaryKey(circle);
        List<TComment> comments = commentMapper.selectByCircleId(circleId);
        List<TCommentDTO> commentdto = new ArrayList<TCommentDTO>();
        if (CollectionUtils.isNotEmpty(comments)) {
            for (TComment bean : comments) {
                TPhone userinfo = userMapper.getUserInfoById(bean.getPidId());
                commentdto.add(new TCommentDTO(bean, userinfo));
            }
        }
        return commentdto;
    }

    public Object like(int userId, Integer circleId) throws ServiceException {
        TPhone user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            throw new ServiceException(ErrorCode.code101.getCode(),
                    ErrorCode.code101.getHttpCode(),
                    ErrorCode.code101.getDesc());
        }
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
        TFriend friend = friendMapper.friendNotConfirm(user.getId(),
                Integer.valueOf(targetUserId));
        if (friend != null) {
            friend.setConfirm(1);
            friendMapper.updateByPrimaryKey(friend);
        } else {
            throw new ServiceException(ErrorCode.code22);
        }
        TPhone userinfo = userMapper.getUserInfoById(friend.getFid());
        return new TFriendDTO(friend, userinfo);
    }

    public Object homepage(String userId, String targetUserId)
            throws ServiceException {
        TPhone user = userMapper.selectByPrimaryKey(Integer.valueOf(userId));
        if (user == null) {
            throw new ServiceException(ErrorCode.code101.getCode(),
                    ErrorCode.code101.getHttpCode(),
                    ErrorCode.code101.getDesc());
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("user", userService.getInfo(userId));
        result.put("service",
                userService.getService(Integer.valueOf(userId), targetUserId));
        List<TCircle> circles = circleMapper
                .getMyCircles(Integer.valueOf(targetUserId));
        if (circles != null && circles.size() > 0) {
            result.put("circle", new TCircleDTO(circles.get(0)));
        }
        return result;
    }

    /**
     * 好友申请列表
     * 
     * @param userId
     * @return
     * @throws ServiceException
     */
    public Object friendApply(String userId) throws ServiceException {
        TPhone user = userMapper.selectByPrimaryKey(Integer.valueOf(userId));
        if (user == null) {
            throw new ServiceException(ErrorCode.code101.getCode(),
                    ErrorCode.code101.getHttpCode(),
                    ErrorCode.code101.getDesc());
        }
        List<TFriend> friends = friendMapper
                .selectBeenApplyNotConfirm(Integer.valueOf(userId));
        List<TFriendDTO> friendresult = new ArrayList<TFriendDTO>();
        for (TFriend bean : friends) {
            TPhone userinfo = userMapper.getUserInfoById(bean.getFid());
            friendresult.add(new TFriendDTO(bean, userinfo));
        }
        return friendresult;
    }

}
