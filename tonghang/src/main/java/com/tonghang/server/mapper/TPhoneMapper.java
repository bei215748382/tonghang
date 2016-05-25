package com.tonghang.server.mapper;

import com.tonghang.server.entity.TPhone;
import java.util.List;

public interface TPhoneMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TPhone record);

    TPhone selectByPrimaryKey(Integer id);

    List<TPhone> selectAll();

    int updateByPrimaryKey(TPhone record);
}