package com.semon.wx.handler.bean;

public class WxHandlerResult {
    private Integer code;
    private String content;

    public WxHandlerResult() {}

    public WxHandlerResult(Integer code, String content) {
        this.code = code;
        this.content = content;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
