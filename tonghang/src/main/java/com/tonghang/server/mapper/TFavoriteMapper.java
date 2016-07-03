package com.tonghang.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tonghang.server.entity.TFavorite;

public interface TFavoriteMapper {

    public int insert(TFavorite bean);

    public List<TFavorite> selectByPid(int pid);

    public void deleteByPidFavid(@Param("type") String type,
            @Param("pid") int pid, @Param("favId") int favId);

    public TFavorite selectByPidFavid(@Param("type") String type,
            @Param("pid") int pid, @Param("favId") int favId);

}
