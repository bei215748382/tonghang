package com.tonghang.server.mapper;

import com.tonghang.server.entity.TCircleSeen;
import java.util.List;

public interface TCircleSeenMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TCircleSeen record);

    TCircleSeen selectByPrimaryKey(Integer id);

    List<TCircleSeen> selectAll();

    int updateByPrimaryKey(TCircleSeen record);
}