package com.tonghang.server.mapper;

import com.tonghang.server.entity.TNotification;
import java.util.List;

public interface TNotificationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TNotification record);

    TNotification selectByPrimaryKey(Integer id);

    List<TNotification> selectAll();

    int updateByPrimaryKey(TNotification record);
}