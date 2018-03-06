package com.semon.wx.caller.caller.impl;

import com.alibaba.fastjson.JSONObject;
import com.semon.common.util.HttpClientUtil;
import com.semon.wx.caller.caller.WxCaller;
import com.semon.wx.common.constant.WxConstant;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class WxCallerImpl implements WxCaller {
    @Override
    public String defaultAccessToken() {
        Map<String, String> param = new HashMap<String, String>();
        param.put("grant_type", WxConstant.GRANT_TYPE_CLIENT_CREDENTIAL);
        param.put("appid", WxConstant.APPID);
        param.put("secret", WxConstant.SECRET);

        String repstr = HttpClientUtil.doGet(WxConstant.URL_PREFIX_WECHAT_API + "cgi-bin/token", param);

        JSONObject jsonObject = JSONObject.parseObject(repstr);
        String accessToken = jsonObject.getString("access_token");
        return accessToken;
    }

    @Override
    public String snsuser(String accessToken, String openId) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("access_token", accessToken);
        param.put("openid", openId);

        return HttpClientUtil.doGet(WxConstant.URL_PREFIX_WECHAT_API + "sns/userinfo", param);
    }
}
