package com.hsr.yxw.game.config.figure;

import com.hsr.yxw.common.InitializedConfig;
import com.hsr.yxw.game.info.YxwGameFigureInfo;
import com.hsr.yxw.util.ConfigUtils;

import java.util.*;

public class YxwGameFigureInfoConfig implements InitializedConfig {
    /** 初始供玩家选择的人物id列表 */
    private List<YxwGameFigureInfo> firstList;

    private Map<Long, YxwGameFigureInfo> figureMap;

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
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1607583627119&di=fc4044c018df7abb0a692d21253482a6&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fforum%2Fw%3D580%2Fsign%3D3cbb1e9dddca7bcb7d7bc7278e086b3f%2Fc9319025bc315c607a1d4cb187b1cb1348547736.jpg",
                Collections.emptyList()
        ));
        long sequence2 = ConfigUtils.getSequenceByClass(YxwGameFigureInfo.class);
        figureMap.put(sequence2, new YxwGameFigureInfo(
                sequence2,
                "人物2",
                "这里是描述2",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1607583627119&di=fc4044c018df7abb0a692d21253482a6&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fforum%2Fw%3D580%2Fsign%3D3cbb1e9dddca7bcb7d7bc7278e086b3f%2Fc9319025bc315c607a1d4cb187b1cb1348547736.jpg",
                Collections.emptyList()
        ));

        if (firstList == null) {
            firstList = new ArrayList<>();
        }
        // 设置默认的初始人物列表
        firstList.add(figureMap.get(sequence));
        firstList.add(figureMap.get(sequence2));
    }
}
