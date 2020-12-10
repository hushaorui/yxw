package com.hsr.yxw.game.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 一个玩家的所有数据
 */
public class YxwGameDataContainer {

    /** 个人的所有数据 */
    private Map<YxwGameDataType, Collection<Long>> personalDataMap = new ConcurrentHashMap<>();

    public Map<YxwGameDataType, Collection<Long>> getPersonalDataMap() {
        return personalDataMap;
    }

    /**
     * 获取非null数据
     * @param dataType 数据的类型
     */
    public Collection<Long> getDataByType(YxwGameDataType dataType) {
        return personalDataMap.computeIfAbsent(dataType, key -> new ArrayList<>());
    }

}
