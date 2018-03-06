package com.semon.wx.web.controller;

import com.semon.wx.web.bean.WxOauth2Token;
import com.semon.wx.web.bean.WxSNSUser;
import com.semon.wx.web.service.WxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WxUserController {
    @Autowired
    private WxUserService wxUserService;

    @RequestMapping("/wxsnsuser")
    public WxSNSUser wxsnsuser(@RequestParam("wxOauth2Token")String  wxOauth2Token,@RequestParam("openid")String
            openid){
       return wxUserService.wxsnsuser(wxOauth2Token,openid);
    }
}
