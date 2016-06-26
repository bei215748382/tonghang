package com.tonghang.server.common.dto;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.tonghang.server.entity.TService;

public class TServiceDTO extends TService implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -1593970768142232944L;
    private String[] imgs;

    public TServiceDTO(TService bean) {
        super(bean.getId(), bean.getTitle(), bean.getDescription(),
                bean.getPictures(), bean.getPid(), bean.getChecked());
        this.setImgs(bean.getPictures());
        this.setPictures(null);
    }

    public String[] getImgs() {
        return imgs;
    }

    public void setImgs(String pics) {
        if(StringUtils.isNotBlank(pics))
        imgs = pics.split(",");

    }

    public static TServiceDTO builder(TService bean) {
        TServiceDTO dto = new TServiceDTO(bean);
        dto.setImgs(bean.getPictures());
        dto.setPictures(null);
        return dto;
    }

}