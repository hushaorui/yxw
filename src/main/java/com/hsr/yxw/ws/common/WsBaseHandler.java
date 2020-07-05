package com.hsr.yxw.ws.common;

import com.hsr.yxw.ws.chat.ChatHallHandler;
import com.hsr.yxw.ws.connect.ConnectHandler;
import com.hsr.yxw.ws.connect.ConnectResponseProtocol;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class WsBaseHandler {
    @Autowired
    private ChatHallHandler chatHallHandler;
    @Autowired
    private ConnectHandler connectHandler;

    private final Map<String, IHandler> handlers = new HashMap<>();
    public WsBaseHandler() {
        // 注册处理类
        registerHandler();

        // 每隔一定时间，给所有的玩家发送服务器信息
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);
        scheduledThreadPool.scheduleAtFixedRate(this::sendServerInfoToAll, 2,2, TimeUnit.SECONDS);
    }
    private void registerHandler() {
        handlers.put(WsProtoConstants.chat_hall_protocol, chatHallHandler);
        handlers.put(WsProtoConstants.connect_protocol, connectHandler);
    }
    public IHandler getHandler(String protocolName) {
        return handlers.get(protocolName);
    }

    public BaseProtocol handle(String protocolName, String message) {
        return getHandler(protocolName).handle(message);
    }
    public boolean containProtocolName(String protocolName) {
        return handlers.containsKey(protocolName);
    }

    /**
     * 给所有用户发送服务器信息
     */
    private void sendServerInfoToAll() {
        BaseProtocol baseProtocol = new BaseProtocol(WsProtoConstants.connect_protocol);
        ConnectResponseProtocol connectResponseProtocol = new ConnectResponseProtocol();
        connectResponseProtocol.setType(ConnectResponseProtocol.SERVER_INFO);
        PlayerWebSocketPool.getAllPlayerMap().forEach((username, wsPlayer) -> {
            WsServerInfo wsServerInfo = new WsServerInfo();
            connectResponseProtocol.setMessage(wsServerInfo.toJsonString());
            baseProtocol.setMessage(connectResponseProtocol.toJsonString());
            String message = baseProtocol.toJsonString();
            sendMessage(wsPlayer.getWsSession(), message);
        });
    }

    /**
     * 给指定用户发送信息
     * @param username
     * @param protocol
     */
    public boolean sendMessage(String username, BaseProtocol protocol){
        return sendMessage(username, protocol.toJsonString());
    }
    /**
     * 给指定用户发送信息
     * @param username
     * @param message
     */
    public boolean sendMessage(String username, String message){
        WsPlayer wsPlayer = PlayerWebSocketPool.getAllPlayerMap().get(username);
        if (wsPlayer != null) {
            sendMessage(wsPlayer.getWsSession(), message);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 给指定用户发送信息
     * @param session
     * @param protocol
     */
    public void sendMessage(Session session, BaseProtocol protocol){
        sendMessage(session, protocol.toJsonString());
    }
    /**
     * 给指定用户发送信息
     * @param session
     * @param msg
     */
    public void sendMessage(Session session, String msg){
        if (session == null)
            return;
        final RemoteEndpoint.Basic basic = session.getBasicRemote();
        if (basic == null)
            return;
        try {
            basic.sendText(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /***
     * 发送给满足条件的用户
     * @param message json字符串
     * @param wsPlayerFilter 过滤器，为null时则发给所有用户
     */
    public void sendMessageToAll(String message, WsPlayerFilter wsPlayerFilter){
        PlayerWebSocketPool.getAllPlayerMap().forEach((username, wsPlayer) -> {
            if (wsPlayerFilter != null && wsPlayerFilter.doFilter(wsPlayer)) {
                sendMessage(wsPlayer.getWsSession(), message);
            }
        });
    }

    /**
     * 发送给满足条件的用户
     * @param protocol 协议
     * @param wsPlayerFilter 过滤器，为null时则发给所有用户
     */
    public void sendMessageToAll(BaseProtocol protocol, WsPlayerFilter wsPlayerFilter){
        sendMessageToAll(protocol.toJsonString(), wsPlayerFilter);
    }
}