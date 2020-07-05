package com.hsr.yxw.em;

/**
 * 卡片类型
 */
public enum CardType {
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
    CardType(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
