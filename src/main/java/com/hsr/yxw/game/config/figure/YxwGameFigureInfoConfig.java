package com.hsr.yxw.game.config.figure;

import com.hsr.yxw.common.InitializedConfig;
import com.hsr.yxw.game.info.YxwGameFigureInfo;
import com.hsr.yxw.util.ConfigUtils;

import java.util.*;

public class YxwGameFigureInfoConfig implements InitializedConfig {
    /** 初始供玩家选择的人物id列表 */
    private List<YxwGameFigureInfo> firstList;

    private Map<Long, YxwGameFigureInfo> figureMap;

    /** 所有配置 */
    private List<YxwGameFigureInfo> allCfg;

    public Map<Long, YxwGameFigureInfo> getFigureMap() {
        return figureMap;
    }

    public void setFigureMap(Map<Long, YxwGameFigureInfo> figureMap) {
        this.figureMap = figureMap;
    }

    public List<YxwGameFigureInfo> getFirstList() {
        return firstList;
    }

    public void setFirstList(List<YxwGameFigureInfo> firstList) {
        this.firstList = firstList;
    }

    @Override
    public void init() {
        if (figureMap == null) {
            figureMap = new HashMap<>();
        }
        long sequence = ConfigUtils.getSequenceByClass(YxwGameFigureInfo.class);
        figureMap.put(sequence, new YxwGameFigureInfo(
                sequence,
                "人物1",
                "这里是描述2",
                "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3454488484,806353070&fm=26&gp=0.jpg",
                Collections.emptyList()
        ));
        long sequence2 = ConfigUtils.getSequenceByClass(YxwGameFigureInfo.class);
        figureMap.put(sequence2, new YxwGameFigureInfo(
                sequence2,
                "人物2",
                "这里是描述2",
                "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1286265097,4029036494&fm=26&gp=0.jpg",
                Collections.emptyList()
        ));

        if (firstList == null) {
            firstList = new ArrayList<>();
        }
        // 设置默认的初始人物列表
        firstList.add(figureMap.get(sequence));
        firstList.add(figureMap.get(sequence2));


        this.allCfg = new ArrayList<>(figureMap.values());
    }

    @Override
    public List<YxwGameFigureInfo> getAllCfg() {
        return allCfg;
    }
}
