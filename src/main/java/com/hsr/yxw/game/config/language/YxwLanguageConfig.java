package com.hsr.yxw.game.config.language;

import com.hsr.yxw.common.InitializedConfig;
import com.hsr.yxw.game.info.YxwLanguageInfo;

import java.util.HashMap;
import java.util.Map;

public class YxwLanguageConfig implements InitializedConfig {

    private Map<Long, YxwLanguageInfo> languageMap;

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

        languageMap.put(100001L, new YxwLanguageInfo(100001L, "选择初始人物失败，已存在人物"));
    }
}
