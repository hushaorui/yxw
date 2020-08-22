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

    private Map<String, IHandler> handlers;
    public WsBaseHandler() {
        handlers = new HashMap<>();
        // 每隔一定时间，给所有的玩家发送服务器信息
        //ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);
        //scheduledThreadPool.scheduleAtFixedRate(this::sendServerInfoToAll, 2,2, TimeUnit.SECONDS);
    }
    // 注册处理类
    private void registerHandler() {
        // 每一个处理类都需要在这里注册
        handlers.put(WsProtoConstants.chat_hall_protocol, chatHallHandler);
        handlers.put(WsProtoConstants.heart_beat_protocol, heartBeatHandler);
    }
    private IHandler getHandler(String protocolName) {
        if (handlers.isEmpty()) {
            registerHandler();
        }
        if (handlers.containsKey(protocolName)) {
            return handlers.get(protocolName);
        } else {
            System.out.println("未知的子协议名称：" + protocolName);
            return null;
        }
    }

    public BaseProtocol handle(Long id, String protocolName, String message) {
        return getHandler(protocolName).handle(id, message);
    }
    public boolean containProtocolName(String protocolName) {
        if (handlers.isEmpty()) {
            registerHandler();
        }
        return handlers.containsKey(protocolName);
    }

}