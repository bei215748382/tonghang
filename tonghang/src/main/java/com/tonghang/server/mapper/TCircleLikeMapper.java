package com.tonghang.server.mapper;

import org.apache.ibatis.annotations.Param;

import com.tonghang.server.entity.TCircleLike;

public interface TCircleLikeMapper {
    int deleteById(Integer id);

    int insert(TCircleLike record);
    
    TCircleLike selectByCircleIdAndPid(@Param("circleId")Integer circleId,@Param("pid")Integer pid);

}