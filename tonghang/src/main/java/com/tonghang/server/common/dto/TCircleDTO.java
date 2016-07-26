package com.tonghang.server.common.dto;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.tonghang.server.entity.TCircle;
import com.tonghang.server.entity.TCircleSeen;
import com.tonghang.server.entity.TFavorite;
import com.tonghang.server.entity.TPhone;
import com.tonghang.server.entity.TTrade;

public class TCircleDTO extends TCircle implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 2005711838361405883L;

    private TPhone userinfo;

    private List<TCommentDTO> comments;
    private List<TPhone> userlikes;
    private String[] imgs;

    private TFavorite favorite;

    private TCircleSeen seen;

    private boolean like;
    private List<TPhone> usershares;

    public List<TPhone> getUsershares() {
        return usershares;
    }

    public void setUsershares(List<TPhone> usershares) {
        this.usershares = usershares;
    }

    public TCircleSeen getSeen() {
        return seen;
    }

    public void setSeen(TCircleSeen seen) {
        this.seen = seen;
    }

    public TFavorite getFavorite() {
        return favorite;
    }

    public void setFavorite(TFavorite favorite) {
        this.favorite = favorite;
    }

    public List<TPhone> getUserlikes() {
        return userlikes;
    }

    public void setUserlikes(List<TPhone> userlikes) {
        this.userlikes = userlikes;
    }

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

    public TCircleDTO(TCircle circle, TFavorite favorite) {
        super(circle);
        this.favorite = favorite;
        this.setImgs(circle.getPics());
        this.setPics(null);
    }

    public List<TCommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<TCommentDTO> comments) {
        this.comments = comments;
    }

    public static TCircleDTO builder(TCircle circle, TPhone userinfo,
            List<TCommentDTO> comments, TTrade trade, List<TPhone> userlikes) {
        TCircleDTO bean = new TCircleDTO(circle);
        bean.setComments(comments);

        bean.setImgs(circle.getPics());
        if (circle.getType() == 1) {
            bean.setPic(null);
        } else {
            if (StringUtils.isNotBlank(circle.getPic())) {
                bean.setPic(circle.getPic().split(",")[0]);
            }
        }
        bean.setPics(null);
        if (trade != null && userinfo != null) {
            if (StringUtils.isNoneBlank(userinfo.getLanguage())
                    && "en_US".equals(userinfo.getLanguage())) {
                userinfo.setTrade(trade.getEnName());
            } else {
                userinfo.setTrade(trade.getName());
            }
        }
        bean.setUserinfo(userinfo);
        bean.setUserlikes(userlikes);
        return bean;
    }

    public static TCircleDTO builder(TCircle circle, TPhone userinfo,
            List<TCommentDTO> comments, TTrade trade, List<TPhone> userlikes,
            TCircleSeen seen) {
        TCircleDTO bean = new TCircleDTO(circle);
        bean.setComments(comments);

        bean.setImgs(circle.getPics());
        if (circle.getType() == 1) {
            bean.setPic(null);
        } else {
            if (StringUtils.isNotBlank(circle.getPic())) {
                bean.setPic(circle.getPic().split(",")[0]);
            }
        }
        bean.setPics(null);
        if (trade != null) {
            if (StringUtils.isNoneBlank(userinfo.getLanguage())
                    && "en_US".equals(userinfo.getLanguage())) {
                userinfo.setTrade(trade.getEnName());
            } else {
                userinfo.setTrade(trade.getName());
            }
        }
        bean.setUserinfo(userinfo);
        bean.setUserlikes(userlikes);
        bean.setSeen(seen);
        return bean;
    }

}