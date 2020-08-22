package com.hsr.yxw.ws.heartbeat;

import com.hsr.yxw.ws.common.*;
import com.hsr.yxw.ws.heartbeat.pojo.WsServerInfo;
import org.springframework.stereotype.Component;

@Component
public class HeartBeatHandler implements IHandler {

    private WsCommonService wsCommonService = WsCommonService.getInstance();
    @Override
    public BaseProtocol handle(Long id, String message) {

        // 将json字符串解析为心跳的请求协议
        HeartBeatRequestProtocol requestProtocol = HeartBeatRequestProtocol.parseStringToProtoCol(message, HeartBeatRequestProtocol.class);
        if (requestProtocol == null) {
            return null;
        }
        BaseProtocol responseBaseProtocol = new BaseProtocol(WsProtoConstants.heart_beat_protocol);
        switch (requestProtocol.getType()) {
            case HeartBeatRequestProtocol.HEART_BEAT:
                sendServerInfo(responseBaseProtocol, id);
                break;
            default:
                responseBaseProtocol.setMessage(HeartBeatResponseProtocol.unknownProto(requestProtocol.getType()));
        }
        // 为null则不用另行返回信息
        return null;
    }

    /**
     * 给该用户发送服务器信息
     */
    private void sendServerInfo(BaseProtocol baseProtocol, Long id) {
        HeartBeatResponseProtocol heartBeatResponseProtocol = new HeartBeatResponseProtocol();
        heartBeatResponseProtocol.setType(HeartBeatResponseProtocol.SERVER_INFO);
        WsServerInfo wsServerInfo = new WsServerInfo();
        heartBeatResponseProtocol.setMessage(wsServerInfo.toJsonString());
        baseProtocol.setMessage(heartBeatResponseProtocol.toJsonString());
        String message = baseProtocol.toJsonString();
        WsPlayer wsPlayer = PlayerWebSocketPool.getWsPlayer(id);
        wsCommonService.sendMessage(wsPlayer.getWsSession(), message);
    }
}
