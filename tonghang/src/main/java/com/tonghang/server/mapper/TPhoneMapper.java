package com.tonghang.server.mapper;

import com.tonghang.server.entity.TPhone;
import com.tonghang.server.vo.IncVo;
import com.tonghang.server.vo.TodayIncVo;
import com.tonghang.server.vo.UserVo;

import java.util.List;
import java.util.Map;

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

    List<TPhone> selectByName(String name);

    List<TPhone> selectByIds(List<Integer> friendsId);

    TPhone getUserById(Integer id);

    List<Integer> getUserIdByCityAndTrade(@Param("cityId") Integer cityId,
            @Param("tradeId") Integer tradeId);

    TPhone getUserInfoById(Integer id);

    UserVo getUser(Integer id);

    List<TPhone> selectNewUsersId(@Param("tradeId") Integer tradeId);

    /**
     * 因为涉及排序  分页无法做到按照id进行获取下一页内容  
     * @param pageNo  页码
     * @param pageSize 页容量
     * @param tradeId TODO
     * @return 
     */
    List<TPhone> selectActiveUsersId(@Param("pageNo") Integer pageNo,
            @Param("pageSize") Integer pageSize,@Param("tradeId") Integer tradeId);
    
    List<TodayIncVo> getTodayNum();

    List<IncVo> get_inc_data();

    IncVo getDistribution();

    int getServicePeople();

    int getNoServicePeople();

    List<TodayIncVo> getCityPeople();
}