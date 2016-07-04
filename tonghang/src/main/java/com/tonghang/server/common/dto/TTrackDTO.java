package com.tonghang.server.common.dto;

import java.io.Serializable;

import com.tonghang.server.entity.TPhone;
import com.tonghang.server.entity.TTrack;

public class TTrackDTO extends TTrack  implements  Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 6506080061956657413L;
    
    private TPhone userinfo;

    public TPhone getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(TPhone userinfo) {
        this.userinfo = userinfo;
    }
    
    public TTrackDTO(TTrack track) {
        super(track);
    }

    public TTrackDTO(TTrack track, TPhone userinfo) {
        super(track);
        this.userinfo = userinfo;
        this.setTargetPid(null);
    }
}

