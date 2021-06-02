package com.hsr.game.yxw.info;

import com.hsr.game.common.LongIdInfoIF;

/**
 * 物品
 */
public class YxwGoodsInfo implements LongIdInfoIF {

    private Long id;
    /** 物品类型 */
    private int type;
    /** 物品最大堆叠 */
    private Long maxStack;
    /** 物品扩展属性 */
    private String extendedAttr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Long getMaxStack() {
        return maxStack;
    }

    public void setMaxStack(Long maxStack) {
        this.maxStack = maxStack;
    }

    public String getExtendedAttr() {
        return extendedAttr;
    }

    public void setExtendedAttr(String extendedAttr) {
        this.extendedAttr = extendedAttr;
    }
}
