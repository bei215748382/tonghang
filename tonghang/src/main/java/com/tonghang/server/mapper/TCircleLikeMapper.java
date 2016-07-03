package com.tonghang.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tonghang.server.entity.TCircleLike;

public interface TCircleLikeMapper {
    int deleteById(Integer id);

    int insert(TCircleLike record);
    
    TCircleLike selectByCircleIdAndPid(@Param("circleId")Integer circleId,@Param("pid")Integer pid);
    
    List<Integer> selectAllLikePidByCircleId(Integer circleId);

}