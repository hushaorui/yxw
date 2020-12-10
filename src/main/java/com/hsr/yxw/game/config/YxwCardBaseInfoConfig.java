package com.hsr.yxw.game.config;

import com.hsr.yxw.game.common.em.card.YxwCardType;
import com.hsr.yxw.game.common.em.card.YxwDLRareLevel;
import com.hsr.yxw.game.info.YxwCardBaseInfo;
import com.hsr.yxw.common.InitializedConfig;
import com.hsr.yxw.util.ConfigUtils;

import java.util.HashMap;
import java.util.Map;

public class YxwCardBaseInfoConfig implements InitializedConfig {

    /** 卡牌基础信息集合 */
    private Map<Long, YxwCardBaseInfo> baseInfoMap;

    public Map<Long, YxwCardBaseInfo> getBaseInfoMap() {
        return baseInfoMap;
    }

    public void setBaseInfoMap(Map<Long, YxwCardBaseInfo> baseInfoMap) {
        this.baseInfoMap = baseInfoMap;
    }

    @Override
    public void init() {
        if (baseInfoMap == null) {
            baseInfoMap = new HashMap<>();
        }
        YxwCardBaseInfo yxwCardBaseInfo = new YxwCardBaseInfo();
        long sequence = ConfigUtils.getSequenceByClass(YxwCardBaseInfo.class);
        yxwCardBaseInfo.setId(sequence);
        yxwCardBaseInfo.setCardName("斧王");
        yxwCardBaseInfo.setCardType(YxwCardType.MONSTER);
        yxwCardBaseInfo.setDescription("攻击力高的战士");
        yxwCardBaseInfo.setLocalImgUrl("");
        yxwCardBaseInfo.setRemoteImgUrl("https://baike.baidu.com/pic/%E5%88%80%E6%96%A7%E6%88%98%E5%A3%AB/3730906/0/91ae68c69804f6089c163d5b#aid=0&pic=91ae68c69804f6089c163d5b");
        yxwCardBaseInfo.setDlRareLevel(YxwDLRareLevel.UR);
        baseInfoMap.put(sequence, yxwCardBaseInfo);
    }
}
