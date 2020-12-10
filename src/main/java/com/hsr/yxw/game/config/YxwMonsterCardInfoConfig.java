package com.hsr.yxw.game.config;

import com.hsr.yxw.common.InitializedConfig;
import com.hsr.yxw.game.info.YxwMonsterCardInfo;

import java.util.HashMap;
import java.util.Map;

public class YxwMonsterCardInfoConfig implements InitializedConfig {

    private Map<Long, YxwMonsterCardInfo> monsterInfoMap;

    public Map<Long, YxwMonsterCardInfo> getMonsterInfoMap() {
        return monsterInfoMap;
    }

    public void setMonsterInfoMap(Map<Long, YxwMonsterCardInfo> monsterInfoMap) {
        this.monsterInfoMap = monsterInfoMap;
    }

    @Override
    public void init() {
        if (monsterInfoMap == null) {
            monsterInfoMap = new HashMap<>();
        }

    }
}
