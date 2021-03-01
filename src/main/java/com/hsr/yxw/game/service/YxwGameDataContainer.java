package com.hsr.yxw.game.service;

import com.hsr.yxw.game.data.YxwGameDataType;
import com.hsr.yxw.ws.figure.pojo.YxwGameFigureData;
import com.hsr.yxw.game.info.YxwGameFigureInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 一个玩家的所有数据（缓存）
 */
public class YxwGameDataContainer {
    private static final Log log = LogFactory.getLog(YxwGameDataContainer.class);
    /** 玩家id */
    private final long userId;
    /** 个人的所有数据，只存储了id的集合 */
    private Map<YxwGameDataType, Collection<Object>> personalDataMap = new ConcurrentHashMap<>();
    /** 缓存数据是否已经初始化的集合 */
    private Set<YxwGameDataType> cacheInitSet = Collections.synchronizedSet(new HashSet<>());
    /** 人物数据缓存 */
    private Map<Long, YxwGameFigureData> figureMap = new ConcurrentHashMap<>();

    Map<YxwGameDataType, Collection<Object>> getPersonalDataMap() {
        return personalDataMap;
    }

    YxwGameDataContainer(long userId) {
        this.userId = userId;
    }

    /**
     * 获取非null数据
     * @param dataType 数据的类型
     */
    Collection<Object> getDataByType(YxwGameDataType dataType) {
        return personalDataMap.computeIfAbsent(dataType, key -> new ArrayList<>());
    }

    /**
     * 获取玩家所有拥有的人物数据，除YxwGameDataManager外请勿对此方法获取的对象进行查找和遍历以外的操作
     */
    public Map<Long, YxwGameFigureData> getFigureMap() {
        return figureMap;
    }

    private void initFigureData(YxwGameInfoManager yxwGameInfoManager) {
        YxwGameDataType figureType = YxwGameDataType.Figure;
        if (cacheInitSet.contains(figureType)) {
            return;
        } else {
            cacheInitSet.add(figureType);
        }
        // 获取人物列表
        Collection<Object> figureList = getDataByType(figureType);
        Map<Long, YxwGameFigureData> figures = new ConcurrentHashMap<>();
        for (Object figureObj : figureList) {
            YxwGameFigureData figureData = (YxwGameFigureData) figureObj;
            YxwGameFigureInfo figureInfoCfg = yxwGameInfoManager.getFigureInfoCfgById(figureData.getCfgId());
            if (figureInfoCfg == null) {
                log.error(String.format("未找到yxw人物配置，id：%s", figureData.getCfgId()));
                continue;
            }
            figureData.setFigureInfo(figureInfoCfg);
            figures.put(figureData.getCfgId(), figureData);
        }
        this.figureMap = figures;
    }

    void init(YxwGameInfoManager yxwGameInfoManager) {
        initFigureData(yxwGameInfoManager);
        // TODO
    }

    public long getUserId() {
        return userId;
    }

}
