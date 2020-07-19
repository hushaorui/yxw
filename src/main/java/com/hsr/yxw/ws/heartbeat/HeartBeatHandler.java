package com.hsr.yxw.ws.heartbeat;

import com.hsr.yxw.ws.common.*;
import com.hsr.yxw.ws.heartbeat.pojo.WsServerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

@Component
public class HeartBeatHandler implements IHandler {
    @Autowired
    private WsBaseHandler wsBaseHandler;
    @Override
    public BaseProtocol handle(String username, String message) {
        BaseProtocol baseProtocol = new BaseProtocol(WsProtoConstants.heart_beat_protocol);
        if (StringUtils.isEmpty(message)) {
            // 空白的协议
            baseProtocol.setMessage(HeartBeatResponseProtocol.emptyProto());
            return baseProtocol;
        }
        // 将json字符串解析为心跳的请求协议
        HeartBeatRequestProtocol requestProtocol = HeartBeatRequestProtocol.parseStringToProtoCol(message);
        switch (requestProtocol.getType()) {
            case HeartBeatRequestProtocol.HEART_BEAT:
                sendServerInfo(baseProtocol, username);
            break;
            default:
                baseProtocol.setMessage(HeartBeatResponseProtocol.unknownProto(requestProtocol.getType()));
        }
        return null;
    }

    /**
     * 给该用户发送服务器信息
     */
    private void sendServerInfo(BaseProtocol baseProtocol, String username) {
        HeartBeatResponseProtocol heartBeatResponseProtocol = new HeartBeatResponseProtocol();
        heartBeatResponseProtocol.setType(HeartBeatResponseProtocol.SERVER_INFO);
        WsServerInfo wsServerInfo = new WsServerInfo();
        heartBeatResponseProtocol.setMessage(wsServerInfo.toJsonString());
        baseProtocol.setMessage(heartBeatResponseProtocol.toJsonString());
        String message = baseProtocol.toJsonString();
        WsPlayer wsPlayer = PlayerWebSocketPool.getWsPlayer(username);
        wsBaseHandler.sendMessage(wsPlayer.getWsSession(), message);
    }
}
