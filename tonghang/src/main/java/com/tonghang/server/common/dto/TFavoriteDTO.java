package com.tonghang.server.common.dto;

import java.io.Serializable;

import com.tonghang.server.entity.TFavorite;

public class TFavoriteDTO extends TFavorite implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1249108808675491374L;

    private TCircleDTO service;

    private TCircleDTO circle;
    
    private TCircleDTO article;

    public TCircleDTO getService() {
        return service;
    }

    public void setService(TCircleDTO service) {
        this.service = service;
    }

    public TCircleDTO getCircle() {
        return circle;
    }

    public void setCircle(TCircleDTO circle) {
        this.circle = circle;
    }
    
    public TCircleDTO getArticle() {
        return article;
    }

    public void setArticle(TCircleDTO article) {
        this.article = article;
    }

    public TFavoriteDTO(TFavorite favorite, TCircleDTO service,
            TCircleDTO cirle, TCircleDTO article) {
        super(favorite);
        this.service = service;
        this.circle = cirle;
        this.article = article;
    }

}
