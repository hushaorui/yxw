package com.hsr.yxw.ws.chat;

import com.hsr.yxw.account.pojo.Account;
import com.hsr.yxw.ws.chat.common.ChatMessageType;
import com.hsr.yxw.ws.chat.common.ChatMessageUtils;
import com.hsr.yxw.ws.chat.pojo.PublicChatMessage;
import com.hsr.yxw.ws.chat.service.ChatMessageService;
import com.hsr.yxw.ws.common.WsCommonService;
import com.hsr.yxw.ws.common.WsAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatHallService {
    private WsCommonService wsCommonService = WsCommonService.getInstance();

    @Autowired
    private ChatMessageService chatMessageService;
    /**
     * 在公共聊天室发送全体信息
     * @param wsAccount 发送者
     * @param message
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
