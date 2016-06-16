package com.tonghang.server.mapper;

import java.util.List;

import com.tonghang.server.entity.TCircle;
import com.tonghang.server.vo.ArticleInfo;
import com.tonghang.server.vo.ArticlesVo;
import com.tonghang.server.vo.CircleVo;

public interface TCircleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TCircle record);

    TCircle selectByPrimaryKey(Integer id);

    List<TCircle> selectAll();

    int updateByPrimaryKey(TCircle record);

	List<CircleVo> getCircleUnCheck();

	List<CircleVo> getCircleChecked();
	
	List<TCircle> getMyCircles(int userId);

    List<ArticlesVo> getArticles();

    ArticlesVo getArticle(Integer id);

	List<TCircle> getFriendCircles(List<Integer> firendsId);

    ArticleInfo getArticleInfo(Integer id);

    Boolean checkCircle(Integer id);
    
    List<ArticlesVo> getTradeArticles(int tradeId);
}