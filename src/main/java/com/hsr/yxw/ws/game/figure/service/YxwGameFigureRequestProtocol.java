package com.hsr.yxw.ws.game.figure.service;

import com.hsr.yxw.ws.common.BaseProtoType;
import com.hsr.yxw.ws.common.IRequestProtocol;

public class YxwGameFigureRequestProtocol extends IRequestProtocol {
    public static final String GET_ALL_FIGURE_DATA = "get_all_figure_data"; // 获取玩家的所有人物数据
    public static final String CHOOSE_FIRST_FIGURE = "choose_first_figure"; // 选择初始人物

    private Long figureId;

    public Long getFigureId() {
        return figureId;
    }

    public void setFigureId(Long figureId) {
        this.figureId = figureId;
    }

    @Override
    public BaseProtoType getBaseType() {
        return BaseProtoType.YXW_GAME_FIGURE;
    }
}
