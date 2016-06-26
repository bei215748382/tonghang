package com.tonghang.server.common.dto;

import java.io.Serializable;

import com.tonghang.server.entity.TPhone;

public class TUserDTO extends TPhone implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 144557483726129059L;

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

    public TUserDTO(TPhone user) {
        super(user);
    }

    public TUserDTO(TPhone user, TServiceDTO service, TCircleDTO circle) {
        super(user);
        this.service = service;
        this.circle = circle;
    }

}
