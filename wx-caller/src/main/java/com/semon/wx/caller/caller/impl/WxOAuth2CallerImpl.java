package com.semon.wx.caller.caller.impl;

import com.semon.common.util.HttpClientUtil;
import com.semon.wx.caller.caller.WxOAuth2Caller;
import com.semon.wx.common.constant.WxConstant;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class WxOAuth2CallerImpl implements WxOAuth2Caller {
    @Override
    public String oauth2token(String code) {
        // 获取网页授权凭证
        Map<String,String> params=new HashMap<>();
        params.put("appid",WxConstant.APPID);
        params.put("secret",WxConstant.SECRET);
        params.put("code",code);
        params.put("grant_type","authorization_code");

        return HttpClientUtil.doPost(WxConstant.URL_PREFIX_WECHAT_API+"sns/oauth2/access_token",params);
    }
}
