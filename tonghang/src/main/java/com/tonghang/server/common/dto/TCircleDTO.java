package com.tonghang.server.common.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.tonghang.server.entity.TCircle;
import com.tonghang.server.entity.TComment;
import com.tonghang.server.entity.TPhone;
import com.tonghang.server.entity.TTrade;

public class TCircleDTO extends TCircle implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 2005711838361405883L;

    private String icon;

    private String position;

    private String trade;

    private String name;

    private String company;

    private List<TComment> comments;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public List<TComment> getComments() {
        return comments;
    }

    public void setComments(List<TComment> comments) {
        this.comments = comments;
    }

    public TCircleDTO(Integer id, String title, Integer hot, Date datetime,
            String pic, Integer pageView, Integer favour, Integer comment,
            Integer type, Integer pid, Integer checked, String pics,
            String area, Integer tradeId, String content) {
        super(id, title, hot, datetime, pic, pageView, favour, comment, type,
                pid, checked, pics, area, tradeId, content);
        // TODO Auto-generated constructor stub
    }

    public TCircleDTO(TCircle circle) {
        super(circle.getId(), circle.getTitle(), circle.getHot(),
                circle.getDatetime(), circle.getPic(), circle.getPageView(),
                circle.getFavour(), circle.getComment(), circle.getType(),
                circle.getPid(), circle.getChecked(), circle.getPics(),
                circle.getArea(), circle.getTradeId(), circle.getContent());
    }

    public static TCircleDTO builder(TCircle circle, TPhone user,
            List<TComment> comments, TTrade trade) {
        TCircleDTO bean = new TCircleDTO(circle);
        bean.setIcon(user.getPic());
        bean.setPosition(user.getPosition());
        if (trade != null) {
            if (StringUtils.isNotBlank(user.getLanguage())
                    && !"zh_CN".equals(user.getLanguage())) {
                bean.setTrade(trade.getEnName());
            } else {
                bean.setTrade(trade.getName());
            }
        }
        bean.setName(user.getName());
        bean.setCompany(user.getCompany());
        bean.setComments(comments);

        return bean;
    }

}