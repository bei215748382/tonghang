package com.tonghang.server.common.dto;

import java.io.Serializable;

import com.tonghang.server.entity.TFriend;
import com.tonghang.server.entity.TPhone;

public class TFriendDTO extends TFriend implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8296158097493019173L;

    private TPhone userinfo;

    public TPhone getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(TPhone userinfo) {
        this.userinfo = userinfo;
    }

    public TFriendDTO(TFriend friend) {
        super(friend);
    }

    public TFriendDTO(TFriend friend, TPhone userinfo) {
        super(friend);
        this.userinfo = userinfo;
    }

}
