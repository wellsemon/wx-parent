package com.semon.wx.web.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.semon.wx.caller.caller.WxCaller;
import com.semon.wx.web.bean.WxSNSUser;
import com.semon.wx.web.service.WxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxUserServiceImpl implements WxUserService {
    @Autowired
    private WxCaller wxCaller;

    @Override
    public WxSNSUser wxsnsuser(String accessToken, String openId) {
        String snsuser = wxCaller.snsuser(accessToken, openId);

        JSONObject jsonObject = JSONObject.parseObject(snsuser);

        WxSNSUser wxSNSUser = null;
        if (null != jsonObject) {
            wxSNSUser = new WxSNSUser();
            // 用户的标识
            wxSNSUser.setOpenId(jsonObject.getString("openid"));
            // 昵称
            wxSNSUser.setNickname(jsonObject.getString("nickname"));
            // 性别（1是男性，2是女性，0是未知）
            wxSNSUser.setSex(jsonObject.getInteger("sex"));
            // 用户所在国家
            wxSNSUser.setCountry(jsonObject.getString("country"));
            // 用户所在省份
            wxSNSUser.setProvince(jsonObject.getString("province"));
            // 用户所在城市
            wxSNSUser.setCity(jsonObject.getString("city"));
            // 用户头像
            wxSNSUser.setHeadImgUrl(jsonObject.getString("headimgurl"));
            // 用户特权信息
            wxSNSUser.setPrivilegeList(JSONArray.parseArray(jsonObject.getString("privilege"), String.class));
        }
        return wxSNSUser;
    }
}
