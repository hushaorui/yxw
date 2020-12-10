package com.hsr.yxw.game.info;

/**
 * 对应一张卡牌的所有数据
 */
public class YxwGameCard {
    /** 卡牌基础信息 */
    private YxwCardBaseInfo baseInfo;
    /** 卡牌怪兽信息，只有类型为怪兽的卡牌才有该信息 */
    private YxwMonsterCardInfo monsterCardInfo;
    // TODO 其他信息


    public YxwCardBaseInfo getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(YxwCardBaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }

    public YxwMonsterCardInfo getMonsterCardInfo() {
        return monsterCardInfo;
    }

    public void setMonsterCardInfo(YxwMonsterCardInfo monsterCardInfo) {
        this.monsterCardInfo = monsterCardInfo;
    }
}
