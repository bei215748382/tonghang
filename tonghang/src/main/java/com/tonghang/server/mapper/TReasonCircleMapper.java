package com.tonghang.server.mapper;

import com.tonghang.server.entity.TReasonCircle;
import java.util.List;

public interface TReasonCircleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TReasonCircle record);

    TReasonCircle selectByPrimaryKey(Integer id);

    List<TReasonCircle> selectAll();

    int updateByPrimaryKey(TReasonCircle record);
}