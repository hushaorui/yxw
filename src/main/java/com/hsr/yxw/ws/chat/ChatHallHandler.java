package com.hsr.yxw.ws.chat;

import com.hsr.yxw.ws.common.BaseProtocol;
import com.hsr.yxw.ws.common.IHandler;
import com.hsr.yxw.ws.common.WsProtoConstants;
import com.hsr.yxw.ws.heartbeat.HeartBeatResponseProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChatHallHandler implements IHandler {
    @Autowired
    private ChatHallService chatHallService;

    @Override
    public BaseProtocol handle(Long id, String message) {
        BaseProtocol responseBaseProtocol = new BaseProtocol(WsProtoConstants.chat_hall_protocol);
        ChatHallRequestIProtocol requestProtocol = ChatHallRequestIProtocol.parseStringToProtoCol(message, ChatHallRequestIProtocol.class);
        if (requestProtocol == null) {
            return null;
        }
        if (ChatHallRequestIProtocol.SEND_PUBLIC_CHAT_MESSAGE.equals(requestProtocol.getType())) {
            // 发送给所有没有屏蔽公共聊天消息的玩家
            chatHallService.sendPublicChatMessage(id, requestProtocol.getMessage());
        } else {
            responseBaseProtocol.setMessage(HeartBeatResponseProtocol.unknownProto(requestProtocol.getType()));
        }
        return responseBaseProtocol;
    }
}
