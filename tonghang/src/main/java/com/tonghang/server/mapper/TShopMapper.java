package com.tonghang.server.mapper;

import java.util.List;

import com.tonghang.server.entity.TShop;

public interface TShopMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TShop record);

    TShop selectByPrimaryKey(Integer id);

    List<TShop> selectAll();

    int updateByPrimaryKey(TShop record);


}