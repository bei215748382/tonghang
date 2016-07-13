package com.tonghang.server.mapper;

import java.util.List;

import com.tonghang.server.entity.TService;
import com.tonghang.server.vo.CircleVo;

public interface TServiceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TService record);

    TService selectByPrimaryKey(Integer id);

    List<TService> selectAll();

    int updateByPrimaryKey(TService record);

	List<TService> getServiceUnCheck();

	List<TService> getServiceChecked();

    List<CircleVo> getServices();
    
    List<TService> getServicesByUserId(int userId);

    List<CircleVo> getUncheckedServices();
    
    List<CircleVo> getCheckedServices();

    Boolean checkService(Integer id);
}