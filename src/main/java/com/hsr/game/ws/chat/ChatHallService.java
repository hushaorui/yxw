package com.hsr.game.ws.chat;

import com.hsr.game.account.pojo.Account;
import com.hsr.game.ws.chat.common.ChatMessageType;
import com.hsr.game.ws.chat.common.ChatMessageUtils;
import com.hsr.game.ws.chat.pojo.PublicChatMessage;
import com.hsr.game.ws.chat.service.ChatMessageService;
import com.hsr.game.ws.common.WsAccount;
import com.hsr.game.ws.common.WsCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatHallService {
    private WsCommonService wsCommonService = WsCommonService.getInstance();

    private ChatMessageService chatMessageService;

    @Autowired
    public ChatHallService(ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }

    /**
     * 在公共聊天室发送全体信息
     *
     * @param wsAccount 发送者
     * @param message   信息内容
     */
    public void sendPublicChatMessage(WsAccount wsAccount, String message) {
        if (message == null || message.trim().length() <= 0) {
            // 信息为空字符串，不进行处理
            return;
        }
        message = ChatMessageUtils.checkContent(message);
        ChatHallResponseProtocol chatHallResponseProtocol = new ChatHallResponseProtocol();
        chatHallResponseProtocol.setResType(ChatHallResponseProtocol.RECEIVE_PUBLIC_CHAT_MESSAGE);
        Account account = wsAccount.getAccount();
        long now = System.currentTimeMillis();
        // 存储聊天信息，最好是异步的
        PublicChatMessage publicChatMessage = chatMessageService.save(ChatMessageType.PUBLIC, account.getId(), account.getUsername(), now, null, null, message);
        chatHallResponseProtocol.setPublicChatMessage(publicChatMessage);
        wsCommonService.sendMessageToAll(chatHallResponseProtocol, null);
    }
}
