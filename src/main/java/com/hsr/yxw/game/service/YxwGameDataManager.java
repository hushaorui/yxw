package com.hsr.yxw.game.service;

import com.alibaba.fastjson.JSONArray;
import com.hsr.yxw.exception.ServiceException;
import com.hsr.yxw.game.data.YxwGameDataContainer;
import com.hsr.yxw.game.data.YxwGameDataItem;
import com.hsr.yxw.game.data.YxwGameDataType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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
     * @param userId 玩家id
     * @param dataType 数据的类型
     * @param dataCollectionValue 数据
     */
    public boolean update(long userId, YxwGameDataType dataType, Collection<Long> dataCollectionValue) {
        // 获取数据容器
        YxwGameDataContainer yxwGameDataContainer = getYxwGameDataContainer(userId);
        // 从数据库中获取该项数据
        YxwGameDataItem yxwGameDataItem = yxwGameDataItemService.selectByUserIdAndType(userId, dataType);
        String dataValue = JSONArray.toJSONString(dataCollectionValue);
        if (yxwGameDataItem == null) {
            // 该数据不存在于，直接更新
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
            // 更新缓存中的数据
            yxwGameDataContainer.getPersonalDataMap().put(dataType, dataCollectionValue);
        } else {
            String dataValueInDB = yxwGameDataItem.getDateValue();
            if (dataValue.length() != dataValueInDB.length() || ! dataValue.equals(dataValueInDB)) {
                // 数据不相同，需要更新
                yxwGameDataItem.setDateValue(dataValue);
                yxwGameDataItemService.update(yxwGameDataItem);
                // 更新缓存中的数据
                yxwGameDataContainer.getPersonalDataMap().put(dataType, dataCollectionValue);
            }
        }
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
            YxwGameDataContainer yxwGameDataContainer = new YxwGameDataContainer();
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
                List<?> objects;
                try {
                    objects = JSONArray.parseArray(dateJsonString, Long.class);
                } catch (Exception e) {
                    log.error(String.format("解析数据失败，玩家id：%s，数据类型：%s", userId, dataType), e);
                    objects = Collections.emptyList();
                }
                // 获取容器中的map
                Map<YxwGameDataType, Collection<?>> personalDataMap = yxwGameDataContainer.getPersonalDataMap();
                // 将转化成功的对象放入到容器类中
                if (personalDataMap.containsKey(dataType)) {
                    log.error(String.format("一个玩家同时拥有两份数据类型相同的数据，玩家id：%s，数据类型：%s", userId, dataType));
                }
                personalDataMap.put(dataType, objects);
            }
            // 初始化缓存数据
            //yxwGameDataContainer.
            // 将该玩家数据放入缓存集合
            gameDataMap.put(userId, yxwGameDataContainer);
            return yxwGameDataContainer;
        }
    }
}
