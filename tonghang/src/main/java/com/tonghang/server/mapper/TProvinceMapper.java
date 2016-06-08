package com.tonghang.server.mapper;

import com.tonghang.server.entity.TProvince;
import java.util.List;

public interface TProvinceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TProvince record);

    TProvince selectByPrimaryKey(Integer id);

    List<TProvince> selectAll();
    
    List<TProvince> selectAllEn();
    List<TProvince> selectAllZh();

    int updateByPrimaryKey(TProvince record);
    
    TProvince selectByName(String name);
}