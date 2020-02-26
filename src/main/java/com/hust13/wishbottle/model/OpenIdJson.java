package com.hust13.wishbottle.model;

import java.io.Serializable;

/**
 * 封装openId和session_key
 */
public class OpenIdJson implements Serializable {

    private static final long serialVersionUID = -8508861873732702443L;

    private String openid;
    private String session_key;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }
}
