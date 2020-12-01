package com.hsr.yxw.ws.chat.common;

import com.hsr.yxw.common.CommonVo;
import com.hsr.yxw.ws.chat.pojo.ChatMessage;

public class ChatMessageQueryVo extends CommonVo {
    private ChatMessage chatMessage;
    private Long sendTimeStart;
    private Long sendTimeEnd;

    public ChatMessage getChatMessage() {
        return chatMessage;
    }

    public void setChatMessage(ChatMessage chatMessage) {
        this.chatMessage = chatMessage;
    }

    public Long getSendTimeStart() {
        return sendTimeStart;
    }

    public void setSendTimeStart(Long sendTimeStart) {
        this.sendTimeStart = sendTimeStart;
    }

    public Long getSendTimeEnd() {
        return sendTimeEnd;
    }

    public void setSendTimeEnd(Long sendTimeEnd) {
        this.sendTimeEnd = sendTimeEnd;
    }
}