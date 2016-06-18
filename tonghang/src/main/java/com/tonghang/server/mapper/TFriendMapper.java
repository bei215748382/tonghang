package com.tonghang.server.mapper;

import com.tonghang.server.entity.TFriend;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TFriendMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(TFriend record);

	TFriend selectByPrimaryKey(Integer id);

	List<TFriend> selectAll();

	int updateByPrimaryKey(TFriend record);

	TFriend isFriends(@Param("userId") Integer userId, @Param("targetUserId") Integer targetUserId);
	
	TFriend friendNotConfirm(@Param("userId") Integer userId, @Param("targetUserId") Integer targetUserId);
	
	List<Integer> selectAllFriendsId(Integer userId);
}