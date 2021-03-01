package com.hsr.yxw.ws.figure.service;

import com.hsr.yxw.game.service.YxwGameDataContainer;
import com.hsr.yxw.game.info.YxwGameFigureInfo;
import com.hsr.yxw.game.service.YxwGameDataManager;
import com.hsr.yxw.game.service.YxwGameInfoManager;
import com.hsr.yxw.ws.figure.pojo.YxwGameFigureData;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class YxwGameFigureService {
    private static final Log log = LogFactory.getLog(YxwGameFigureService.class);

    private YxwGameInfoManager yxwGameInfoManager;
    private YxwGameDataManager yxwGameDataManager;
    @Autowired
    public YxwGameFigureService(YxwGameInfoManager yxwGameInfoManager, YxwGameDataManager yxwGameDataManager) {
        this.yxwGameInfoManager = yxwGameInfoManager;
        this.yxwGameDataManager = yxwGameDataManager;
    }

    /**
     * 当没有任何一个人物数据时，给定一些默认值，或由玩家选择
     * @param customFigureId 玩家自己选择的初始人物id
     * @return 当成功时返回null，当失败时返回提示信息
     */
    public String chooseFirstFigure(Long userId, long customFigureId) {
        YxwGameDataContainer dataContainer = yxwGameDataManager.getYxwGameDataContainer(userId);
        Map<Long, YxwGameFigureData> figureMap = dataContainer.getFigureMap();
        if (! figureMap.isEmpty()) {
            // 已有人物数据，则此次选择失败
            log.error(String.format("已有人物数据，选择失败，userId:%s,figureId:%s", userId, customFigureId));
            return yxwGameInfoManager.getLanguageString(100001L);
        }
        YxwGameFigureInfo figureInfo = yxwGameInfoManager.getFigureInfoCfgById(customFigureId);
        if (figureInfo == null) {
            // 找不到配置
            log.error(String.format("找不到人物配置，userId:%s,figureId:%s", userId, customFigureId));
            return String.format(yxwGameInfoManager.getLanguageString(2L), customFigureId);
        }
        YxwGameFigureData figureData = new YxwGameFigureData();
        figureData.setCfgId(customFigureId);
        figureData.setFigureInfo(figureInfo);
        figureData.setLevel(0L);
        figureData.setExp(0L);
        return yxwGameDataManager.addYxwGameFigureData(userId, figureData);
    }
}
