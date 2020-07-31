package com.hsr.yxw.ws.chat;

import com.hsr.yxw.ws.common.BaseProtocol;
import com.hsr.yxw.ws.common.WsCommonService;
import org.springframework.stereotype.Service;

@Service
public class ChatHallService {
    private WsCommonService wsCommonService = WsCommonService.getInstance();

    /**
     * 在公共聊天室发送全体信息
     * @param senderId 发送者id
     * @param message
     */
    public void sendPublicChatMessage(Long senderId, String message) {
        BaseProtocol baseProtocol = new BaseProtocol(ChatHallResponseProtocol.RECEIVE_PUBLIC_CHAT_MESSAGE);
        ChatHallResponseProtocol chatHallResponseProtocol = new ChatHallResponseProtocol();

        baseProtocol.setMessage(chatHallResponseProtocol);
        wsCommonService.sendMessageToAll(baseProtocol, null);
    }
}
