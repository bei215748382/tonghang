package com.tonghang.server.mapper;

import java.util.List;

import com.tonghang.server.entity.TTrack;

public interface TTrackMapper {

	public void insert(TTrack record);
	
	/**
	 * 谁看过我////
	 * @param userId
	 * @return
	 */
	public List<TTrack>  findOneBeenTrack(int targetPid);
	
	
}
