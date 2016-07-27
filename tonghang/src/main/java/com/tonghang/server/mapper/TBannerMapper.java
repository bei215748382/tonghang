package com.tonghang.server.mapper;

import java.util.List;

import com.tonghang.server.entity.TBanner;

public interface TBannerMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(TBanner record);

    List<TBanner> selectAll();

    List<TBanner> selectAllEnable();

    TBanner findById(Integer id);

	void update(TBanner tBanner);//更新banner

}
