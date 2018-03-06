package com.semon.wx.handler;

import com.semon.wx.handler.bean.WxHandlerResult;

import java.util.Map;

public interface MsgHandler {
     /**
      * handle msg from wechat
      * @param map
      * @return
      * @throws Exception
      */
     WxHandlerResult handle(Map<String, String> map) throws Exception;
}
