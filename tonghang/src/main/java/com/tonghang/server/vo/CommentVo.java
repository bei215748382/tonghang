package com.tonghang.server.vo;

import com.tonghang.server.entity.TComment;

public class CommentVo extends TComment{
    
    private String pname;
    
    private String ppic;
    
    public String getPname() {
        return pname;
    }
    public void setPname(String pname) {
        this.pname = pname;
    }
    public String getPpic() {
        return ppic;
    }
    public void setPpic(String ppic) {
        this.ppic = ppic;
    }
    
}
