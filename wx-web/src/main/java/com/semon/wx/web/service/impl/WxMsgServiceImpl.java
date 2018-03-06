package com.semon.wx.web.service.impl;

import com.semon.wx.handler.MsgHandler;
import com.semon.wx.handler.bean.WxHandlerResult;
import com.semon.wx.web.service.WxMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class WxMsgServiceImpl implements WxMsgService {
    @Autowired
    private MsgHandler msgHandler;

    @Override
    public String handle(Map<String, String> reqmap) throws Exception {
        WxHandlerResult wxHandlerResult = msgHandler.handle(reqmap);

        if (null != wxHandlerResult)
            return wxHandlerResult.getContent();

        return "";
    }
}
