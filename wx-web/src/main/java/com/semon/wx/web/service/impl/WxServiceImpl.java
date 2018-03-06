package com.semon.wx.web.service.impl;

import com.semon.wx.caller.caller.WxCaller;
import com.semon.wx.web.service.WxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxServiceImpl implements WxService {
    @Autowired
    private WxCaller wxCaller;

    @Override
    public String defaultAccessToken() {
        return wxCaller.defaultAccessToken();
    }
}
