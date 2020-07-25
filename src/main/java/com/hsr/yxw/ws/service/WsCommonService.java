package com.hsr.yxw.ws.service;

import com.hsr.yxw.ws.common.BaseProtocol;
import com.hsr.yxw.ws.common.PlayerWebSocketPool;
import com.hsr.yxw.ws.common.WsPlayer;
import com.hsr.yxw.ws.common.WsPlayerFilter;
import org.springframework.stereotype.Service;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.io.IOException;

@Service
public class WsCommonService {
    /**
     * 给指定用户发送信息
     * @param username 唯一用户名
     * @param protocol 协议
     */
    public boolean sendMessage(String username, BaseProtocol protocol){
        return sendMessage(username, protocol.toJsonString());
    }
    /**
     * 给指定用户发送信息
     * @param username 唯一用户名
     * @param message json字符串
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
     * @param session 会话
     * @param protocol 协议
     */
    public void sendMessage(Session session, BaseProtocol protocol){
        sendMessage(session, protocol.toJsonString());
    }
    /**
     * 给指定用户发送信息
     * @param session 会话
     * @param msg 信息
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
