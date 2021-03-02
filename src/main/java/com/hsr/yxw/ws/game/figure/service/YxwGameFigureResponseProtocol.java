package com.hsr.yxw.ws.game.figure.service;

import com.hsr.yxw.ws.common.BaseProtoType;
import com.hsr.yxw.ws.common.IResponseProtocol;
import com.hsr.yxw.ws.game.figure.pojo.YxwGameFigureData;

import java.util.Collection;

public class YxwGameFigureResponseProtocol extends IResponseProtocol {

    public static final String CHOOSE_SUCCESS = "choose_success";

    private Collection<YxwGameFigureData> allFigures; // 所有人物信息
    private String tips; // 提示语

    public static String getChooseSuccess() {
        return CHOOSE_SUCCESS;
    }

    public Collection<YxwGameFigureData> getAllFigures() {
        return allFigures;
    }

    public void setAllFigures(Collection<YxwGameFigureData> allFigures) {
        this.allFigures = allFigures;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    @Override
    public BaseProtoType getBaseType() {
        return BaseProtoType.YXW_GAME_FIGURE;
    }
}
