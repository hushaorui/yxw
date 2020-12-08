package com.hsr.yxw.card.common.em.card;

/**
 * yxw魔陷类型
 */
public enum YxwMOTCardType {
    NORMAL_MAGIC(0, "通常魔法"),
    FAST_MAGIC(1, "速攻魔法"),
    PERPETUAL_MAGIC(2, "永续魔法"),
    SPACE_MAGIC(3, "场地魔法"),
    EQUIP_MAGIC(4, "装备魔法"),

    NORMAL_TRAP(10, "通常陷阱"),
    PERPETUAL_TRAP(11, "永续陷阱"),
    COUNTER_TRAP(12, "反击陷阱"),
    ;
    private int id;
    private String name;
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    YxwMOTCardType(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
