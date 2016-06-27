package com.tonghang.server.mapper;

import com.tonghang.server.entity.TCircle;
import com.tonghang.server.entity.TComment;
import com.tonghang.server.vo.CheckCommentVo;

import java.util.List;

public interface TCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TComment record);

    TComment selectByPrimaryKey(Integer id);

    List<TComment> selectAll();

    int updateByPrimaryKey(TComment record);

	List<CheckCommentVo> getCommentUnCheck();

	List<CheckCommentVo> getCommentChecked();
	
	List<TComment> selectByCircleId(Integer id);

    Boolean checkComment(Integer id);
}