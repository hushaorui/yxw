package com.hsr.game.ws.common;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;

public class WsCommonService {
    private static final WsCommonService instance = new WsCommonService();

    public static WsCommonService getInstance() {
        return instance;
    }
    private WsCommonService() {}

    public void sendMessageToAccount(WsAccount wsAccount, String message) {
        HashSet<String> closeSessionIdSet = null;
        for (Map.Entry<String, Session> entry : wsAccount.getWsSessions().entrySet()) {
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
                wsAccount.getWsSessions().remove(sessionId);
            }
        }
    }
    public void sendMessageToAccount(WsAccount wsAccount, IResponseProtocol responseProtocol) {
        if (wsAccount != null) {
            sendMessageToAccount(wsAccount, BaseProtocol.buildResponse(responseProtocol).toJsonString());
        }
    }
    /**
     * 给指定用户发送信息
     * @param wsAccount 唯一用户
     * @param protocol 协议
     */
    public boolean sendMessage(WsAccount wsAccount, String sessionId, IResponseProtocol protocol){
        return sendMessage(wsAccount, sessionId, BaseProtocol.buildResponse(protocol).toJsonString());
    }
    /**
     * 给指定用户发送信息
     * @param wsAccount 唯一用户
     * @param message json字符串
     */
    public boolean sendMessage(WsAccount wsAccount, String sessionId, String message){
        if (wsAccount != null && wsAccount.getWsSessions().containsKey(sessionId)) {
            Session session = wsAccount.getWsSessions().get(sessionId);
            if (session.isOpen()) {
                sendMessage(wsAccount.getWsSessions().get(sessionId), message);
                return true;
            } else {
                wsAccount.getWsSessions().remove(sessionId);
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
     * @param wsAccountFilter 过滤器，为null时则发给所有用户
     */
    public void sendMessageToAll(IResponseProtocol responseProtocol, WsAccountFilter wsAccountFilter){
        final String message = BaseProtocol.buildResponse(responseProtocol).toJsonString();
        AccountWebSocketPool.getAllAccountMap().forEach((username, wsAccount) -> {
            if (wsAccountFilter == null || wsAccountFilter.doFilter(wsAccount)) {
                sendMessageToAccount(wsAccount, message);
            }
        });
    }
}
