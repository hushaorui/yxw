package com.hsr.yxw.card.common.em.card;

/**
 * 怪兽卡属性，如：暗
 */
public enum MonsterElement {
    LIGHT(0, "光"),
    DARK(1, "暗"),
    WATER(2, "水"),
    FIRE(3, "火"),
    GROUND(4, "地"),
    WIND(5, "风"),
    GOD(6, "神"),
    ;
    private int id;
    private String name;
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    MonsterElement(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
