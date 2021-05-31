package com.hsr.yxw.game.config.goods;

import com.hsr.yxw.common.InitializedConfig;
import com.hsr.yxw.game.info.YxwGoodsInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YxwGoodsInfoConfig implements InitializedConfig {
    private Map<Long, YxwGoodsInfo> goodsMap;

    private List<YxwGoodsInfo> allCfg;

    public Map<Long, YxwGoodsInfo> getGoodsMap() {
        return goodsMap;
    }

    public void setGoodsMap(Map<Long, YxwGoodsInfo> goodsMap) {
        this.goodsMap = goodsMap;
    }

    @Override
    public void init() {
        // 一些数据的初始化
        if (goodsMap == null) {
            goodsMap = new HashMap<>();
        }


        this.allCfg = new ArrayList<>(goodsMap.values());
    }

    @Override
    public List<YxwGoodsInfo> getAllCfg() {
        return allCfg;
    }
}
