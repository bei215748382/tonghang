package com.tonghang.server.entity;

import java.sql.Date;

public class TFavorite {

    private int id;
    private int favoriteId;
    private int pid;
    /**
     * 1 服务 2同行圈
     */
    private String type;
    private Date createTime;

    public TFavorite() {

    }

    public TFavorite(TFavorite favorite) {
        this.id = favorite.getId();
        this.favoriteId = favorite.getFavoriteId();
        this.pid = favorite.getPid();
        this.type = favorite.getType();
        this.createTime = favorite.getCreateTime();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(int favoriteId) {
        this.favoriteId = favoriteId;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
