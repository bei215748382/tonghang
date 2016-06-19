package com.tonghang.server.entity;

import java.util.Date;

public class TCircle {
	private Integer id;

	private String title;

	private Integer hot;

	private Date datetime;

	private String pic;

	private Integer pageView;

	private Integer favour;

	private Integer comment;

	private Integer type;

	private Integer pid;

	private Integer checked;

	private String pics;

	private String area;

	private Integer tradeId;

	private String content;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getHot() {
		return hot;
	}

	public void setHot(Integer hot) {
		this.hot = hot;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Integer getPageView() {
		return pageView;
	}

	public void setPageView(Integer pageView) {
		this.pageView = pageView;
	}

	public Integer getFavour() {
		return favour;
	}

	public void setFavour(Integer favour) {
		this.favour = favour;
	}

	public Integer getComment() {
		return comment;
	}

	public void setComment(Integer comment) {
		this.comment = comment;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getChecked() {
		return checked;
	}

	public void setChecked(Integer checked) {
		this.checked = checked;
	}

	public String getPics() {
		return pics;
	}

	public void setPics(String pics) {
		this.pics = pics;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Integer getTradeId() {
		return tradeId;
	}

	public void setTradeId(Integer tradeId) {
		this.tradeId = tradeId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	

    public TCircle() {
        super();
    }

    public TCircle(Integer id, String title, Integer hot, Date datetime,
            String pic, Integer pageView, Integer favour, Integer comment,
            Integer type, Integer pid, Integer checked, String pics,
            String area, Integer tradeId, String content) {
        super();
        this.id = id;
        this.title = title;
        this.hot = hot;
        this.datetime = datetime;
        this.pic = pic;
        this.pageView = pageView;
        this.favour = favour;
        this.comment = comment;
        this.type = type;
        this.pid = pid;
        this.checked = checked;
        this.pics = pics;
        this.area = area;
        this.tradeId = tradeId;
        this.content = content;
    }
	
	
}