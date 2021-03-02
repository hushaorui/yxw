package com.hsr.yxw.ws.game.base.pojo;

public class YxwGameMainData {

    private long diamond; // 钻石数量
    private long coin; // 金币数量
    private long jewel_R; // R宝珠数量
    private long jewel_SR; // SR宝珠数量
    private long jewel_UR; // UR宝珠数量

    public long getDiamond() {
        return diamond;
    }

    public void setDiamond(long diamond) {
        this.diamond = diamond;
    }

    public long getCoin() {
        return coin;
    }

    public void setCoin(long coin) {
        this.coin = coin;
    }

    public long getJewel_R() {
        return jewel_R;
    }

    public void setJewel_R(long jewel_R) {
        this.jewel_R = jewel_R;
    }

    public long getJewel_SR() {
        return jewel_SR;
    }

    public void setJewel_SR(long jewel_SR) {
        this.jewel_SR = jewel_SR;
    }

    public long getJewel_UR() {
        return jewel_UR;
    }

    public void setJewel_UR(long jewel_UR) {
        this.jewel_UR = jewel_UR;
    }
}
