package com.semon.wx.common.constant;

public class WxConstant {
    public static final String TOKEN = "wechat";

    public static final String URL_PREFIX_WECHAT_API = "https://api.weixin.qq.com/";
    public static final String URL_PREFIX_WECHAT_OPEN = "https://open.weixin.qq.com/";

    public static final String GRANT_TYPE_CLIENT_CREDENTIAL = "client_credential";
    public static final String APPID = "wxc033ede3b33abbad";
    public static final String SECRET = "b04b22dac46c7106d1212571051d5fda";

    public enum MsgType {
        TEXT("text");

        private String msgType;

        private MsgType(String msgType) {
            this.msgType = msgType;
        }

        public String value() {
            return this.msgType;
        }
    }

    public enum HandlerResultCode {
        OK(200);
        private Integer code;

        private HandlerResultCode(Integer code) {
            this.code = code;
        }

        public Integer value() {
            return this.code;
        }
    }
}
