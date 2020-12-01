package com.hsr.yxw.ws.chat;

import com.hsr.yxw.player.pojo.Player;
import com.hsr.yxw.ws.chat.common.ChatMessageType;
import com.hsr.yxw.ws.chat.common.ChatMessageUtils;
import com.hsr.yxw.ws.chat.pojo.ChatMessage;
import com.hsr.yxw.ws.chat.service.ChatMessageService;
import com.hsr.yxw.ws.common.BaseProtocol;
import com.hsr.yxw.ws.common.WsCommonService;
import com.hsr.yxw.ws.common.WsPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatHallService {
    private WsCommonService wsCommonService = WsCommonService.getInstance();

    @Autowired
    private ChatMessageService chatMessageService;
    /**
     * 在公共聊天室发送全体信息
     * @param wsPlayer 发送者
     * @param message
     */
    public void sendPublicChatMessage(WsPlayer wsPlayer, String message) {
        if (message == null || message.trim().length() <= 0) {
            // 信息为空字符串，不进行处理
            return;
        }
        message = ChatMessageUtils.checkContent(message);
        ChatHallResponseProtocol chatHallResponseProtocol = new ChatHallResponseProtocol();
        chatHallResponseProtocol.setResType(ChatHallResponseProtocol.RECEIVE_PUBLIC_CHAT_MESSAGE);
        Player player = wsPlayer.getPlayer();
        long now = System.currentTimeMillis();
        // 存储聊天信息，最好是异步的
        ChatMessage chatMessage = chatMessageService.save(ChatMessageType.PUBLIC, player.getId(), player.getUsername(), now, null, null, message);
        chatHallResponseProtocol.setChatMessage(chatMessage);
        wsCommonService.sendMessageToAll(chatHallResponseProtocol, null);
    }
}
