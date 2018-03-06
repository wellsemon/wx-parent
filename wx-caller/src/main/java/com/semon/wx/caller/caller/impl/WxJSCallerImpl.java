package com.semon.wx.caller.caller.impl;

import com.semon.common.util.HttpClientUtil;
import com.semon.wx.caller.caller.WxJSCaller;
import com.semon.wx.common.constant.WxConstant;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class WxJSCallerImpl implements WxJSCaller {
    @Override
    public String jsticket(String defaultAccessToken) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("access_token", defaultAccessToken);
        param.put("type", "jsapi");

        return HttpClientUtil.doGet(WxConstant.URL_PREFIX_WECHAT_API + "cgi-bin/ticket/getticket", param);
    }
}
