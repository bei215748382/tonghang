package com.tonghang.server.common.dto;

import java.io.Serializable;

import com.tonghang.server.entity.TComment;
import com.tonghang.server.entity.TPhone;

public class TCommentDTO  extends  TComment implements  Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = -7787915121877754709L;
    private TPhone userinfo;

    public TPhone getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(TPhone userinfo) {
        this.userinfo = userinfo;
    }

    public TCommentDTO(TComment comment) {
        super(comment);
    }

    public TCommentDTO(TComment comment, TPhone user) {
        super(comment);
        this.userinfo = user;
    }
    
}