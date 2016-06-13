package com.tonghang.server.vo;

import com.tonghang.server.entity.TCircle;
import com.tonghang.server.entity.TPhone;

public class CircleVo extends TCircle{
    
    private TPhone phone;

    public TPhone getPhone() {
        return phone;
    }

    public void setPhone(TPhone phone) {
        this.phone = phone;
    }
    
}
