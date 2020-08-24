package com.hsr.yxw.ws.heartbeat;

import com.alibaba.fastjson.JSONArray;
import com.hsr.yxw.ws.common.*;
import com.hsr.yxw.ws.heartbeat.pojo.WsServerInfo;
import org.springframework.stereotype.Component;

@Component
public class HeartBeatHandler implements IHandler<HeartBeatRequestProtocol, HeartBeatResponseProtocol> {

    private WsCommonService wsCommonService = WsCommonService.getInstance();
    @Override
    public IResponseProtocol handle(Long id, HeartBeatRequestProtocol requestIProtocol) {
        if (requestIProtocol == null) {
            return null;
        }
        switch (requestIProtocol.getReqType()) {
            case HeartBeatRequestProtocol.HEART_BEAT:
                sendServerInfo(id);
                break;
            default:
                return HeartBeatResponseProtocol.unknownProto(requestIProtocol.getReqType());
        }
        // 为null则不用另行返回信息
        return null;
    }

    @Override
    public HeartBeatRequestProtocol parseRequest(String message) {
        return JSONArray.parseObject(message, HeartBeatRequestProtocol.class);
    }

    /**
     * 给该用户发送服务器信息
     */
    private void sendServerInfo(Long id) {
        HeartBeatResponseProtocol heartBeatResponseProtocol = new HeartBeatResponseProtocol();
        heartBeatResponseProtocol.setResType(HeartBeatResponseProtocol.SERVER_INFO);
        WsServerInfo wsServerInfo = new WsServerInfo();
        heartBeatResponseProtocol.setMessage(wsServerInfo.toJsonString());
        WsPlayer wsPlayer = PlayerWebSocketPool.getWsPlayer(id);
        wsCommonService.sendMessage(wsPlayer.getWsSession(), heartBeatResponseProtocol);
    }
}
