package com.hsr.yxw.ws.chat;

import com.hsr.yxw.ws.chat.pojo.ChatMessage;
import com.hsr.yxw.ws.common.BaseProtoType;
import com.hsr.yxw.ws.common.IResponseProtocol;

/**
 * 聊天大厅响应使用的协议
 */
public class ChatHallResponseProtocol extends IResponseProtocol {

    /** 接收公共聊天内容 */
    public static final String RECEIVE_PUBLIC_CHAT_MESSAGE = "RECEIVE_PUBLIC_CHAT_MESSAGE";

    private ChatMessage chatMessage;
    public ChatMessage getChatMessage() {
        return chatMessage;
    }
    public void setChatMessage(ChatMessage chatMessage) {
        this.chatMessage = chatMessage;
    }

    public ChatHallResponseProtocol() {}

    @Override
    public BaseProtoType getBaseType() {
        return BaseProtoType.BASE_CHAT_HALL;
    }
}