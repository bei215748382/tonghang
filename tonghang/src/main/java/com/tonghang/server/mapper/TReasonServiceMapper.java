package com.tonghang.server.mapper;

import com.tonghang.server.entity.TReasonService;
import java.util.List;

public interface TReasonServiceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TReasonService record);

    TReasonService selectByPrimaryKey(Integer id);

    List<TReasonService> selectAll();

    int updateByPrimaryKey(TReasonService record);
}