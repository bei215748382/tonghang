package com.tonghang.server.common.vo;

import java.net.URL;

public class TokenCredentail extends Credentail {

    public TokenCredentail(Token token) {
        super(token);
    }

    @Override
    protected URL getUrl() {
        return null;
    }

    @Override
    protected GrantType getGrantType() {
        return null;
    }

    @Override
    public Token getToken() {
        return token;
    }


}
