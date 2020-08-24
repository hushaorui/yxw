package com.hsr.yxw.ws.common;

import com.hsr.yxw.ws.chat.ChatHallHandler;
import com.hsr.yxw.ws.heartbeat.HeartBeatHandler;
import com.hsr.yxw.ws.heartbeat.HeartBeatResponseProtocol;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class WsBaseHandler {
    @Autowired
    private ChatHallHandler chatHallHandler;
    @Autowired
    private HeartBeatHandler heartBeatHandler;

    private Map<BaseProtoType, IHandler> handlers;
    public WsBaseHandler() {
        handlers = new HashMap<>();
        // 每隔一定时间，给所有的玩家发送服务器信息
        //ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);
        //scheduledThreadPool.scheduleAtFixedRate(this::sendServerInfoToAll, 2,2, TimeUnit.SECONDS);
    }
    // 注册处理类
    private void registerHandler() {
        // 每一个处理类都需要在这里注册
        handlers.put(BaseProtoType.chat_hall, chatHallHandler);
        handlers.put(BaseProtoType.heart_beat, heartBeatHandler);
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

    public IResponseProtocol handle(Long id, BaseProtoType baseProtoType, String message) {
        IHandler handler = getHandler(baseProtoType);
        try {
            if (handler != null) {
                return handler.handle(id, handler.parseRequest(message));
            } else {
                return HeartBeatResponseProtocol.unknownProto(String.valueOf(baseProtoType));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public boolean containSubProtocol(BaseProtoType baseProtoType) {
        if (handlers.isEmpty()) {
            registerHandler();
        }
        return handlers.containsKey(baseProtoType);
    }

}