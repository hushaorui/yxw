package com.hsr.yxw.ws.heartbeat;

import com.alibaba.fastjson.JSONArray;
import com.hsr.yxw.ws.common.IHandler;
import com.hsr.yxw.ws.common.IResponseProtocol;
import com.hsr.yxw.ws.common.WsCommonService;
import com.hsr.yxw.ws.heartbeat.pojo.WsServerInfo;
import org.springframework.stereotype.Component;

@Component
public class HeartBeatHandler implements IHandler<HeartBeatRequestProtocol, HeartBeatResponseProtocol> {

    private WsCommonService wsCommonService = WsCommonService.getInstance();
    @Override
    public IResponseProtocol handle(Long id, HeartBeatRequestProtocol request) {
        if (request == null) {
            return null;
        }
        HeartBeatResponseProtocol responseProtocol;
        switch (request.getReqType()) {
            case HeartBeatRequestProtocol.SUB_HEART_BEAT:
                responseProtocol = getServerInfo(request.getClientTime());
                break;
            default:
                return HeartBeatResponseProtocol.unknownProto(request.getReqType());
        }
        // 为null则不用另行返回信息
        return responseProtocol;
    }

    @Override
    public HeartBeatRequestProtocol parseRequest(String message) {
        return JSONArray.parseObject(message, HeartBeatRequestProtocol.class);
    }

    /**
     * 给该用户发送服务器信息
     */
    private HeartBeatResponseProtocol getServerInfo(long clientTime) {
        HeartBeatResponseProtocol heartBeatResponseProtocol = new HeartBeatResponseProtocol();
        heartBeatResponseProtocol.setResType(HeartBeatResponseProtocol.SERVER_INFO);
        WsServerInfo wsServerInfo = new WsServerInfo(clientTime);
        heartBeatResponseProtocol.setMessage(wsServerInfo.toJsonString());
        return heartBeatResponseProtocol;
    }
}
