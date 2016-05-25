package com.tonghang.server.entity;

import java.util.Date;

public class TCircleSeen {
    private Integer id;

    private Integer circleD;

    private Integer pid;

    private Date timestamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCircleD() {
        return circleD;
    }

    public void setCircleD(Integer circleD) {
        this.circleD = circleD;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}