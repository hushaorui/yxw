package com.hsr.yxw.ws.chat;

import com.hsr.yxw.ws.common.BaseProtocol;
import com.hsr.yxw.ws.common.WsCommonService;
import com.hsr.yxw.ws.common.WsPlayer;
import org.springframework.stereotype.Service;

@Service
public class ChatHallService {
    private WsCommonService wsCommonService = WsCommonService.getInstance();

    /**
     * 在公共聊天室发送全体信息
     * @param wsPlayer 发送者
     * @param message
     */
    public void sendPublicChatMessage(WsPlayer wsPlayer, String message) {
        ChatHallResponseProtocol chatHallResponseProtocol = new ChatHallResponseProtocol();
        chatHallResponseProtocol.setResType(ChatHallResponseProtocol.RECEIVE_PUBLIC_CHAT_MESSAGE);
        //chatHallResponseProtocol.setContent(chatHallResponseProtocol);
        // TODO
        wsCommonService.sendMessageToAll(chatHallResponseProtocol, null);
    }
}
