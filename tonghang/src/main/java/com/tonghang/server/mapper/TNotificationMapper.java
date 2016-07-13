package com.tonghang.server.mapper;

import com.tonghang.server.entity.TNotification;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TNotificationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TNotification record);

    TNotification selectByPrimaryKey(Integer id);

    List<TNotification> selectAll();

    int updateByPrimaryKey(TNotification record);
    
    List<TNotification> selectByPid(Integer userId);
    
    
    /**
     * System("系统通知", "1"), Article("资讯评论通知", "2"), 
     * Circle("同行圈评论通知", "3"), Check("审核通知", "4")，Service("服务评论","5");
     * @param userId
     * @param type
     * @return
     */
    List<TNotification> selectByPidAndType(@Param("userId")Integer userId, @Param("type") String type);
}