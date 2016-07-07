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
import com.tonghang.server.common.dto.TFavoriteDTO;
import com.tonghang.server.common.dto.TFriendDTO;
import com.tonghang.server.common.dto.TUserDTO;
import com.tonghang.server.entity.TCircle;
import com.tonghang.server.entity.TCircleLike;
import com.tonghang.server.entity.TCircleSeen;
import com.tonghang.server.entity.TCity;
import com.tonghang.server.entity.TComment;
import com.tonghang.server.entity.TFavorite;
import com.tonghang.server.entity.TFriend;
import com.tonghang.server.entity.TNotification;
import com.tonghang.server.entity.TPhone;
import com.tonghang.server.entity.TProvince;
import com.tonghang.server.entity.TTrade;
import com.tonghang.server.exception.ErrorCode;
import com.tonghang.server.exception.ServiceException;
import com.tonghang.server.mapper.TCircleLikeMapper;
import com.tonghang.server.mapper.TCircleMapper;
import com.tonghang.server.mapper.TCircleSeenMapper;
import com.tonghang.server.mapper.TCityMapper;
import com.tonghang.server.mapper.TCommentMapper;
import com.tonghang.server.mapper.TFavoriteMapper;
import com.tonghang.server.mapper.TFriendMapper;
import com.tonghang.server.mapper.TNotificationMapper;
import com.tonghang.server.mapper.TPhoneMapper;
import com.tonghang.server.mapper.TProvinceMapper;
import com.tonghang.server.mapper.TTradeMapper;
import com.tonghang.server.util.NotificationTypeEnum;
import com.tonghang.server.util.OSSUtil;
import com.tonghang.server.vo.ArticlesVo;

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
    @Autowired
    private TCircleLikeMapper likeMapper;
    @Autowired
    private TFavoriteMapper favoriteMapper;
    @Autowired
    private TProvinceMapper provinceMapper;
    @Autowired
    private TCityMapper cityMapper;
    @Autowired
    private TCircleSeenMapper seenMapper;

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
            if (u.getCityId() != null && u.getProvinceId() != null) {
                TProvince province = provinceMapper
                        .selectByPrimaryKey(u.getProvinceId());
                TCity city = cityMapper.selectByPrimaryKey(u.getCityId());
                if (StringUtils.isNotBlank(user.getLanguage())
                        && "en_US".equals(user.getLanguage())
                        && province != null && city != null) {
                    user.setProvince(province.getEnName());
                    user.setCity(city.getEnName());
                } else {
                    user.setProvince(province.getName());
                    user.setCity(city.getName());
                }
            }
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
            result.add(TCircleDTO.builder(bean, u, commentdto, trade, null));

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
        } else {
            circles = circleMapper.getAllCircleShow(Integer.valueOf(pageNo),
                    Integer.valueOf(pageSize));
        }
        List<TCircleDTO> result = new ArrayList<TCircleDTO>();
        for (TCircle circle : circles) {
            TPhone u = userMapper.getUserInfoById(circle.getPid());
            if (u.getCityId() != null && u.getProvinceId() != null) {
                TProvince province = provinceMapper
                        .selectByPrimaryKey(u.getProvinceId());
                TCity city = cityMapper.selectByPrimaryKey(u.getCityId());
                if (StringUtils.isNotBlank(user.getLanguage())
                        && "en_US".equals(user.getLanguage())
                        && province != null && city != null) {
                    user.setProvince(province.getEnName());
                    user.setCity(city.getEnName());
                } else {
                    user.setProvince(province.getName());
                    user.setCity(city.getName());
                }
            }
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
            List<Integer> userids = likeMapper
                    .selectAllLikePidByCircleId(circle.getId());
            List<TPhone> userinfos = userMapper.selectByIds(userids);
            TCircleDTO dto = TCircleDTO.builder(circle, u, commentdto, trade,
                    userinfos);
            TCircleLike like = likeMapper.selectByCircleIdAndPid(circle.getId(),
                    user.getId());
            if (like == null) {
                dto.setLike(false);
            } else {
                dto.setLike(true);
            }
            result.add(dto);

        }
        return result;
    }

    public TCircleDTO getCircleInfoById(int id) throws ServiceException {
        TCircle circle = circleMapper.selectByPrimaryKey(id);
        if (circle == null) {
            throw new ServiceException(ErrorCode.code200);
        }
        circle.setPageView(circle.getPageView()+1);
        circleMapper.updateByPrimaryKey(circle);
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
        List<Integer> userids = likeMapper
                .selectAllLikePidByCircleId(circle.getId());
        List<TPhone> userinfos = userMapper.selectByIds(userids);
        TCircleDTO dto = TCircleDTO.builder(circle, u, commentdto, trade,
                userinfos);

        return dto;
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
            TFriend friend = friendMapper.isFriends(userId,
                    Integer.valueOf(targetUserId));
            if (friend == null) {
                if (targetUser.getId() != user.getId()) {
                    friend = new TFriend();
                    friend.setPid(user.getId());
                    friend.setFid(targetUser.getId());
                    friend.setConfirm(0);
                    friendMapper.insert(friend);
                }
            }
        }
        List<TFriend> friends = friendMapper.selectApplyNotConfirm(userId);
        List<TFriendDTO> friendresult = new ArrayList<TFriendDTO>();
        for (TFriend bean : friends) {
            TPhone userinfo = userMapper.getUserInfoById(bean.getFid());
            if (userinfo.getCityId() != null
                    && userinfo.getProvinceId() != null) {
                TProvince province = provinceMapper
                        .selectByPrimaryKey(userinfo.getProvinceId());
                TCity city = cityMapper
                        .selectByPrimaryKey(userinfo.getCityId());
                if (StringUtils.isNotBlank(user.getLanguage())
                        && "en_US".equals(user.getLanguage())
                        && province != null && city != null) {
                    user.setProvince(province.getEnName());
                    user.setCity(city.getEnName());
                } else {
                    user.setProvince(province.getName());
                    user.setCity(city.getName());
                }
            }
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

    public Object recommend(int userId, String pageNo, String pageSize,
            String tradeId) throws ServiceException {
        TPhone user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            throw new ServiceException(ErrorCode.code101.getCode(),
                    ErrorCode.code101.getHttpCode(),
                    ErrorCode.code101.getDesc());
        }
        if (StringUtils.isBlank(pageSize) || !StringUtils.isNumeric(pageSize)
                || Integer.valueOf(pageSize) <= 0) {
            pageSize = "10";
        }
        if (StringUtils.isBlank(pageNo) || !StringUtils.isNumeric(pageNo)
                || Integer.valueOf(pageNo) <= 0) {
            pageNo = "0";
        }
        Integer id = null;
        if (StringUtils.isNotBlank(tradeId) && StringUtils.isNumeric(tradeId)) {
            id = Integer.valueOf(tradeId);
        }
        Map<String, Object> result = new HashMap<String, Object>();
        List<TPhone> users = userMapper.selectNewUsersId(id);
        List<TUserDTO> newUsers = new ArrayList<TUserDTO>();
        if (CollectionUtils.isNotEmpty(users)) {
            for (TPhone bean : users) {
                TTrade trade = tradeMapper
                        .selectByPrimaryKey(bean.getTradeId());
                List<TCircle> service = circleMapper
                        .getServicesByUserId(bean.getId());
                TProvince province = provinceMapper
                        .selectByPrimaryKey(bean.getProvinceId());
                TCity city = cityMapper.selectByPrimaryKey(bean.getCityId());
                newUsers.add(
                        new TUserDTO(user,
                                CollectionUtils.isEmpty(service) ? null
                                        : service.get(0),
                                null, trade, province, city));
            }
        }
        result.put("new", newUsers);
        users.clear();
        users = userMapper.selectActiveUsersId(Integer.valueOf(pageNo),
                Integer.valueOf(pageSize), id);
        List<TUserDTO> activeUsers = new ArrayList<TUserDTO>();
        if (CollectionUtils.isNotEmpty(users)) {
            for (TPhone bean : users) {
                TTrade trade = tradeMapper
                        .selectByPrimaryKey(bean.getTradeId());
                List<TCircle> service = circleMapper
                        .getServicesByUserId(bean.getId());
                TProvince province = provinceMapper
                        .selectByPrimaryKey(bean.getProvinceId());
                TCity city = cityMapper.selectByPrimaryKey(bean.getCityId());
                activeUsers
                        .add(new TUserDTO(user,
                                CollectionUtils.isEmpty(service) ? null
                                        : service.get(0),
                                null, trade, province, city));
            }
        }
        result.put("active", activeUsers);
        result.put("pageNo", pageNo);
        result.put("pageSize", pageSize);
        return result;

    }

    public Object browseArticles(int userId, String tradeId, String pageSize,
            String pageNo) throws ServiceException {
        TPhone user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            throw new ServiceException(ErrorCode.code101.getCode(),
                    ErrorCode.code101.getHttpCode(),
                    ErrorCode.code101.getDesc());
        }
        List<ArticlesVo> result = new ArrayList<ArticlesVo>();
        List<ArticlesVo> articles = new ArrayList<ArticlesVo>();
        if (StringUtils.isNotBlank(tradeId) || StringUtils.isNumeric(tradeId)) {
            articles = circleMapper.getTradeArticles(Integer.valueOf(tradeId));
        } else {
            articles = circleMapper.getArticles();
        }
        for (ArticlesVo bean : articles) {
            TCircleLike like = likeMapper.selectByCircleIdAndPid(bean.getId(),
                    userId);
            if (like != null) {
                bean.setLike(true);
            } else {
                bean.setLike(false);
            }
            result.add(bean);
        }
        return result;
    }

    public Object browseArticle(int userId, String id) throws ServiceException {
        TPhone user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            throw new ServiceException(ErrorCode.code101.getCode(),
                    ErrorCode.code101.getHttpCode(),
                    ErrorCode.code101.getDesc());
        }
        TCircle article = circleMapper.selectByPrimaryKey(Integer.valueOf(id));
        if (article == null) {
            throw new ServiceException(ErrorCode.code300);
        }
        article.setPageView(article.getPageView() + 1);
        circleMapper.updateByPrimaryKey(article);
        String content = article.getContent();
        content = content.substring(
                content.indexOf("<body>") + "<body>".length(),
                content.indexOf("</body>"));
        article.setContent(content);
        TCircleSeen seen = seenMapper.selectByUserAndCircle(user.getId(),
                article.getId());
        if (seen == null) {
            seen = new TCircleSeen();
            seen.setCircleD(article.getId());
            seen.setPid(userId);
            seen.setTimestamp(new Date());
            seenMapper.insert(seen);
        }
        TTrade trade = tradeMapper.selectByPrimaryKey(article.getTradeId());
        List<TCommentDTO> commentdto = new ArrayList<TCommentDTO>();
        if (article.getComment() != 0) {
            List<TComment> comments = commentMapper
                    .selectByCircleId(article.getId());
            if (CollectionUtils.isNotEmpty(comments)) {
                for (TComment bean : comments) {
                    TPhone userinfo = userMapper
                            .getUserInfoById(bean.getPidId());
                    commentdto.add(new TCommentDTO(bean, userinfo));
                }
            }
        }
        List<Integer> userids = likeMapper
                .selectAllLikePidByCircleId(article.getId());
        List<TPhone> userinfos = userMapper.selectByIds(userids);
        TPhone u = userMapper.getUserInfoById(userId);
        if (u.getCityId() != null && u.getProvinceId() != null) {
            TProvince province = provinceMapper
                    .selectByPrimaryKey(u.getProvinceId());
            TCity city = cityMapper.selectByPrimaryKey(u.getCityId());
            if (StringUtils.isNotBlank(user.getLanguage())
                    && "en_US".equals(user.getLanguage()) && province != null
                    && city != null) {
                user.setProvince(province.getEnName());
                user.setCity(city.getEnName());
            } else {
                user.setProvince(province.getName());
                user.setCity(city.getName());
            }
        }
        TCircleDTO dto = TCircleDTO.builder(article, u, commentdto, trade,
                userinfos);
        TCircleLike like = likeMapper.selectByCircleIdAndPid(article.getId(),
                user.getId());
        if (like == null) {
            dto.setLike(false);
        } else {
            dto.setLike(true);
        }
        dto.setSeen(seen);
        return dto;

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
            notification.setProId(userId);
            notification.setPid(commentMapper
                    .selectByPrimaryKey(Integer.valueOf(commentId)).getPidId());
            notification.setTitle("有人评论你的评论");
            if ("1".equals(circle.getType())) {
                notification.setType(NotificationTypeEnum.Circle.getCode());
            } else {
                notification.setType(NotificationTypeEnum.Article.getCode());
            }
        }
        commentMapper.insert(comment);
        Integer pid = circleMapper.selectByPrimaryKey(circleId).getPid();
        TNotification notification = new TNotification();
        notification.setContent(txt);
        notification.setDatetime(new Date());
        notification.setContentId(circleId);
        notification.setPid(pid);
        notification.setProId(userId);
        notification.setTitle("你的同行圈收到评论");
        if ("1".equals(circle.getType())) {
            notification.setType(NotificationTypeEnum.Circle.getCode());
        } else {
            notification.setType(NotificationTypeEnum.Article.getCode());
        }
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
        TCircleLike like = likeMapper.selectByCircleIdAndPid(circleId, userId);
        if (like == null) {
            circle.setFavour(circle.getFavour() + 1);
            like = new TCircleLike();
            like.setPid(userId);
            like.setCircleId(circleId);
            likeMapper.insert(like);
        } else {
            circle.setFavour(circle.getFavour() - 1);
            likeMapper.deleteById(like.getId());
        }
        circleMapper.updateByPrimaryKey(circle);
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
        if (userinfo.getCityId() != null && userinfo.getProvinceId() != null) {
            TProvince province = provinceMapper
                    .selectByPrimaryKey(userinfo.getProvinceId());
            TCity city = cityMapper.selectByPrimaryKey(userinfo.getCityId());
            if (StringUtils.isNotBlank(user.getLanguage())
                    && "en_US".equals(user.getLanguage()) && province != null
                    && city != null) {
                user.setProvince(province.getEnName());
                user.setCity(city.getEnName());
            } else {
                user.setProvince(province.getName());
                user.setCity(city.getName());
            }
        }
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
        result.put("user", userService.getInfo(targetUserId));
        List<TCircle> services = circleMapper.getServicesByUserId(user.getId());
        List<TCircleDTO> s = new ArrayList<TCircleDTO>();
        s.add(new TCircleDTO(services.get(0)));
        result.put("service", s);
        List<TCircle> circles = circleMapper
                .getMyCircles(Integer.valueOf(targetUserId));
        if (circles != null && circles.size() > 0) {
            result.put("circle", new TCircleDTO(circles.get(0)));
        }
        TFriend friend = friendMapper.isApplyFriends(Integer.valueOf(userId),
                Integer.valueOf(targetUserId));
        result.put("friend", friend);
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
            if (userinfo.getCityId() != null
                    && userinfo.getProvinceId() != null) {
                TProvince province = provinceMapper
                        .selectByPrimaryKey(userinfo.getProvinceId());
                TCity city = cityMapper
                        .selectByPrimaryKey(userinfo.getCityId());
                if (StringUtils.isNotBlank(user.getLanguage())
                        && "en_US".equals(user.getLanguage())
                        && province != null && city != null) {
                    user.setProvince(province.getEnName());
                    user.setCity(city.getEnName());
                } else {
                    user.setProvince(province.getName());
                    user.setCity(city.getName());
                }
            }
            friendresult.add(new TFriendDTO(bean, userinfo));
        }
        return friendresult;
    }

    public Object favorite(int userId, String type, String id)
            throws ServiceException {
        TPhone user = userMapper.selectByPrimaryKey(Integer.valueOf(userId));
        if (user == null) {
            throw new ServiceException(ErrorCode.code101.getCode(),
                    ErrorCode.code101.getHttpCode(),
                    ErrorCode.code101.getDesc());
        }
        TFavorite bean = favoriteMapper.selectByPidFavid(type, userId,
                Integer.valueOf(id));
        if (bean == null) {
            bean = new TFavorite();
            bean.setPid(userId);
            bean.setFavoriteId(Integer.valueOf(id));
            bean.setType(type);
            favoriteMapper.insert(bean);
        } else {
            favoriteMapper.deleteByPidFavid(type, userId, Integer.valueOf(id));
        }
        List<TFavorite> favorites = favoriteMapper.selectByPid(userId);
        List<TFavoriteDTO> result = new ArrayList<TFavoriteDTO>();
        for (TFavorite f : favorites) {
            if ("1".equals(f.getType())) {
                TCircle service = circleMapper
                        .selectByPrimaryKey(f.getFavoriteId());
                TPhone userinfo = userMapper.getUserInfoById(service.getPid());
                if (userinfo.getCityId() != null
                        && userinfo.getProvinceId() != null) {
                    TProvince province = provinceMapper
                            .selectByPrimaryKey(userinfo.getProvinceId());
                    TCity city = cityMapper
                            .selectByPrimaryKey(userinfo.getCityId());
                    if (StringUtils.isNotBlank(user.getLanguage())
                            && "en_US".equals(user.getLanguage())
                            && province != null && city != null) {
                        user.setProvince(province.getEnName());
                        user.setCity(city.getEnName());
                    } else {
                        user.setProvince(province.getName());
                        user.setCity(city.getName());
                    }
                }
                TCircleDTO dto = TCircleDTO.builder(service, userinfo, null,
                        null, null);
                result.add(new TFavoriteDTO(f, dto, null));
            } else if ("2".equals(f.getType())) {
                TCircle cirle = circleMapper
                        .selectByPrimaryKey(f.getFavoriteId());
                TPhone userinfo = userMapper.getUserInfoById(cirle.getPid());
                TTrade tarde = tradeMapper
                        .selectByPrimaryKey(userinfo.getTradeId());
                TCircleDTO dto = TCircleDTO.builder(cirle, userinfo, null,
                        tarde, null);
                result.add(new TFavoriteDTO(f, null, dto));
            }
        }
        return result;
    }

    public Object favoritelist(int userId) throws ServiceException {
        TPhone user = userMapper.selectByPrimaryKey(Integer.valueOf(userId));
        if (user == null) {
            throw new ServiceException(ErrorCode.code101.getCode(),
                    ErrorCode.code101.getHttpCode(),
                    ErrorCode.code101.getDesc());
        }
        List<TFavorite> favorites = favoriteMapper.selectByPid(userId);
        List<TFavoriteDTO> result = new ArrayList<TFavoriteDTO>();
        for (TFavorite f : favorites) {
            if ("1".equals(f.getType())) {
                TCircle service = circleMapper
                        .selectByPrimaryKey(f.getFavoriteId());
                TPhone userinfo = userMapper.getUserInfoById(service.getPid());
                if (userinfo.getCityId() != null
                        && userinfo.getProvinceId() != null) {
                    TProvince province = provinceMapper
                            .selectByPrimaryKey(userinfo.getProvinceId());
                    TCity city = cityMapper
                            .selectByPrimaryKey(userinfo.getCityId());
                    if (StringUtils.isNotBlank(user.getLanguage())
                            && "en_US".equals(user.getLanguage())
                            && province != null && city != null) {
                        user.setProvince(province.getEnName());
                        user.setCity(city.getEnName());
                    } else {
                        user.setProvince(province.getName());
                        user.setCity(city.getName());
                    }
                }
                TCircleDTO dto = TCircleDTO.builder(service, userinfo, null,
                        null, null);
                result.add(new TFavoriteDTO(f, dto, null));
            } else if ("2".equals(f.getType())) {
                TCircle cirle = circleMapper
                        .selectByPrimaryKey(f.getFavoriteId());
                TPhone userinfo = userMapper.getUserInfoById(cirle.getPid());
                TTrade tarde = tradeMapper
                        .selectByPrimaryKey(userinfo.getTradeId());
                TCircleDTO dto = TCircleDTO.builder(cirle, userinfo, null,
                        tarde, null);
                result.add(new TFavoriteDTO(f, null, dto));
            }
        }
        return result;
    }

    public Object hotArticle(int userId) throws ServiceException {
        TPhone user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            throw new ServiceException(ErrorCode.code101.getCode(),
                    ErrorCode.code101.getHttpCode(),
                    ErrorCode.code101.getDesc());
        }
        List<ArticlesVo> result = new ArrayList<ArticlesVo>();
        List<ArticlesVo> articles = circleMapper.getHotArticles();
        if (CollectionUtils.isNotEmpty(articles)) {
            // for (ArticlesVo bean : articles) {
            ArticlesVo bean = articles.get(0);
            TCircleLike like = likeMapper.selectByCircleIdAndPid(bean.getId(),
                    userId);
            if (like != null) {
                bean.setLike(true);
            } else {
                bean.setLike(false);
            }
            result.add(bean);
            // }
        }
        return result;
    }

}
