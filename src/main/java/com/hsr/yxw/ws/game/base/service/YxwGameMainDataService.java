package com.hsr.yxw.ws.game.base.service;

import com.hsr.yxw.game.service.YxwGameDataContainer;
import com.hsr.yxw.game.service.YxwGameDataManager;
import com.hsr.yxw.game.service.YxwGameInfoManager;
import com.hsr.yxw.ws.game.base.common.YxwGameCurrencyType;
import com.hsr.yxw.ws.game.base.common.YxwGameMainDataHelper;
import com.hsr.yxw.ws.game.base.pojo.YxwGameMainData;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YxwGameMainDataService {
    private static final Log log = LogFactory.getLog(YxwGameMainDataService.class);

    private YxwGameInfoManager yxwGameInfoManager;
    private YxwGameDataManager yxwGameDataManager;

    @Autowired
    public YxwGameMainDataService(YxwGameInfoManager yxwGameInfoManager, YxwGameDataManager yxwGameDataManager) {
        this.yxwGameInfoManager = yxwGameInfoManager;
        this.yxwGameDataManager = yxwGameDataManager;
    }

    /**
     * 获取基础数据
     * @param userId 玩家id
     * @return 基础数据
     */
    public YxwGameMainData getMainData(long userId) {
        return yxwGameDataManager.getYxwGameDataContainer(userId).getMainData();
    }

    /**
     * 添加或消耗特殊货币
     * @param userId 玩家id
     * @param currencyType 货币类型
     * @return 成功返回null，失败返回tips
     */
    public String updateCurrency(long userId, int currencyType, long count) {
        YxwGameCurrencyType currencyTypeEnum = YxwGameCurrencyType.getById(currencyType);
        if (currencyTypeEnum == null) {
            // 不存在此货币类型
            return String.format(yxwGameInfoManager.getLanguageString(3L), currencyType);
        }
        YxwGameDataContainer yxwGameDataContainer = yxwGameDataManager.getYxwGameDataContainer(userId);
        YxwGameMainData mainData = yxwGameDataContainer.getMainData();
        long currentCount = YxwGameMainDataHelper.getCurrencyCount(mainData, currencyTypeEnum);
        currentCount += count;
        if (currentCount < 0) {
            currentCount = 0;
        }
        if (currentCount > currencyTypeEnum.getUpperLimit()) {
            // 超过上限，这里直接忽略掉
            currentCount = currencyTypeEnum.getUpperLimit();
        }
        YxwGameMainDataHelper.updateCurrency(mainData, currencyTypeEnum, currentCount);
        boolean updateResult = yxwGameDataManager.update(yxwGameDataContainer, mainData);
        if (updateResult) {
            return null;
        } else {
            // 更新失败
            return yxwGameInfoManager.getLanguageString(1L);
        }
    }
}
