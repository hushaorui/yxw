package com.hsr.yxw.ws.chat;

import com.hsr.yxw.ws.common.BaseProtoType;
import com.hsr.yxw.ws.common.IRequestProtocol;

/**
 * 聊天大厅使用的协议
 */
public class ChatHallRequestIProtocol extends IRequestProtocol {

    /** 发送公共聊天内容 */
    public static final String SEND_PUBLIC_CHAT_MESSAGE = "SEND_PUBLIC_CHAT_MESSAGE";

    private String message;
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public ChatHallRequestIProtocol() {}

    @Override
    public BaseProtoType getBaseType() {
        return BaseProtoType.chat_hall;
    }
}
