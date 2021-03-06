package com.hsr.game.yxw.common.em.skill;

/**
 * 游戏内的技能
 */
public enum YxwGameSkill {
    NULL(0, "未设置技能"),
    LP_A(1, "LP增强阿尔法"),
    LP_B(2, "LP增强贝塔"),
    ;
    private int id;
    private String name;
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    YxwGameSkill(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
