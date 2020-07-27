package com.hsr.yxw.ws.chat;

import com.hsr.yxw.ws.common.BaseProtocol;
import com.hsr.yxw.ws.common.IHandler;
import com.hsr.yxw.ws.common.WsProtoConstants;
import com.hsr.yxw.ws.heartbeat.HeartBeatResponseProtocol;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

@Component
public class ChatHallHandler implements IHandler {

    @Override
    public BaseProtocol handle(String username, String message) {
        BaseProtocol baseProtocol = new BaseProtocol(WsProtoConstants.chat_hall_protocol);
        if (StringUtils.isEmpty(message)) {
            // 空白的协议
            baseProtocol.setMessage(HeartBeatResponseProtocol.emptyProto());
            return baseProtocol;
        }
        return baseProtocol;
    }
}
