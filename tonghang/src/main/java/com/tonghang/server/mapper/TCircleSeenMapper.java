package com.tonghang.server.mapper;

import com.tonghang.server.entity.TCircleSeen;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TCircleSeenMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TCircleSeen record);

    TCircleSeen selectByPrimaryKey(Integer id);

    List<TCircleSeen> selectAll();

    int updateByPrimaryKey(TCircleSeen record);
    
    TCircleSeen selectByUserAndCircle(@Param("pid")Integer pid,@Param("circleId")Integer circleId);
}