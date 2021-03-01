package com.hsr.yxw.game.service;

import com.alibaba.fastjson.JSONArray;
import com.hsr.yxw.exception.ServiceException;
import com.hsr.yxw.game.data.YxwGameDataItem;
import com.hsr.yxw.game.data.YxwGameDataType;
import com.hsr.yxw.ws.figure.pojo.YxwGameFigureData;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * yxw玩家数据管理类
 */
@Service
public class YxwGameDataManager {
    private static final Log log = LogFactory.getLog(YxwGameDataManager.class);

    private final YxwGameDataItemService yxwGameDataItemService;
    private final YxwGameInfoManager yxwGameInfoManager;
    /** 保存所有玩家数据缓存的集合，key为玩家id */
    private ConcurrentHashMap<Long, YxwGameDataContainer> gameDataMap = new ConcurrentHashMap<>();

    @Autowired
    public YxwGameDataManager(YxwGameDataItemService yxwGameDataItemService, YxwGameInfoManager yxwGameInfoManager) {
        this.yxwGameDataItemService = yxwGameDataItemService;
        this.yxwGameInfoManager = yxwGameInfoManager;
    }

    /**
     * 更新数据
     * @param yxwGameDataContainer 玩家数据缓存
     * @param dataType 数据的类型
     */
    public boolean update(YxwGameDataContainer yxwGameDataContainer, YxwGameDataType dataType) {
        Collection<Object> dataCollectionValue = yxwGameDataContainer.getDataByType(dataType);
        return update(yxwGameDataContainer, dataType, dataCollectionValue);
    }

    /**
     * 更新数据
     * @param yxwGameDataContainer 玩家数据缓存
     * @param dataType 数据的类型
     * @param dataCollectionValue 数据
     */
    private boolean update(YxwGameDataContainer yxwGameDataContainer, YxwGameDataType dataType, Collection<Object> dataCollectionValue) {
        long userId = yxwGameDataContainer.getUserId();
        // 从数据库中获取该项数据
        YxwGameDataItem yxwGameDataItem = yxwGameDataItemService.selectByUserIdAndType(userId, dataType);
        String dataValue = JSONArray.toJSONString(dataCollectionValue);
        if (yxwGameDataItem == null) {
            // 该数据不存在于数据库，直接更新
            yxwGameDataItem = new YxwGameDataItem();
            yxwGameDataItem.setId(userId);
            yxwGameDataItem.setDataType(dataType);
            yxwGameDataItem.setDateValue(dataValue);
            // 保存进数据库
            try {
                yxwGameDataItemService.insert(yxwGameDataItem);
            } catch (ServiceException e) {
                log.error("保存玩家数据时失败！", e);
                return false;
            }
        } else {
            String dataValueInDB = yxwGameDataItem.getDateValue();
            if (dataValue.length() != dataValueInDB.length() || ! dataValue.equals(dataValueInDB)) {
                // 数据不相同，需要更新
                yxwGameDataItem.setDateValue(dataValue);
                yxwGameDataItemService.update(yxwGameDataItem);
            }
        }
        // 更新缓存中的数据
        yxwGameDataContainer.getPersonalDataMap().put(dataType, dataCollectionValue);
        return true;
    }

    /**
     * 获取一个玩家的所有数据
     * @param userId 玩家id
     * @return 玩家数据容器类对象
     */
    public YxwGameDataContainer getYxwGameDataContainer(long userId) {
        if (gameDataMap.containsKey(userId)) {
            return gameDataMap.get(userId);
        } else {
            // 创建玩家游戏数据容器类
            YxwGameDataContainer yxwGameDataContainer = new YxwGameDataContainer(userId);
            // 根据玩家id，从数据库中查找出所有该玩家的数据
            List<YxwGameDataItem> yxwGameDataItems = yxwGameDataItemService.selectByUserId(userId);
            if (yxwGameDataItems == null) {
                yxwGameDataItems = Collections.emptyList();
            }
            for (YxwGameDataItem yxwGameDataItem : yxwGameDataItems) {
                // 数据的类型
                YxwGameDataType dataType = yxwGameDataItem.getDataType();
                // 数据的值
                String dateJsonString = yxwGameDataItem.getDateValue();
                // 将json数据转换为对象
                List<Object> objects;
                try {
                    objects = (List<Object>) JSONArray.parseArray(dateJsonString, dataType.getDataClass());
                } catch (Exception e) {
                    log.error(String.format("解析数据失败，玩家id：%s，数据类型：%s", userId, dataType), e);
                    objects = new ArrayList<>();
                }
                // 获取容器中的map
                Map<YxwGameDataType, Collection<Object>> personalDataMap = yxwGameDataContainer.getPersonalDataMap();
                // 将转化成功的对象放入到容器类中
                if (personalDataMap.containsKey(dataType)) {
                    log.error(String.format("一个玩家同时拥有两份数据类型相同的数据，玩家id：%s，数据类型：%s", userId, dataType));
                }
                personalDataMap.put(dataType, objects);
            }
            // 初始化缓存数据
            yxwGameDataContainer.init(yxwGameInfoManager);
            // 将该玩家数据放入缓存集合
            gameDataMap.put(userId, yxwGameDataContainer);
            return yxwGameDataContainer;
        }
    }

    /**
     * 添加一个人物
     * @param figureData 人物数据
     */
    public String addYxwGameFigureData(Long userId, YxwGameFigureData figureData) {
        YxwGameDataContainer dataContainer = getYxwGameDataContainer(userId);
        Map<Long, YxwGameFigureData> figureMap = dataContainer.getFigureMap();
        if (figureMap.containsKey(figureData.getCfgId())) {
            log.error(String.format("添加人物数据失败，该人物数据已存在，userId:%s,cfgId:%s", userId, figureData.getCfgId()));
            return yxwGameInfoManager.getLanguageString(1L);
        }
        YxwGameDataType dataType = YxwGameDataType.Figure;
        Collection<Object> dataCollectionValue = dataContainer.getDataByType(dataType);
        dataCollectionValue.add(figureData);
        boolean result = update(dataContainer, dataType, dataCollectionValue);
        if (result) {
            figureMap.put(figureData.getCfgId(), figureData);
            // 更新成功
            return null;
        } else {
            return yxwGameInfoManager.getLanguageString(1L);
        }
    }
}
