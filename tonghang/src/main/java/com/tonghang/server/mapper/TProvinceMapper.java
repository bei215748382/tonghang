package com.tonghang.server.mapper;

import com.tonghang.server.entity.TProvince;
import java.util.List;

public interface TProvinceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TProvince record);

    TProvince selectByPrimaryKey(Integer id);

    List<TProvince> selectAll();

    int updateByPrimaryKey(TProvince record);
    
    TProvince selectByName(String name);
}