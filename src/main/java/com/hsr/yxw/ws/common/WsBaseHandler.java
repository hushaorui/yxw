package com.hsr.yxw.ws.common;

import com.hsr.yxw.ws.chat.ChatHallHandler;
import com.hsr.yxw.ws.game.figure.service.YxwGameFigureHandler;
import com.hsr.yxw.ws.heartbeat.HeartBeatHandler;
import com.hsr.yxw.ws.heartbeat.HeartBeatResponseProtocol;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class WsBaseHandler {
    @Autowired
    private ChatHallHandler chatHallHandler;
    @Autowired
    private HeartBeatHandler heartBeatHandler;
    @Autowired
    private YxwGameFigureHandler yxwGameFigureHandler;

    private Map<BaseProtoType, IHandler> handlers;
    public WsBaseHandler() {
        handlers = new HashMap<>();
    }
    // 注册处理类
    private void registerHandler() {
        // 每一个处理类都需要在这里注册
        handlers.put(BaseProtoType.BASE_CHAT_HALL, chatHallHandler);
        handlers.put(BaseProtoType.BASE_HEART_BEAT, heartBeatHandler);
        handlers.put(BaseProtoType.YXW_GAME_FIGURE, yxwGameFigureHandler);
    }
    private IHandler getHandler(BaseProtoType baseProtoType) {
        if (handlers.isEmpty()) {
            registerHandler();
        }
        if (handlers.containsKey(baseProtoType)) {
            return handlers.get(baseProtoType);
        } else {
            System.out.println("未知的子协议名称：" + baseProtoType);
            return null;
        }
    }

    public IResponseProtocol handle(WsAccount wsAccount, Session session, BaseProtoType baseProtoType, String message) {
        IHandler handler = getHandler(baseProtoType);
        try {
            if (handler != null) {
                return handler.handle(wsAccount, session, handler.parseRequest(message));
            } else {
                return HeartBeatResponseProtocol.unknownProto(String.valueOf(baseProtoType));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    boolean containSubProtocol(BaseProtoType baseProtoType) {
        if (handlers.isEmpty()) {
            registerHandler();
        }
        return handlers.containsKey(baseProtoType);
    }

}
