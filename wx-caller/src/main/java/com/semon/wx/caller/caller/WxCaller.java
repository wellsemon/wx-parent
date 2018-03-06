package com.semon.wx.caller.caller;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
public interface WxCaller {
    @Cacheable(value = "accesstoken")
    String defaultAccessToken();

    String snsuser(String accessToken, String openId);
}
