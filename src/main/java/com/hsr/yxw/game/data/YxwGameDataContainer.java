package com.hsr.yxw.game.data;

import com.hsr.yxw.game.info.YxwGameFigure;
import com.hsr.yxw.game.service.YxwGameInfoManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 一个玩家的所有数据
 */
public class YxwGameDataContainer {
    private static final Log log = LogFactory.getLog(YxwGameDataContainer.class);

    /** 个人的所有数据，只存储了id的集合 */
    private Map<YxwGameDataType, Collection<Long>> personalDataMap = new ConcurrentHashMap<>();
    /** 缓存数据是否已经初始化的集合 */
    private Set<YxwGameDataType> cacheInitSet = Collections.synchronizedSet(new HashSet<>());
    /** 人物数据缓存 */
    private Map<Long, YxwGameFigure> figureMap = new ConcurrentHashMap<>();

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

    public Map<Long, YxwGameFigure> getFigureMap() {
        return figureMap;
    }

    public void initFigure(YxwGameInfoManager yxwGameInfoManager) {
        YxwGameDataType figureType = YxwGameDataType.Figure;
        if (cacheInitSet.contains(figureType)) {
            return;
        } else {
            cacheInitSet.add(figureType);
        }
        // 获取人物列表
        Collection<Long> figureIdList = getDataByType(figureType);
        Map<Long, YxwGameFigure> figures = new ConcurrentHashMap<>();
        for (Long figureId : figureIdList) {
            YxwGameFigure yxwGameFigure = yxwGameInfoManager.findFigureById(figureId);
            if (yxwGameFigure == null) {
                log.error(String.format("未找到yxw人物，id：%s", figureId));
                continue;
            }
            figures.put(figureId, yxwGameFigure);
        }
        if (figures.isEmpty()) {
            // 当没有任何一个人物数据时，给定一些默认值，或由玩家选择
        }
        this.figureMap = figures;
    }
}
