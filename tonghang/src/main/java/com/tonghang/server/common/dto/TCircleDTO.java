package com.tonghang.server.common.dto;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.tonghang.server.entity.TCircle;
import com.tonghang.server.entity.TPhone;
import com.tonghang.server.entity.TTrade;

public class TCircleDTO extends TCircle implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 2005711838361405883L;

    private TPhone  userinfo;    

    private List<TCommentDTO> comments;

    private String[] imgs;
    
    private boolean like;

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public String[] getImgs() {
        return imgs;
    }

    public TPhone getUserinfo() {
        return userinfo;
    }

    public void setImgs(String[] imgs) {
        this.imgs = imgs;
    }

    public void setImgs(String pics) {
        if (StringUtils.isNotBlank(pics))
            imgs = pics.split(",");

    }

    public void setUserinfo(TPhone userinfo) {
        this.userinfo = userinfo;
    }

    public TCircleDTO(TCircle circle) {
        super(circle);
        this.setImgs(circle.getPics());
        this.setPics(null);
    }

    public List<TCommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<TCommentDTO> comments) {
        this.comments = comments;
    }



    public static TCircleDTO builder(TCircle circle, TPhone user,
            List<TCommentDTO> comments, TTrade trade) {
        TCircleDTO bean = new TCircleDTO(circle);
        bean.setComments(comments);
        bean.setUserinfo(user);
        bean.setImgs(circle.getPics());
        if (circle.getType() == 1) {
            bean.setPic(null);
        } else {
            if (StringUtils.isNotBlank(circle.getPic())) {
                bean.setPic(circle.getPic().split(",")[0]);
            }
        }
        bean.setPics(null);
        return bean;
    }

}