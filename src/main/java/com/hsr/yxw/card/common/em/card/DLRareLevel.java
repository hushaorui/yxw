package com.hsr.yxw.card.common.em.card;

/**
 * 在决斗链接的稀有度
 */
public enum DLRareLevel {
    UR(0, "UR"),
    SR(1, "SR"),
    R(2, "R"),
    N(3,"N"),
    ;
    private int id;
    private String name;
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    DLRareLevel(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
