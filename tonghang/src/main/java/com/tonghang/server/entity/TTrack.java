package com.tonghang.server.entity;

import java.sql.Timestamp;

/**
 * 足迹类
 * @author yxx
 *
 */
public class TTrack {

	private int id;
	
	private int  pid;
	
	private int  targetPid;
	
	private  Timestamp createTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}
	

	public int getTargetPid() {
		return targetPid;
	}

	public void setTargetPid(int targetPid) {
		this.targetPid = targetPid;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	
}
