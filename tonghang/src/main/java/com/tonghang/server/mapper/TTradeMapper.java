package com.tonghang.server.mapper;

import com.tonghang.server.entity.TTrade;
import java.util.List;

public interface TTradeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TTrade record);

    TTrade selectByPrimaryKey(Integer id);

    List<TTrade> selectAll();

    int updateByPrimaryKey(TTrade record);

    List<TTrade> selectAllZh();
    
    List<TTrade> selectAllEn();
}