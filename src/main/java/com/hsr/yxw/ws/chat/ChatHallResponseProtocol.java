package com.hsr.yxw.ws.chat;

import com.alibaba.fastjson.annotation.JSONField;
import com.hsr.yxw.ws.chat.pojo.PublicChatMessage;
import com.hsr.yxw.ws.common.BaseProtoType;
import com.hsr.yxw.ws.common.IResponseProtocol;

/**
 * 聊天大厅响应使用的协议
 */
public class ChatHallResponseProtocol extends IResponseProtocol {

    /** 发送聊天内容成功 */
    public static final String SEND_SUCCESS = "send_success";
    /** 接收公共聊天内容 */
    public static final String RECEIVE_PUBLIC_CHAT_MESSAGE = "receive_public_chat_message";

    private PublicChatMessage publicChatMessage;
    public PublicChatMessage getPublicChatMessage() {
        return publicChatMessage;
    }
    public void setPublicChatMessage(PublicChatMessage publicChatMessage) {
        this.publicChatMessage = publicChatMessage;
    }

    public ChatHallResponseProtocol() {}

    @Override
    @JSONField(serialize = false)
    public BaseProtoType getBaseType() {
        return BaseProtoType.BASE_CHAT_HALL;
    }
}
