package com.semon.wx.web.controller;

import com.semon.common.util.ConvertUtil;
import com.semon.common.util.EncryptUtil;
import com.semon.common.util.SortUtil;
import com.semon.wx.common.constant.WxConstant;
import com.semon.wx.web.service.WxMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class WxMsgController {
    @Autowired
    private WxMsgService wxMsgService;

    /**
     * handle wechat check
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    @RequestMapping(value = "accept", method = RequestMethod.GET)
    public String accept(@RequestParam("signature") String signature, @RequestParam("timestamp") String timestamp,
                         @RequestParam("nonce") String nonce, @RequestParam("echostr") String echostr) {
        String sign = EncryptUtil.SHA1(ConvertUtil.arrToStr(SortUtil.bydic(WxConstant.TOKEN, timestamp, nonce)));

        if (!StringUtils.isEmpty(sign) && sign.equals(signature)) {
            return echostr;
        }

        return null;
    }

    /**
     * handle user msg(include event) passed through wechat server
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "accept", method = RequestMethod.POST)
    public String handle(HttpServletRequest request) throws Exception {
        //5s 内回复，否则微信断掉连接，重试3次
        //mgsid 排重
        //可回复空串，避免微信重复请求
        String respstr = null;

        Map<String, String> reqmap = ConvertUtil.xmlToMap(request.getInputStream());
        String resultstr = wxMsgService.handle(reqmap);
        //-----------------------------------

        //调用微信接口处理各种业务
        //  用户向公众号发送消息，后者收到基于用户微信号加密后的 openid
        //  每个用户对每个公众号的 openid 唯一

        //消息加密
        //开启加密后，用户发来的消息和开发者回复的消息都会被加密（但开发者通过客服接口等API调用形式向用户发送消息，则不受影响）
        return resultstr;

    }
}