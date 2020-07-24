package com.hsr.yxw.em.card;

/**
 * 怪兽类型
 */
public enum MonsterType {
    NORMAL(0, "通常"),
    EFFECT(1, "效果"),
    CEREMONY(2, "仪式"),
    FUSION(3,"融合"),
    SYNCHRO(4, "同调"),
    ;
    private int id;
    private String name;
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    MonsterType(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
