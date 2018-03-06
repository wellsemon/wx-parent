package com.semon.wx.handler.impl;

import com.alibaba.fastjson.JSONObject;
import com.semon.common.util.ConvertUtil;
import com.semon.wx.handler.bean.WxHandlerResult;
import com.semon.wx.handler.bean.WxTextMsg;
import com.semon.wx.common.constant.WxConstant;
import com.semon.wx.handler.MsgHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;
import java.util.logging.Logger;

@Component
public class WxMsgHandler implements MsgHandler {
    private static final Logger LOGGER = Logger.getLogger(WxMsgHandler.class.getName());

    @Override
    public WxHandlerResult handle(Map<String, String> map) throws Exception {
        WxHandlerResult handlerResult = null;

        String msgType = map.get("MsgType");
        String event = map.get("Event");

        if (!StringUtils.isEmpty(msgType)) {
            if (!StringUtils.isEmpty(event)) {
                handlerResult = handleEvent(map, msgType, event);
            } else {
                handlerResult = handleMsg(map, msgType);
            }
        }

        return handlerResult;
    }

    private WxHandlerResult handleMsg(Map<String, String> map, String msgType) {
        String msg = null;

        String toUserName = map.get("ToUserName");
        String fromUserName = map.get("FromUserName");

        if (WxConstant.MsgType.TEXT.value().equals(msgType)) {
            String content = map.get("Content");

            WxTextMsg text = new WxTextMsg();
            text.setFromUserName(toUserName);         // 发送和回复是反向的
            text.setToUserName(fromUserName);
            text.setMsgType("text");
            text.setCreateTime(new Date().getTime());
            text.setContent("你发送的消息是：" + content);
            msg = ConvertUtil.toXMLStr(WxTextMsg.class, text);
            LOGGER.info("msg: " + msg);
        }

        return new WxHandlerResult(WxConstant.HandlerResultCode.OK.value(), msg);
    }

    private WxHandlerResult handleEvent(Map<String, String> map, String msgType, String event) throws Exception {
        LOGGER.info(JSONObject.toJSONString(map));
        return null;
    }
}
