package com.semon.wx.web.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.semon.wx.caller.caller.WxJSCaller;
import com.semon.wx.web.service.WxJSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxJSServiceImpl implements WxJSService {
    @Autowired
    private WxJSCaller wxJSCaller;

    @Override
    public String jsticket(String defaultAccessToken) {
        String ticket = wxJSCaller.jsticket(defaultAccessToken);
        JSONObject ticketJsonObj = JSONObject.parseObject(ticket);

        return null !=ticketJsonObj?ticketJsonObj.getString("ticket"):null;
    }
}
