package com.hsr.yxw.ws.game.figure.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.hsr.yxw.game.info.YxwGameFigureInfo;

/**
 * 对应一个人物的所有数据
 */
public class YxwGameFigureData {
    @JSONField(serialize = false)
    private YxwGameFigureInfo figureInfo;
    /** 配置中的id */
    private Long cfgId;
    /** 人物等级 */
    private Long level;
    /** 当前等级的经验值 */
    private Long exp;
    // TODO 其他信息

    public YxwGameFigureInfo getFigureInfo() {
        return figureInfo;
    }
    public void setFigureInfo(YxwGameFigureInfo figureInfo) {
        this.figureInfo = figureInfo;
    }

    public Long getCfgId() {
        return cfgId;
    }

    public void setCfgId(Long cfgId) {
        this.cfgId = cfgId;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public Long getExp() {
        return exp;
    }

    public void setExp(Long exp) {
        this.exp = exp;
    }

}
