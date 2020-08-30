package com.hsr.yxw.ws.common;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class WsCommonService {
    private static final WsCommonService instance = new WsCommonService();

    public static WsCommonService getInstance() {
        return instance;
    }
    private WsCommonService() {}

    public void sendMessageToPlayer(WsPlayer wsPlayer, String message) {
        HashSet<String> closeSessionIdSet = null;
        for (Map.Entry<String, Session> entry : wsPlayer.getWsSessions().entrySet()) {
            Session session = entry.getValue();
            if (session.isOpen()) {
                sendMessage(session, message);
            } else {
                if (closeSessionIdSet == null) {
                    closeSessionIdSet = new HashSet<>();
                }
                closeSessionIdSet.add(entry.getKey());
            }
        }
        // 删除已经关闭的session
        if (closeSessionIdSet != null) {
            for (String sessionId : closeSessionIdSet) {
                wsPlayer.getWsSessions().remove(sessionId);
            }
        }
    }
    public void sendMessageToPlayer(WsPlayer wsPlayer, IResponseProtocol responseProtocol) {
        if (wsPlayer != null) {
            sendMessageToPlayer(wsPlayer, BaseProtocol.buildResponse(responseProtocol).toJsonString());
        }
    }
    /**
     * 给指定用户发送信息
     * @param wsPlayer 唯一用户
     * @param protocol 协议
     */
    public boolean sendMessage(WsPlayer wsPlayer, String sessionId, IResponseProtocol protocol){
        return sendMessage(wsPlayer, sessionId, BaseProtocol.buildResponse(protocol).toJsonString());
    }
    /**
     * 给指定用户发送信息
     * @param wsPlayer 唯一用户
     * @param message json字符串
     */
    public boolean sendMessage(WsPlayer wsPlayer, String sessionId, String message){
        if (wsPlayer != null && wsPlayer.getWsSessions().containsKey(sessionId)) {
            Session session = wsPlayer.getWsSessions().get(sessionId);
            if (session.isOpen()) {
                sendMessage(wsPlayer.getWsSessions().get(sessionId), message);
                return true;
            } else {
                wsPlayer.getWsSessions().remove(sessionId);
                return false;
            }
        } else {
            return false;
        }
    }

    public void sendMessage(Session session, IResponseProtocol responseProtocol){
        sendMessage(session, BaseProtocol.buildResponse(responseProtocol).toString());
    }
    /**
     * 给指定session发送信息
     * @param session 会话
     * @param protoString 信息
     */
    public void sendMessage(Session session, String protoString){
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
     * @param responseProtocol 响应
     * @param wsPlayerFilter 过滤器，为null时则发给所有用户
     */
    public void sendMessageToAll(IResponseProtocol responseProtocol, WsPlayerFilter wsPlayerFilter){
        final String message = BaseProtocol.buildResponse(responseProtocol).toJsonString();
        PlayerWebSocketPool.getAllPlayerMap().forEach((username, wsPlayer) -> {
            if (wsPlayerFilter != null && wsPlayerFilter.doFilter(wsPlayer)) {
                sendMessageToPlayer(wsPlayer, message);
            }
        });
    }
}
