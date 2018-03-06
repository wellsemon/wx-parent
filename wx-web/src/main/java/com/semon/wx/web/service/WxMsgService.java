package com.semon.wx.web.service;

import java.util.Map;

public interface WxMsgService {

    /**
     * handle msg from wechat
     * @param reqmap
     * @return
     * @throws Exception
     */
    String handle(Map<String, String> reqmap) throws Exception;
}
