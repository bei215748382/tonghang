package com.tonghang.server.entity;

import java.sql.Timestamp;

/**
 * 足迹类
 * 
 * @author yxx
 *
 */
public class TTrack {

    private int id;

    private int pid;

    private int targetPid;//被浏览者id

    private Timestamp createTime;

    public TTrack() {
    }

    public TTrack(TTrack track) {
        this.id = track.getId();
        this.pid = track.getPid();
        this.targetPid = track.getTargetPid();
        this.createTime = track.getCreateTime();
    }

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
