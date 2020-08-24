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
        ChatHallResponseProtocol chatHallResponseProtocol = new ChatHallResponseProtocol();
        chatHallResponseProtocol.setResType(ChatHallResponseProtocol.RECEIVE_PUBLIC_CHAT_MESSAGE);
        //chatHallResponseProtocol.setContent(chatHallResponseProtocol);
        wsCommonService.sendMessageToAll(chatHallResponseProtocol, null);
    }
}
