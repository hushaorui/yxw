package com.hsr.yxw.game.config;

import com.hsr.yxw.common.InitializedConfig;
import com.hsr.yxw.game.common.em.card.YxwCardType;
import com.hsr.yxw.game.common.em.card.YxwDLRareLevel;
import com.hsr.yxw.game.info.YxwCardBaseInfo;
import com.hsr.yxw.util.ConfigUtils;

import java.util.*;

public class YxwCardBaseInfoConfig implements InitializedConfig {

    /** 卡牌基础信息集合 */
    private Map<Long, YxwCardBaseInfo> baseInfoMap;
    /** 以List存储的卡牌基础信息集合 */
    private List<YxwCardBaseInfo> baseInfoList;

    public Map<Long, YxwCardBaseInfo> getBaseInfoMap() {
        return baseInfoMap;
    }

    public List<YxwCardBaseInfo> getBaseInfoList() {
        if (baseInfoList == null) {
            baseInfoList = new ArrayList<>(baseInfoMap.values());
        }
        return baseInfoList;
    }

    public void setBaseInfoMap(Map<Long, YxwCardBaseInfo> baseInfoMap) {
        this.baseInfoMap = baseInfoMap;
    }

    public void setBaseInfoList(List<YxwCardBaseInfo> baseInfoList) {
        this.baseInfoList = baseInfoList;
    }

    public List<YxwCardBaseInfo> getBySort(Integer start, Integer size) {
        if (baseInfoList.isEmpty()) {
            return Collections.emptyList();
        }
        if (start == null || size == null || (start <= 1 && size == baseInfoList.size())) {
            return baseInfoList;
        }
        // baseInfoList size = 10 索引：0-9  start = 5 size = 7
        if (start + size > baseInfoList.size()) {
            size = baseInfoList.size();
        }
        // start从1开始
        start --;
        if (start < 0) {
            start = 0;
        }
        return baseInfoList.subList(start, size);
    }

    @Override
    public void init() {
        if (baseInfoMap == null) {
            baseInfoMap = new LinkedHashMap<>();
        }
        YxwCardBaseInfo yxwCardBaseInfo = new YxwCardBaseInfo();
        long sequence = ConfigUtils.getSequenceByClass(YxwCardBaseInfo.class);
        yxwCardBaseInfo.setId(sequence);
        yxwCardBaseInfo.setCardName("斧王");
        yxwCardBaseInfo.setCardType(YxwCardType.MONSTER);
        yxwCardBaseInfo.setDescription("攻击力高的战士");
        yxwCardBaseInfo.setLocalImgUrl("");
        yxwCardBaseInfo.setRemoteImgUrl("https://p.ocgsoft.cn/493.jpg");
        yxwCardBaseInfo.setDlRareLevel(YxwDLRareLevel.UR);
        baseInfoMap.put(sequence, yxwCardBaseInfo);


        baseInfoList = new ArrayList<>();
        baseInfoList.addAll(baseInfoMap.values());
    }
}
