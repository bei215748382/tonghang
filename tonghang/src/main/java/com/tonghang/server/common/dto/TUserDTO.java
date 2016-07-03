package com.tonghang.server.common.dto;

import java.io.Serializable;

import com.tonghang.server.entity.TCircle;
import com.tonghang.server.entity.TCity;
import com.tonghang.server.entity.TPhone;
import com.tonghang.server.entity.TProvince;
import com.tonghang.server.entity.TService;
import com.tonghang.server.entity.TTrade;

public class TUserDTO extends TPhone implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 144557483726129059L;

    private TCircleDTO service;

    private TCircleDTO circle;

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

    public TUserDTO(TPhone user) {
        super(user);
    }

    public TUserDTO(TPhone user, TCircleDTO service, TCircleDTO circle) {
        super(user);
        this.service = service;
        this.circle = circle;
    }

    public TUserDTO(TPhone user, TCircle service, TCircleDTO circle,
            TTrade trade, TProvince province, TCity city) {
        super(user);
        if (user != null)
            if (trade != null) {
                if ("en_US".equals(user.getLanguage())) {
                    this.setTrade(trade.getEnName());
                } else {
                    this.setTrade(trade.getName());
                }
            }
        if (province != null) {
            if ("en_US".equals(user.getLanguage())) {
                this.setProvince(province.getEnName());
            } else {
                this.setProvince(province.getName());
            }
        }
        if (city != null) {
            if ("en_US".equals(user.getLanguage())) {
                this.setCity(city.getEnName());
            } else {
                this.setCity(city.getName());
            }
        }
        if (service != null) {
            this.service = new TCircleDTO(service);
        }
        this.circle = circle;
    }

}
