package com.hsr.game.run;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@SpringBootConfiguration
@ComponentScan(basePackages = {"com.hsr.game"})
@MapperScan(basePackages = "com.hsr.game.mapper")
public class YxwApplication {

    public static void main(String[] args) {
        SpringApplication.run(YxwApplication.class, args);
    }

}
