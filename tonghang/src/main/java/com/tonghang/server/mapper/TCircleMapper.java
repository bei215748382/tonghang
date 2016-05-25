package com.tonghang.server.mapper;

import com.tonghang.server.entity.TCircle;
import java.util.List;

public interface TCircleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TCircle record);

    TCircle selectByPrimaryKey(Integer id);

    List<TCircle> selectAll();

    int updateByPrimaryKey(TCircle record);
}