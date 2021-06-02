package com.hsr.game.yxw.service;

import com.hsr.game.yxw.data.YxwGameDataType;
import com.hsr.game.yxw.info.YxwGameFigureInfo;
import com.hsr.game.ws.yxw.base.pojo.YxwGameMainData;
import com.hsr.game.ws.yxw.figure.pojo.YxwGameFigureData;
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
    /** 基础数据缓存 */
    private YxwGameMainData mainData;

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
    Collection<Object> getDataByTypeDefaultEmptyList(YxwGameDataType dataType) {
        return personalDataMap.computeIfAbsent(dataType, key -> new ArrayList<>());
    }

    private Collection<Object> getDataByType(YxwGameDataType dataType) {
        return personalDataMap.get(dataType);
    }

    //==============以下=============所有对外提供的公共get方法===============以下=======-====//
    /**
     * 获取玩家所有拥有的人物数据，除YxwGameDataManager外请勿对此方法获取的对象进行查找和遍历以外的操作
     */
    public Map<Long, YxwGameFigureData> getFigureMap() {
        return figureMap;
    }

    /**
     * 获取基础数据
     */
    public YxwGameMainData getMainData() {
        return mainData;
    }
    //==============以上=============所有对外提供的公共get方法===============以上============//

    private void initMainData(YxwGameInfoManager yxwGameInfoManager) {
        YxwGameDataType figureType = YxwGameDataType.Main;
        if (cacheInitSet.contains(figureType)) {
            return;
        } else {
            cacheInitSet.add(figureType);
        }
        Collection<Object> mainDataList = getDataByType(figureType);
        if (mainDataList == null || mainDataList.isEmpty()) {
            // 没有数据，新建一个数据
            this.mainData = new YxwGameMainData();
        } else {
            Object next = mainDataList.iterator().next();
            if (next instanceof  YxwGameMainData) {
                this.mainData = (YxwGameMainData) next;
            } else {
                // 数据存在问题
                log.error(String.format("解析数据存在问题，实际类型:%s,需要的类型:%s",
                        next == null ? "null":next.getClass().getName(), YxwGameMainData.class.getName()));
                this.mainData = new YxwGameMainData();
            }
        }
    }
    private void initFigureData(YxwGameInfoManager yxwGameInfoManager) {
        YxwGameDataType figureType = YxwGameDataType.Figure;
        if (cacheInitSet.contains(figureType)) {
            return;
        } else {
            cacheInitSet.add(figureType);
        }
        // 获取人物列表
        Collection<Object> figureList = getDataByTypeDefaultEmptyList(figureType);
        Map<Long, YxwGameFigureData> figures = new ConcurrentHashMap<>();
        for (Object figureObj : figureList) {
            YxwGameFigureData figureData = (YxwGameFigureData) figureObj;
            YxwGameFigureInfo figureInfoCfg = yxwGameInfoManager.getFigureInfoCfgById(figureData.getCfgId());
            if (figureInfoCfg == null) {
                log.error(String.format("未找到yxw人物配置，id：%s", figureData.getCfgId()));
                continue;
            }
            figures.put(figureData.getCfgId(), figureData);
        }
        this.figureMap = figures;
    }

    void init(YxwGameInfoManager yxwGameInfoManager) {
        initMainData(yxwGameInfoManager);
        initFigureData(yxwGameInfoManager);
        // TODO 后续所有类型的数据都需要在这里初始化
    }

    public long getUserId() {
        return userId;
    }

}
