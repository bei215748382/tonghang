package com.tonghang.server.vo;

import com.tonghang.server.entity.TComment;

public class CheckCommentVo extends TComment{
    private Integer pid;//用户id
    
    private String pname;//用户姓名
    
    public Integer getPid() {
        return pid;
    }
    public void setPid(Integer pid) {
        this.pid = pid;
    }
    
    public String getPname() {
        return pname;
    }
    public void setPname(String pname) {
        this.pname = pname;
    }
    
}
