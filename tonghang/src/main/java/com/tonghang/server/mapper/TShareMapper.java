package com.tonghang.server.mapper;

import java.util.List;

import com.tonghang.server.entity.TShare;

public interface TShareMapper {

    public void insert(TShare bean);

    public List<TShare> selectByCid(Integer cid);

    public List<TShare> selectByPid(Integer pid);
    
    public List<Integer> selectPidsByCid(Integer cid);

}
