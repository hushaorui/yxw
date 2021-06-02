package com.hsr.game.yxw.common.em.card;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum YxwCardType {
    EMPTY(0, "空白"),
    MONSTER(1, "怪兽"),
    MAGIC_OR_TRAP(2, "魔陷"),
    ;

    private int id;
    private String desc;
    private static Map<String, String> mapping;
    static {
        YxwCardType[] values = YxwCardType.values();
        HashMap<String, String> tempMap = new HashMap<>(values.length, 1.5f);
        for (YxwCardType type : values) {
            tempMap.put(type.toString(), type.getDesc());
        }
        mapping = Collections.unmodifiableMap(tempMap);
    }

    YxwCardType(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public static Map<String, String> getMapping() {
        return mapping;
    }
}
