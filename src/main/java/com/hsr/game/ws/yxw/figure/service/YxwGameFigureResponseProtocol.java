package com.hsr.game.ws.yxw.figure.service;

import com.hsr.game.ws.yxw.figure.pojo.YxwGameFigureData;
import com.hsr.game.yxw.info.YxwGameFigureInfo;
import com.hsr.game.ws.common.BaseProtoType;
import com.hsr.game.ws.common.IResponseProtocol;

import java.util.Collection;

public class YxwGameFigureResponseProtocol extends IResponseProtocol {

    public static final String CHOOSE_SUCCESS = "choose_success";

    private Collection<YxwGameFigureData> allFigures; // 所有人物信息
    private Collection<YxwGameFigureInfo> infos; // 人物配置信息
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

    public Collection<YxwGameFigureInfo> getInfos() {
        return infos;
    }

    public void setInfos(Collection<YxwGameFigureInfo> infos) {
        this.infos = infos;
    }

    @Override
    public BaseProtoType getBaseType() {
        return BaseProtoType.YXW_GAME_FIGURE;
    }
}
