package com.tonghang.server.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.tonghang.server.common.dto.AccessToken;
import com.tonghang.server.entity.TPhone;
import com.tonghang.server.exception.ServiceException;
import com.tonghang.server.util.CodecUtil;

@Service
public class TokenServiceImpl {
    private static final Logger log = LoggerFactory
            .getLogger(TokenServiceImpl.class);

    public String generateAccessToken(TPhone user) throws ServiceException {
        AccessToken accessToken = new AccessToken();
        accessToken.setAuid(user.getId());
        accessToken
                .setExpriation(System.currentTimeMillis() + 24 * 60 * 60 * 1000);
        accessToken.setPlatformType(Integer.valueOf(user.getDevice()));
        String token = CodecUtil.encodeToken2Ciphertext(JSON.toJSONString(accessToken));
        if (log.isDebugEnabled()) {
            log.debug("{} -> {}", accessToken, token);
        }
        return token;
    }
}
