package com.semon.wx.web.controller;

import com.semon.wx.common.constant.WxConstant;
import com.semon.wx.common.util.WxUtil;
import com.semon.wx.web.service.WxJSService;
import com.semon.wx.web.service.WxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * some wechat function
 */
@Controller
public class WxFuncController {
    @Autowired
    private WxService wxService;
    @Autowired
    private WxJSService wxJSService;

    @RequestMapping("/share")
    public String share(HttpServletRequest request, Model model) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String defaultAccessToken = wxService.defaultAccessToken();
        String jsapi_ticket = wxJSService.jsticket(defaultAccessToken);
        String nonceStr = UUID.randomUUID().toString(); // 必填，生成签名的随机串
        String timestamp = Long.toString(System.currentTimeMillis() / 1000); // 必填，生成签名的时间戳

        String signature = WxUtil.signature(jsapi_ticket, nonceStr, timestamp, request.getRequestURI());

        model.addAttribute("appId", WxConstant.APPID);
        model.addAttribute("nonceStr", nonceStr);
        model.addAttribute("timestamp", timestamp);
        model.addAttribute("signature", signature);
        model.addAttribute("jsapi_ticket", jsapi_ticket);
        return "/share";
    }
}
