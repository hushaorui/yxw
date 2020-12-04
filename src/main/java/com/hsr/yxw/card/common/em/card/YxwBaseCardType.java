package com.hsr.yxw.card.common.em.card;

/**
 * yxw卡片基本类型
 */
public enum YxwBaseCardType {
    MONSTER(0, "怪兽卡"),
    MAGIC(1, "魔法卡"),
    TRAP(2, "陷阱卡"),
    ;
    private int id;
    private String name;
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    YxwBaseCardType(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
