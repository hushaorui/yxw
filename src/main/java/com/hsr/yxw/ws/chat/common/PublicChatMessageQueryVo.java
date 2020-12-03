package com.hsr.yxw.ws.chat.common;

import com.hsr.yxw.common.CommonVo;
import com.hsr.yxw.ws.chat.pojo.PublicChatMessage;

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
