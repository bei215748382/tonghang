package com.tonghang.server.mapper;

import com.tonghang.server.entity.TCity;
import com.tonghang.server.entity.TProvince;

import java.util.List;

public interface TCityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TCity record);

    TCity selectByPrimaryKey(Integer id);

    List<TCity> selectAll();

    int updateByPrimaryKey(TCity record);

    TCity selectByName(String name);

    List<TCity> selectByProvinceId(Integer provinceId);
    
    List<TCity> selectByProvinceIdZh(Integer provinceId);
    List<TCity> selectByProvinceIdEn(Integer provinceId);

    List<TCity> selectMostUsed();
    
    List<TCity> selectMostUsedEn();
}