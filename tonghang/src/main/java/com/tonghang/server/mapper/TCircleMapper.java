package com.tonghang.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tonghang.server.entity.TCircle;
import com.tonghang.server.vo.ArticleInfo;
import com.tonghang.server.vo.ArticlesVo;
import com.tonghang.server.vo.CircleVo;
import com.tonghang.server.vo.ServiceVo;

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
    
    List<ArticlesVo> getHotArticles();

    List<TCircle> getAllCircleShow(@Param("pageNo")int pageNo,@Param("pageSize") int pageSize);

    List<TCircle> getCircleByUserId(Integer id);
    
    List<TCircle> getServicesByUserId(Integer userId);
    
    TCircle getServiceById(Integer id);

    List<CircleVo> getUncheckedServices();
    
    List<CircleVo> getCheckedServices();

    Boolean checkService(Integer id);

    List<ServiceVo> getServices();
    
    TCircle getServiceByUid(Integer id);
    void deleteLastServiceByPid(Integer pid);
}