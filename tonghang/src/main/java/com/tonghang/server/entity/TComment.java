package com.tonghang.server.entity;

import java.util.Date;

public class TComment {
    private Integer id;

    private Integer circleId;

    private Integer pidId;

    private Integer replyId;

    private String content;

    private Integer checked;

    private Date datetime;

    public TComment() {
    }

    public TComment(TComment comment) {
        this.id = comment.getId();
        this.circleId = comment.getCircleId();
        this.pidId = comment.getPidId();
        this.replyId = comment.getReplyId();
        this.content = comment.getContent();
        this.checked = comment.getChecked();
        this.datetime = comment.getDatetime();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCircleId() {
        return circleId;
    }

    public void setCircleId(Integer circleId) {
        this.circleId = circleId;
    }

    public Integer getPidId() {
        return pidId;
    }

    public void setPidId(Integer pidId) {
        this.pidId = pidId;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getChecked() {
        return checked;
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

}