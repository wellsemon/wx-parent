package com.semon.wx.web.controller;

import com.semon.wx.common.constant.WxConstant;
import com.semon.wx.web.bean.WxOauth2Token;
import com.semon.wx.web.service.WxOAuth2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

/**
 * TODO 可进一步转换为特定 URI 的 filter 来进行网页授权
 * TODO 记录网页、access_token、refresh_token 关联，并使用未过时的 refresh_token 更新已过时 access_token，否则重新获取 access_token 和 refresh_token
 * TODO 每次使用网页 access_token，有必要检查有效性
 */
@Controller
public class WxOAuth2Controller {
    @Autowired
    private WxOAuth2Service wxOAuth2Service;

    /**
     * auto build redirect uri when auth2 web page to ensure correct param order
     *
     * @param redirect_uri
     * @param response_type
     * @param scope
     * @param state 实际 url
     * @return
     */
    @RequestMapping(value = "/wxoauth2uri")
    public String wxoauth2uri(@RequestParam("redirect_uri") String redirect_uri, @RequestParam("response_type") String
            response_type,
                              @RequestParam("scope") String scope, @RequestParam(value = "state")
                                    String state, RedirectAttributes redirectAttr) throws IOException {
        String oauth2URI = wxOAuth2Service.redirectURI();

        redirectAttr.addAttribute("appid", WxConstant.APPID);
        redirectAttr.addAttribute("redirect_uri", redirect_uri);
        redirectAttr.addAttribute("response_type", response_type);
        redirectAttr.addAttribute("scope", scope);
        redirectAttr.addAttribute("state", state);

        return "redirect:" + oauth2URI + "#wechat_redirect";
    }

    @RequestMapping(value = "/wxoauth2token")
    public String wxoauth2token(@RequestParam("code")String code, @RequestParam("state")String state,
                                RedirectAttributes
            redirectAttr){
        WxOauth2Token wxOauth2Token = wxOAuth2Service.wxoauth2token(code);

        redirectAttr.addAttribute("wxOauth2Token", wxOauth2Token.getAccessToken());
        redirectAttr.addAttribute("openid", wxOauth2Token.getOpenId());

        return "redirect:/" + state;
    }
}
