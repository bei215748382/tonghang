package com.tonghang.server.entity;

public class TService {
    private Integer id;

    private String title;

    private String description;

    private String pictures;

    private Integer pid;

    private Integer checked;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
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

    public TService(Integer id, String title, String description,
            String pictures, Integer pid, Integer checked) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.pictures = pictures;
        this.pid = pid;
        this.checked = checked;
    }

    public TService() {
        super();
    }
	
	

}