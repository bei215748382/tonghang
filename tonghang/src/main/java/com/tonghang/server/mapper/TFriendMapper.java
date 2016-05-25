package com.tonghang.server.mapper;

import com.tonghang.server.entity.TFriend;
import java.util.List;

public interface TFriendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TFriend record);

    TFriend selectByPrimaryKey(Integer id);

    List<TFriend> selectAll();

    int updateByPrimaryKey(TFriend record);
}