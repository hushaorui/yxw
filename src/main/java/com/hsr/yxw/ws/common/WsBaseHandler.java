package com.hsr.yxw.ws.common;

import com.hsr.yxw.ws.chat.ChatHallHandler;
import com.hsr.yxw.ws.heartbeat.HeartBeatHandler;
import com.hsr.yxw.ws.heartbeat.HeartBeatResponseProtocol;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class WsBaseHandler {
    @Autowired
    private ChatHallHandler chatHallHandler;
    @Autowired
    private HeartBeatHandler heartBeatHandler;

    private final Map<String, IHandler> handlers = new HashMap<>();
    public WsBaseHandler() {
        // 注册处理类
        registerHandler();

        // 每隔一定时间，给所有的玩家发送服务器信息
        //ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);
        //scheduledThreadPool.scheduleAtFixedRate(this::sendServerInfoToAll, 2,2, TimeUnit.SECONDS);
    }
    private void registerHandler() {
        // 每一个处理类都需要在这里注册
        handlers.put(WsProtoConstants.chat_hall_protocol, chatHallHandler);
        handlers.put(WsProtoConstants.heart_beat_protocol, heartBeatHandler);
    }
    public IHandler getHandler(String protocolName) {
        return handlers.get(protocolName);
    }

    public BaseProtocol handle(String username, String protocolName, String message) {
        return getHandler(protocolName).handle(username, message);
    }
    public boolean containProtocolName(String protocolName) {
        return handlers.containsKey(protocolName);
    }

}