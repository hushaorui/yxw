package com.hsr.yxw.ws.game.base.common;

import com.hsr.yxw.ws.game.base.pojo.YxwGameMainData;

public class YxwGameMainDataHelper {

    public static long getCurrencyCount(YxwGameMainData mainData, YxwGameCurrencyType currencyTypeEnum) {
        switch (currencyTypeEnum) {
            case Coin:
                return mainData.getCoin();
            case Diamond:
                return mainData.getDiamond();
            case Jewel_R:
                return mainData.getJewel_R();
            case Jewel_SR:
                return mainData.getJewel_SR();
            case Jewel_UR:
                return mainData.getJewel_UR();
        }
        return 0;
    }

    public static void updateCurrency(YxwGameMainData mainData, YxwGameCurrencyType currencyTypeEnum, long currentCount) {
        switch (currencyTypeEnum) {
            case Coin:
                mainData.setCoin(currentCount);
                break;
            case Diamond:
                mainData.setDiamond(currentCount);
                break;
            case Jewel_R:
                mainData.setJewel_R(currentCount);
                break;
            case Jewel_SR:
                mainData.setJewel_SR(currentCount);
                break;
            case Jewel_UR:
                mainData.setJewel_UR(currentCount);
                break;
        }
    }
}
