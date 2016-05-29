package com.tonghang.server.mapper;

import com.tonghang.server.entity.TCircle;
import com.tonghang.server.entity.TService;

import java.util.List;

public interface TServiceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TService record);

    TService selectByPrimaryKey(Integer id);

    List<TService> selectAll();

    int updateByPrimaryKey(TService record);

	List<TCircle> getServiceUnCheck();

	List<TCircle> getServiceChecked();
}