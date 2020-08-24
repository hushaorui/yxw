package com.hsr.yxw.ws.common;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.io.IOException;

public class WsCommonService {
    private static final WsCommonService instance = new WsCommonService();

    public static WsCommonService getInstance() {
        return instance;
    }
    private WsCommonService() {}

    /**
     * 给指定用户发送信息
     * @param id 唯一用户id
     * @param protocol 协议
     */
    public boolean sendMessage(Long id, IResponseProtocol protocol){
        return sendMessage(id, BaseProtocol.buildResponse(protocol).toJsonString());
    }
    /**
     * 给指定用户发送信息
     * @param id 唯一用户id
     * @param message json字符串
     */
    public boolean sendMessage(Long id, String message){
        WsPlayer wsPlayer = PlayerWebSocketPool.getAllPlayerMap().get(id);
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
    public void sendMessage(Session session, IResponseProtocol protocol){
        sendMessage(session, BaseProtocol.buildResponse(protocol).toJsonString());
    }
    /**
     * 给指定用户发送信息
     * @param session 会话
     * @param protoString 信息
     */
    public void sendMessage(Session session, String protoString){
        if (session == null)
            return;
        final RemoteEndpoint.Basic basic = session.getBasicRemote();
        if (basic == null)
            return;
        try {
            basic.sendText(protoString);
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
    public void sendMessageToAll(IResponseProtocol protocol, WsPlayerFilter wsPlayerFilter){
        sendMessageToAll(BaseProtocol.buildResponse(protocol).toJsonString(), wsPlayerFilter);
    }
}
