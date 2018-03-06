package com.semon.wx.web;

import com.alibaba.fastjson.JSONObject;
import com.semon.common.util.HttpClientUtil;
import com.semon.wx.common.constant.WxConstant;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class WechatAPITest {
    public static final Logger LOGGER = Logger.getLogger(WechatAPITest.class.getName());

    public static final String WECHAT_API_URL_PREFIX = "https://api.weixin.qq.com/";
    public static final String GRANT_TYPE_CLIENT_CREDENTIAL = "client_credential";
    public static final String APPID = "wxc033ede3b33abbad";
    public static final String SECRET = "b04b22dac46c7106d1212571051d5fda";

    public static void main(String[] args) {
//        getAccessToken();
//        getWechatServerIPs();
//        addKfAccount();
//        sendKfMsg();
//        getMsgTemplates();
        batchGetMaterial();
    }

    public static String getAccessToken() {
        Map<String, String> param = new HashMap<String, String>();
        param.put("grant_type", GRANT_TYPE_CLIENT_CREDENTIAL);
        param.put("appid", APPID);
        param.put("secret", SECRET);

        String repstr = HttpClientUtil.doGet(buildURL(WECHAT_API_URL_PREFIX, "cgi-bin/token"), param);

        JSONObject jsonObject = JSONObject.parseObject(repstr);
        String accessToken = jsonObject.getString("access_token");
        LOGGER.info(accessToken);
        return accessToken;
    }

    public static void getWechatServerIPs() {
        Map<String, String> param = new HashMap<String, String>();

        param.put("access_token", getAccessToken());

        String repstr = HttpClientUtil.doGet(buildURL(WECHAT_API_URL_PREFIX, "cgi-bin/getcallbackip"), param);
        LOGGER.info(repstr);
        JSONObject serversJsonObj = JSONObject.parseObject(repstr);
        LOGGER.info("" + (serversJsonObj.getString("ip_list").split(",").length + 1));
    }

    /**
     * 需开通，无法测试
     */
    public static void addKfAccount() {
        Map<String, String> param = new HashMap<String, String>();
        JSONObject jsonObject = JSONObject.parseObject(getAccessToken());
        param.put("access_token", jsonObject.getString("access_token"));
        param.put("kf_account", "test@test.com");
        param.put("nickname", "kefu1");
        param.put("password", "kefu1");

        String repstr = HttpClientUtil.doPost(buildURL(WECHAT_API_URL_PREFIX, "customservice/kfaccount/add"), param);
        LOGGER.info(repstr);
    }

    public static void sendKfMsg() {
        String json = "";

        JSONObject totalJsonObject = new JSONObject();
//        totalJsonObject.put("")
        //alert:微信公众测试号下的openid并非用户发送消息时得到的FromUserName，而是测试号web页用户列表下用户的微信号
        totalJsonObject.put("touser", "oYDOE1FA47DjJ7IUPtS5gtvUcCn0");
        totalJsonObject.put("msgtype", WxConstant.MsgType.TEXT.value());

        JSONObject textJsonObject = new JSONObject();
        textJsonObject.put("content", "ke fu sent msg.");
        totalJsonObject.put("text", textJsonObject);

        json = totalJsonObject.toJSONString();
        String repstr = HttpClientUtil.doPostJson(buildURL(WECHAT_API_URL_PREFIX, "cgi-bin/message/custom/send?access_token=" + getAccessToken()), json);
        LOGGER.info(repstr);
    }

    public static void getMsgTemplates() {
        Map<String, String> param = new HashMap<String, String>();

        param.put("access_token", getAccessToken());

        String repstr = HttpClientUtil.doGet(buildURL(WECHAT_API_URL_PREFIX, "cgi-bin/template/get_all_private_template"), param);
        LOGGER.info(repstr);
    }

    public static String buildURL(String prefix, String apiName) {
        return prefix + apiName;
    }

    public static void batchGetMaterial() {
        Map<String, String> param = new HashMap<String, String>();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "news");
        jsonObject.put("offset", 0);
        jsonObject.put("count", 2);

        String repstr = HttpClientUtil.doPostJson(buildURL(WECHAT_API_URL_PREFIX, "cgi-bin/material/batchget_material?access_token=" + getAccessToken()), jsonObject.toJSONString());
        LOGGER.info(repstr);
    }
}
