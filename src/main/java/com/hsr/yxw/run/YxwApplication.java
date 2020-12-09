package com.hsr.yxw.run;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@SpringBootConfiguration
@ComponentScan(basePackages = {"com.hsr.yxw"})
@MapperScan(basePackages = "com.hsr.yxw.mapper")
public class YxwApplication {

    public static void main(String[] args) {
        SpringApplication.run(YxwApplication.class, args);
    }

}
