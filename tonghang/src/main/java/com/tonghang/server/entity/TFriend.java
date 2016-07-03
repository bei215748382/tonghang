package com.tonghang.server.entity;

public class TFriend {
    private Integer id;

    private Integer pid;// 申请者

    private Integer fid;// 被加好友者

    private Integer confirm;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getConfirm() {
        return confirm;
    }

    public void setConfirm(Integer confirm) {
        this.confirm = confirm;
    }

    public TFriend() {
    }

    public TFriend(TFriend bean) {
        this.confirm = bean.getConfirm();
        this.fid = bean.getFid();
        this.pid = bean.getPid();
        this.id = bean.getId();
    }
}