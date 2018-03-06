package com.semon.wx.web.service;

import com.semon.wx.web.bean.WxSNSUser;

public interface WxUserService {
    WxSNSUser wxsnsuser(String accessToken, String openId);
}
