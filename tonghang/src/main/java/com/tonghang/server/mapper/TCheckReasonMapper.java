package com.tonghang.server.mapper;

import com.tonghang.server.entity.TCheckReason;
import java.util.List;

public interface TCheckReasonMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TCheckReason record);

    TCheckReason selectByPrimaryKey(Integer id);

    List<TCheckReason> selectAll();

    int updateByPrimaryKey(TCheckReason record);
}