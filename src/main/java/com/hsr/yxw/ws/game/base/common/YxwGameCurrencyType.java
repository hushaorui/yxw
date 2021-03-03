package com.hsr.yxw.ws.game.base.common;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum YxwGameCurrencyType {
    Diamond(1), // 钻石数量
    Coin(2), // 金币数量
    Jewel_R(3), // R宝珠数量
    Jewel_SR(4), // SR宝珠数量
    Jewel_UR(5), // UR宝珠数量
    ;
    private static final Map<Integer, YxwGameCurrencyType> mapping;
    static {
        YxwGameCurrencyType[] values = YxwGameCurrencyType.values();
        Map<Integer, YxwGameCurrencyType> temp = new HashMap<>(values.length, 1.5f);
        for (YxwGameCurrencyType type : values) {
            temp.put(type.id, type);
        }
        mapping = Collections.unmodifiableMap(temp);
    }
    private final int id;
    YxwGameCurrencyType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static YxwGameCurrencyType getById(int id) {
        return mapping.get(id);
    }
}
