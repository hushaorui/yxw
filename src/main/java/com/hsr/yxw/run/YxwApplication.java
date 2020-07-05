package com.hsr.yxw.run;

import com.hsr.yxw.config.YxwCardConfig;
import com.hsr.yxw.config.YxwMainConfig;
import com.hsr.yxw.util.ConfigUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;

@SpringBootApplication
@SpringBootConfiguration
@ComponentScan(basePackages = {"com.hsr.yxw.controller", "com.hsr.yxw.service",
        "com.hsr.yxw.interceptor", "com.hsr.yxw.run", "com.hsr.yxw.ws"})
public class YxwApplication {

    @Bean
    public YxwMainConfig YxwMainConfig() throws IOException, IllegalAccessException, InstantiationException {
        return ConfigUtils.getYxwConfig(YxwMainConfig.class, true);
    }
    @Bean
    public YxwCardConfig YxwCardConfig() throws IOException, IllegalAccessException, InstantiationException {
        return ConfigUtils.getYxwConfig(YxwCardConfig.class, false);
    }
    public static void main(String[] args) {
        SpringApplication.run(YxwApplication.class, args);
    }

}
