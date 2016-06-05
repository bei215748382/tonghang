package com.tonghang.server.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tonghang.server.common.dto.AccessToken;
import com.tonghang.server.entity.TPhone;
import com.tonghang.server.exception.ServiceException;
import com.tonghang.server.util.CodecUtil;

@Service
public class TokenServiceImpl   {
    private static final Logger log = LoggerFactory.getLogger(TokenServiceImpl.class);


    public String generateAccessToken(TPhone user) throws ServiceException {
        AccessToken accessToken = new AccessToken();
        accessToken.setAuid(user.getId());
        accessToken.setCurrenttime(System.currentTimeMillis());
        accessToken.setExpriation(
                accessToken.getCurrenttime() + 24*60*60*100);
        String token = CodecUtil.encodeToken2Ciphertext(accessToken.toString());
        if(log.isDebugEnabled()){
            log.debug("{} -> {}",accessToken,token);
        }
        return token;
    }
}
