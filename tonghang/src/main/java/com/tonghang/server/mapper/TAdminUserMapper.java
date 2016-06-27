package com.tonghang.server.mapper;

import com.tonghang.server.entity.TAdminUser;
import java.util.List;

public interface TAdminUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TAdminUser record);

    TAdminUser selectByPrimaryKey(Integer id);

    List<TAdminUser> selectAll();

    int updateByPrimaryKey(TAdminUser record);
}