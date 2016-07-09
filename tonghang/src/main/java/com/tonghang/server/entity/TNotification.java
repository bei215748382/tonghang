package com.tonghang.server.entity;

import java.util.Date;

public class TNotification {
    private Integer id;

    /**
     *  System("系统通知", "1"), Article("资讯评论通知", "2"), Circle("同行圈评论通知", "3"), Check("审核通知", "4")，Service("服务评论","5");
     */
    private String type;

    private String title;

    /**
     * 接收消息方
     */
    private Integer pid;

    private String content;

    private Integer contentId;

    private Date datetime;

    private String url;

    /**
     * 产生消息方 ，比如评论者id
     */
    private Integer proId;

    public TNotification() {

    }

    public TNotification(TNotification bean) {
        this.id = bean.getId();
        this.type = bean.getType();
        this.title = bean.getTitle();
        this.pid = bean.getPid();
        this.content = bean.getContent();
        this.contentId = bean.getContentId();
        this.datetime = bean.getDatetime();
        this.url = bean.getUrl();
        this.proId = bean.getProId();
    }

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}