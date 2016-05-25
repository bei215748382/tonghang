package com.tonghang.server.mapper;

import com.tonghang.server.entity.TReasonComment;
import java.util.List;

public interface TReasonCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TReasonComment record);

    TReasonComment selectByPrimaryKey(Integer id);

    List<TReasonComment> selectAll();

    int updateByPrimaryKey(TReasonComment record);
}