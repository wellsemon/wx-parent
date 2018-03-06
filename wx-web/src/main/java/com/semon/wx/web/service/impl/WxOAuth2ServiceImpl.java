package com.semon.wx.web.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.semon.wx.caller.caller.WxOAuth2Caller;
import com.semon.wx.common.constant.WxConstant;
import com.semon.wx.web.bean.WxOauth2Token;
import com.semon.wx.web.service.WxOAuth2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxOAuth2ServiceImpl implements WxOAuth2Service {
    @Autowired
    private WxOAuth2Caller wxOAuth2Caller;

    @Override
    public String redirectURI() {
        StringBuffer redirecURISbuf = new StringBuffer();
        redirecURISbuf.append(WxConstant.URL_PREFIX_WECHAT_OPEN);
        redirecURISbuf.append("connect/oauth2/authorize");

        return redirecURISbuf.toString();
    }

    @Override
    public WxOauth2Token wxoauth2token(String code) {
        String oauth2token = wxOAuth2Caller.oauth2token(code);

        JSONObject jsonObject = JSONObject.parseObject(oauth2token);
        WxOauth2Token wxOauth2Token = null;
        if (null != jsonObject) {
            wxOauth2Token = new WxOauth2Token();
            wxOauth2Token.setAccessToken(jsonObject.getString("access_token"));
            wxOauth2Token.setExpiresIn(jsonObject.getInteger("expires_in"));
            wxOauth2Token.setRefreshToken(jsonObject.getString("refresh_token"));
            wxOauth2Token.setOpenId(jsonObject.getString("openid"));
            wxOauth2Token.setScope(jsonObject.getString("scope"));
        }
        return wxOauth2Token;
    }
}
