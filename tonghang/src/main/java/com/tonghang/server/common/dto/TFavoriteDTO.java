package com.tonghang.server.common.dto;

import java.io.Serializable;

import com.tonghang.server.entity.TFavorite;

public class TFavoriteDTO extends TFavorite implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1249108808675491374L;

    private TServiceDTO service;

    private TCircleDTO circle;

    public TServiceDTO getService() {
        return service;
    }

    public void setService(TServiceDTO service) {
        this.service = service;
    }

    public TCircleDTO getCircle() {
        return circle;
    }

    public void setCircle(TCircleDTO circle) {
        this.circle = circle;
    }

    public TFavoriteDTO(TFavorite favorite, TServiceDTO service,
            TCircleDTO cirle) {
        super(favorite);
        this.service = service;
        this.circle = cirle;
    }

}
