package com.hsr.yxw.ws.game.figure.service;

import com.alibaba.fastjson.JSONArray;
import com.hsr.yxw.common.WebConstants;
import com.hsr.yxw.game.info.YxwGameFigureInfo;
import com.hsr.yxw.ws.common.IHandler;
import com.hsr.yxw.ws.common.IResponseProtocol;
import com.hsr.yxw.ws.common.WsAccount;
import com.hsr.yxw.ws.game.figure.pojo.YxwGameFigureData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.util.Collection;
import java.util.List;

@Component
public class YxwGameFigureHandler implements IHandler<YxwGameFigureRequestProtocol, YxwGameFigureResponseProtocol> {
    private YxwGameFigureService yxwGameFigureService;
    @Autowired
    public YxwGameFigureHandler(YxwGameFigureService yxwGameFigureService) {
        this.yxwGameFigureService = yxwGameFigureService;
    }
    @Override
    public IResponseProtocol handle(WsAccount wsAccount, Session session, YxwGameFigureRequestProtocol request) {
        if (request == null) {
            return null;
        }
        long userId = wsAccount.getAccount().getId();
        YxwGameFigureResponseProtocol response = new YxwGameFigureResponseProtocol();
        if (YxwGameFigureRequestProtocol.SHOW_FIRST_FIGURES.equals(request.getReqType())) {
            List<YxwGameFigureInfo> firstFigureList = yxwGameFigureService.getFirstFigures();
            response.setResType(YxwGameFigureRequestProtocol.SHOW_FIRST_FIGURES);
            response.setInfos(firstFigureList);
        } else if (YxwGameFigureRequestProtocol.CHOOSE_FIRST_FIGURE.equals(request.getReqType())) {
            Long figureId = request.getFigureId();
            if (figureId == null) {
                response.setResType(WebConstants.ERROR);
                response.setTips("人物id不可为空");
                return response;
            }
            String resultTip = yxwGameFigureService.chooseFirstFigure(userId, figureId);
            if (resultTip != null) {
                // 失败
                response.setResType(WebConstants.ERROR);
                response.setTips(resultTip);
                return response;
            }
            // 选择成功，获取最新的人物数据，发送给客户端
            Collection<YxwGameFigureData> allFigures = yxwGameFigureService.getAllFigures(userId);
            response.setAllFigures(allFigures);
            response.setResType(YxwGameFigureRequestProtocol.CHOOSE_FIRST_FIGURE);
        } else if (YxwGameFigureRequestProtocol.GET_ALL_FIGURE_DATA.equals(request.getReqType())) {
            Collection<YxwGameFigureData> allFigures = yxwGameFigureService.getAllFigures(userId);
            response.setAllFigures(allFigures);
            response.setResType(YxwGameFigureRequestProtocol.GET_ALL_FIGURE_DATA);
        } else {

        }
        return response;
    }

    @Override
    public YxwGameFigureRequestProtocol parseRequest(String message) {
        return JSONArray.parseObject(message, YxwGameFigureRequestProtocol.class);
    }
}
