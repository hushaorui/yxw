package com.hsr.game.ws.chat.common;

import com.hsr.game.ws.chat.pojo.PublicChatMessage;
import com.hsr.game.common.CommonVo;

public class PublicChatMessageQueryVo extends CommonVo {
    private PublicChatMessage publicChatMessage;
    private Long sendTimeStart;
    private Long sendTimeEnd;

    public PublicChatMessage getPublicChatMessage() {
        return publicChatMessage;
    }

    public void setPublicChatMessage(PublicChatMessage publicChatMessage) {
        this.publicChatMessage = publicChatMessage;
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
