package com.semon.wx.web;

import com.semon.wx.caller.caller.WxCaller;
import com.semon.wx.caller.caller.WxOAuth2Caller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.AdviceMode;

@SpringBootApplication(scanBasePackages = "com.semon.wx")
@EnableCaching
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
