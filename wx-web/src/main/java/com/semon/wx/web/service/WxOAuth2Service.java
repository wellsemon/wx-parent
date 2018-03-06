package com.semon.wx.web.service;

import com.semon.wx.web.bean.WxOauth2Token;

public interface WxOAuth2Service {
    /**
     * get redirect uri
     * @return
     */
    String redirectURI();

    WxOauth2Token wxoauth2token(String code);
}
