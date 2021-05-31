package com.hsr.yxw.game.config.card;

import com.hsr.yxw.common.InitializedConfig;
import com.hsr.yxw.game.info.YxwMonsterCardInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YxwMonsterCardInfoConfig implements InitializedConfig {

    private Map<Long, YxwMonsterCardInfo> monsterInfoMap;

    public Map<Long, YxwMonsterCardInfo> getMonsterInfoMap() {
        return monsterInfoMap;
    }

    public void setMonsterInfoMap(Map<Long, YxwMonsterCardInfo> monsterInfoMap) {
        this.monsterInfoMap = monsterInfoMap;
    }
    private List<YxwMonsterCardInfo> allCfg;

    @Override
    public void init() {
        if (monsterInfoMap == null) {
            monsterInfoMap = new HashMap<>();
        }


        this.allCfg = new ArrayList<>(monsterInfoMap.values());
    }

    @Override
    public List<YxwMonsterCardInfo> getAllCfg() {
        return allCfg;
    }
}
