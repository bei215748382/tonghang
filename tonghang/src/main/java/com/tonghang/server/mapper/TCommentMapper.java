package com.tonghang.server.mapper;

import com.tonghang.server.entity.TCircle;
import com.tonghang.server.entity.TComment;

import java.util.List;

public interface TCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TComment record);

    TComment selectByPrimaryKey(Integer id);

    List<TComment> selectAll();

    int updateByPrimaryKey(TComment record);

	List<TCircle> getCommentUnCheck();

	List<TCircle> getCommentChecked();
	
	List<TComment> selectByCircleId(Integer id);
}