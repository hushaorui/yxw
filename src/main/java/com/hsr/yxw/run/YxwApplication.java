package com.hsr.yxw.run;

import com.hsr.yxw.config.YxwCardConfig;
import com.hsr.yxw.util.ConfigUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;

@SpringBootApplication
@SpringBootConfiguration
@ComponentScan(basePackages = {"com.hsr.yxw"})
@MapperScan(basePackages = "com.hsr.yxw.mapper")
public class YxwApplication {

    @Bean
    public YxwCardConfig YxwCardConfig() throws IOException, IllegalAccessException, InstantiationException {
        return ConfigUtils.getYxwConfig(YxwCardConfig.class, false);
    }
    public static void main(String[] args) {
        SpringApplication.run(YxwApplication.class, args);
    }

}
