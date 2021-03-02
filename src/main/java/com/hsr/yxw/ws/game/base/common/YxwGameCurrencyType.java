package com.hsr.yxw.ws.game.base.common;

public enum YxwGameCurrencyType {
    Diamond(1), // 钻石数量
    Coin(2), // 金币数量
    Jewel_R(3), // R宝珠数量
    Jewel_SR(4), // SR宝珠数量
    Jewel_UR(5), // UR宝珠数量
    ;
    private final int id;
    YxwGameCurrencyType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
