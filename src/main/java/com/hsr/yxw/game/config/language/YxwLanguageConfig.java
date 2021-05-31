package com.hsr.yxw.game.config.language;

import com.hsr.yxw.common.InitializedConfig;
import com.hsr.yxw.game.info.YxwLanguageInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YxwLanguageConfig implements InitializedConfig {

    private Map<Long, YxwLanguageInfo> languageMap;

    private List<YxwLanguageInfo> allCfg;

    public Map<Long, YxwLanguageInfo> getLanguageMap() {
        return languageMap;
    }

    public void setLanguageMap(Map<Long, YxwLanguageInfo> languageMap) {
        this.languageMap = languageMap;
    }

    @Override
    public void init() {
        if (languageMap == null) {
            languageMap = new HashMap<>();
        }
        languageMap.put(1L, new YxwLanguageInfo(1L, "数据错误"));
        languageMap.put(2L, new YxwLanguageInfo(2L, "找不到配置，id：%s"));
        languageMap.put(3L, new YxwLanguageInfo(3L, "未知货币类型，id：%s"));

        languageMap.put(100001L, new YxwLanguageInfo(100001L, "选择初始人物失败，已存在人物"));
        languageMap.put(100002L, new YxwLanguageInfo(100002L, "选择初始人物失败，该人物目前不可解锁"));


        this.allCfg = new ArrayList<>(languageMap.values());
    }

    @Override
    public List<YxwLanguageInfo> getAllCfg() {
        return allCfg;
    }
}
