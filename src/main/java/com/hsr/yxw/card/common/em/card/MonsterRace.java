package com.hsr.yxw.card.common.em.card;

/**
 * 怪兽种族
 */
public enum MonsterRace {
    DRAGON(0, "龙族"),
    UNDEAD(1, "不死族"),
    DEMON(2, "恶魔族"),
    FIRE(3,"炎族"),
    HAILONG(4, "海龙族"),
    ROCK(5, "岩石族"),
    MECHANICAL(6, "机械族"),
    FISH(7, "鱼族"),
    DINOSAUR(8,"恐龙族"),
    INSECT(9, "昆虫族"),
    BEAST(10, "兽族"),
    BEAST_WARRIOR(11, "兽战士族"),
    PLANT(12, "植物族"),
    WATER(13,"水族"),
    WARRIOR(14, "战士族"),
    BIRD(15, "鸟兽族"),
    ANGEL(16, "天使族"),
    MAGICIAN(17, "魔法师族"),
    THUNDER(18,"雷族"),
    REPTILE(19, "爬虫类族"),
    SUPERMAN(20, "念动力族"),
    MAGIC_DRAGON(21, "幻龙族"),
    ELECTRONIC(22, "电子界族"),
    GOD(23,"幻神兽族"),
    ;
    private int id;
    private String name;
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    MonsterRace(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
