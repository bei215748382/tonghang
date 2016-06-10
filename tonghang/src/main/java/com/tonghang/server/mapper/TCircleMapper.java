package com.tonghang.server.mapper;

import com.tonghang.server.entity.TCircle;
import com.tonghang.server.vo.ArticlesVo;

import java.util.List;

public interface TCircleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TCircle record);

    TCircle selectByPrimaryKey(Integer id);

    List<TCircle> selectAll();

    int updateByPrimaryKey(TCircle record);

	List<TCircle> getCircleUnCheck();

	List<TCircle> getCircleChecked();

    List<ArticlesVo> getArticles();

    ArticlesVo getArticle(Integer id);
}