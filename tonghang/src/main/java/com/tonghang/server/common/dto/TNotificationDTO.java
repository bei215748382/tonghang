package com.tonghang.server.common.dto;

import java.io.Serializable;

import com.tonghang.server.entity.TNotification;
import com.tonghang.server.entity.TPhone;

public class TNotificationDTO  extends  TNotification  implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -2864695993699100693L;
    
    
    private TPhone userinfo;

    public TPhone getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(TPhone userinfo) {
        this.userinfo = userinfo;
    }

    public TNotificationDTO(TNotification bean) {
        super(bean);
    }

    public TNotificationDTO(TNotification bean, TPhone user) {
        super(bean);
        this.userinfo = user;
    }
}