package com.hsr.yxw.run;

import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class SpringBootUtil {
    private static WebServerApplicationContext applicationContext;

    @EventListener(WebServerInitializedEvent.class)
    public void onWebServerReady(WebServerInitializedEvent event) {
        System.out.println("1.当前WebServer实现类为："+event.getWebServer().getClass().getName());
        applicationContext = event.getApplicationContext();
        int port = applicationContext.getWebServer().getPort();
        String name = applicationContext.getWebServer().getClass().getName();
        System.out.println("1.容器端口"+port+",实现类-"+name);
    }
    public static int getServerPort() {
        return applicationContext.getWebServer().getPort();
    }
}