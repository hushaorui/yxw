package com.hsr.yxw.ws.chat;

import com.alibaba.fastjson.JSONArray;
import com.hsr.yxw.ws.common.IHandler;
import com.hsr.yxw.ws.common.IResponseProtocol;
import com.hsr.yxw.ws.heartbeat.HeartBeatResponseProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChatHallHandler implements IHandler<ChatHallRequestIProtocol, ChatHallResponseProtocol> {
    @Autowired
    private ChatHallService chatHallService;

    @Override
    public IResponseProtocol handle(Long senderId, ChatHallRequestIProtocol requestProtocol) {
        if (requestProtocol == null) {
            return null;
        }
        if (ChatHallRequestIProtocol.SEND_PUBLIC_CHAT_MESSAGE.equals(requestProtocol.getReqType())) {
            // 发送给所有没有屏蔽公共聊天消息的玩家
            chatHallService.sendPublicChatMessage(senderId, requestProtocol.getMessage());
        } else {
            return HeartBeatResponseProtocol.unknownProto(requestProtocol.getReqType());
        }
        return null;
    }

    @Override
    public ChatHallRequestIProtocol parseRequest(String message) {
        return JSONArray.parseObject(message, ChatHallRequestIProtocol.class);
    }
}
