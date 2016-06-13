package com.tonghang.server.mapper;

import com.tonghang.server.entity.TCircle;
import com.tonghang.server.vo.ArticleInfo;
import com.tonghang.server.vo.ArticlesVo;
import com.tonghang.server.vo.CircleVo;

import java.util.List;

public interface TCircleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TCircle record);

    TCircle selectByPrimaryKey(Integer id);

    List<TCircle> selectAll();

    int updateByPrimaryKey(TCircle record);

	List<CircleVo> getCircleUnCheck();

	List<TCircle> getCircleChecked();
	
	List<TCircle> getMyCircles(int userId);

    List<ArticlesVo> getArticles();

    ArticlesVo getArticle(Integer id);

	List<TCircle> getFriendCircles(List<Integer> firendsId);

    ArticleInfo getArticleInfo(Integer id);
}