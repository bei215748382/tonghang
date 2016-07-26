package com.tonghang.server.mapper;

import com.tonghang.server.entity.TComment;
import com.tonghang.server.vo.CheckCommentVo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TComment record);

    TComment selectByPrimaryKey(Integer id);

    List<TComment> selectAll();

    int updateByPrimaryKey(TComment record);

    List<CheckCommentVo> getCommentUnCheck();

    List<CheckCommentVo> getCommentChecked();

    List<TComment> selectByCircleId(Integer id);

    Boolean checkComment(@Param("id") Integer id,
            @Param("checked") Integer checked);

    List<TComment> getCommentByUserId(Integer id);
}