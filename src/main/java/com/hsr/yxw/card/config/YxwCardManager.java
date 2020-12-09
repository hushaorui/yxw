package com.hsr.yxw.card.config;

import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

@Service
public class YxwCardManager {

    /**
     * 读取yml配置，初始化所有卡牌，在spring容器初始化完成后调用
     */
    public void initYxwCard() {
        // TODO
        Yaml yaml = new Yaml();

    }
}
