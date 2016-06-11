package com.tonghang.server.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tonghang.server.entity.TCircle;
import com.tonghang.server.entity.TFriend;
import com.tonghang.server.entity.TPhone;
import com.tonghang.server.exception.ErrorCode;
import com.tonghang.server.exception.ServiceException;
import com.tonghang.server.mapper.TCircleMapper;
import com.tonghang.server.mapper.TFriendMapper;
import com.tonghang.server.mapper.TPhoneMapper;

@Service
public class SocialServiceImpl {

	@Autowired
	private TCircleMapper circleMapper;
	@Autowired
	private TPhoneMapper userMapper;

	@Autowired
	private TFriendMapper friendMap;

	public Map<String, Object> publishSns(int userId, String txt, MultipartFile[] pictures) throws ServiceException {
		// TODO Auto-generated method stub
		TPhone user = userMapper.selectByPrimaryKey(userId);
		if (user == null) {
			throw new ServiceException(ErrorCode.code101.getCode(), ErrorCode.code101.getHttpCode(),
					ErrorCode.code101.getDesc());
		}
		TCircle circle = new TCircle();
		circle.setContent(txt);
		circle.setPid(userId);
		circle.setType(1);
		circle.setDatetime(new Date());
		circle.setTradeId(user.getTradeId());
		circle.setPics("");// TODO 图片
		circle.setArea("");// TODO 地区需修改成省市id
		circleMapper.insert(circle);
		List<TCircle> circles = circleMapper.getMyCircles(user.getId());
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("circles", circles);
		return result;
	}

	public Map<String, Object> browseSns(int userId, String targetUserId, String pageNo, String pageSize)
			throws ServiceException {
		TPhone user = userMapper.selectByPrimaryKey(userId);
		if (user == null) {
			throw new ServiceException(ErrorCode.code101.getCode(), ErrorCode.code101.getHttpCode(),
					ErrorCode.code101.getDesc());
		}
		Map<String, Object> result = new HashMap<String, Object>();
		if (StringUtils.isBlank(pageSize) || StringUtils.isNumeric(pageSize) || Integer.valueOf(pageSize) <= 0) {
			pageSize = "10";
		}
		if (StringUtils.isBlank(pageNo) || StringUtils.isNumeric(pageNo) || Integer.valueOf(pageNo) <= 0) {
			pageNo = "1";
		}
		if (StringUtils.isNotBlank(targetUserId)) {
			TPhone targetUser = userMapper.selectByPrimaryKey(Integer.valueOf(targetUserId));
			if (targetUser == null) {
				throw new ServiceException(ErrorCode.code101.getCode(), ErrorCode.code101.getHttpCode(),
						"user  been  look up  not   exist");
			}

			TFriend friend = friendMap.isFriends(user.getId(), targetUser.getId());
			if (friend != null) {
				List<TCircle> circles = circleMapper.getMyCircles(targetUser.getId());
				result.put("sns", circles);
			} else {
				throw new ServiceException(ErrorCode.code118.getCode(), ErrorCode.code118.getHttpCode(),
						ErrorCode.code118.getDesc());
			}
		} else {
			List<Integer> firendsId = friendMap.selectAllFriendsId(user.getId());
			if (firendsId == null) {
				firendsId = new ArrayList<Integer>();
			}
			List<TCircle> circles = circleMapper.getFriendCircles(firendsId);
			result.put("sns", circles);
		}
		return result;
	}

	public Map<String, Object> applyFriend(int userId, String targetUserId) throws ServiceException {

		TPhone user = userMapper.selectByPrimaryKey(userId);
		if (user == null) {
			throw new ServiceException(ErrorCode.code101.getCode(), ErrorCode.code101.getHttpCode(),
					ErrorCode.code101.getDesc());
		}
		TPhone targetUser = userMapper.selectByPrimaryKey(Integer.valueOf(targetUserId));
		if (targetUser == null) {
			throw new ServiceException(ErrorCode.code101.getCode(), ErrorCode.code101.getHttpCode(),
					"user  been  look up  not   exist");
		}

		TFriend friend = new TFriend();
		friend.setPid(user.getId());
		friend.setFid(targetUser.getId());
		friendMap.insert(friend);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("friend", friendMap.isFriends(user.getId(), targetUser.getId()));
		return result;
	}

	public Map<String, Object> friends(int userId) throws ServiceException {
		TPhone user = userMapper.selectByPrimaryKey(userId);
		if (user == null) {
			throw new ServiceException(ErrorCode.code101.getCode(), ErrorCode.code101.getHttpCode(),
					ErrorCode.code101.getDesc());
		}
		List<Integer> friendsId = friendMap.selectAllFriendsId(user.getId());
		if (friendsId == null) {
			friendsId = new ArrayList<Integer>();
		}
		List<TPhone> friends =userMapper.selectByIds(friendsId);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("friends", friends);
		return result;
	}

}
