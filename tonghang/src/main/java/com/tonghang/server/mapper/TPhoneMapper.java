package com.tonghang.server.mapper;

import com.tonghang.server.entity.TPhone;
import com.tonghang.server.vo.UserVo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TPhoneMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TPhone record);

    TPhone selectByPrimaryKey(Integer id);

    List<TPhone> selectAll();

    int updateByPrimaryKey(TPhone record);

    List<UserVo> getUsers();

    /**
     * 根据手机号码进行查询
     * 
     * @param phone
     * @return
     */
    TPhone selectByPhone(String phone);

    List<TPhone> selectByIds(List<Integer> friendsId);

    TPhone getUserById(Integer id);

    List<Integer> getUserIdByCityAndTrade(@Param("cityId") Integer cityId,
            @Param("tradeId") Integer tradeId);
    
    TPhone getUserInfoById(Integer id);
}