package com.hsr.yxw.game.config.figure;

import com.hsr.yxw.common.InitializedConfig;
import com.hsr.yxw.game.info.YxwGameFigureInfo;
import com.hsr.yxw.util.ConfigUtils;

import java.util.HashMap;
import java.util.Map;

public class YxwGameFigureInfoConfig implements InitializedConfig {

    private Map<Long, YxwGameFigureInfo> figureMap;

    public Map<Long, YxwGameFigureInfo> getFigureMap() {
        return figureMap;
    }

    public void setFigureMap(Map<Long, YxwGameFigureInfo> figureMap) {
        this.figureMap = figureMap;
    }

    @Override
    public void init() {
        if (figureMap == null) {
            figureMap = new HashMap<>();
        }
        YxwGameFigureInfo yxwGameFigureInfo = new YxwGameFigureInfo();
        long sequence = ConfigUtils.getSequenceByClass(YxwGameFigureInfo.class);
        yxwGameFigureInfo.setId(sequence);
        yxwGameFigureInfo.setName("名称");
        yxwGameFigureInfo.setAppearanceImgUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1607583627119&di=fc4044c018df7abb0a692d21253482a6&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fforum%2Fw%3D580%2Fsign%3D3cbb1e9dddca7bcb7d7bc7278e086b3f%2Fc9319025bc315c607a1d4cb187b1cb1348547736.jpg");
        yxwGameFigureInfo.setDescription("这里是描述");
        figureMap.put(sequence, yxwGameFigureInfo);
    }
}
