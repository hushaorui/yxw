package com.hsr.yxw.game.common.em.card;

public enum YxwMonsterType {
    NORMAL(0, "通常", YxwMonsterBaseType.NORMAL),
    EFFECT(1, "效果", YxwMonsterBaseType.EFFECT),
    CEREMONY_NO_EFFECT(2, "仪式效果外", YxwMonsterBaseType.CEREMONY),
    CEREMONY_EFFECT(3, "仪式效果", YxwMonsterBaseType.CEREMONY),
    FUSION_NO_EFFECT(4,"融合效果外", YxwMonsterBaseType.FUSION),
    FUSION_EFFECT(5,"融合效果", YxwMonsterBaseType.FUSION),
    SYNCHRO_NO_EFFECT(6, "同调效果外", YxwMonsterBaseType.SYNCHRO),
    SYNCHRO_EFFECT(7, "同调效果", YxwMonsterBaseType.SYNCHRO),
    EXCESS_NO_EFFECT(8, "超量效果外", YxwMonsterBaseType.EXCESS),
    EXCESS_EFFECT(9, "超量效果", YxwMonsterBaseType.EXCESS),
    ;
    private int id;
    private String name;
    private YxwMonsterBaseType baseType;
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public YxwMonsterBaseType getBaseType() {
        return baseType;
    }
    YxwMonsterType(int id, String name, YxwMonsterBaseType baseType) {
        this.id = id;
        this.name = name;
        this.baseType = baseType;
    }
}
