package com.hsr.yxw.ws.chat;

import com.hsr.yxw.ws.common.BaseProtocol;

/**
 * 聊天大厅使用的协议
 */
public class ChatHallRequestProtocol extends BaseProtocol {

    /** 发送公共聊天内容 */
    public static final String SEND_PUBLIC_CHAT_MESSAGE = "SEND_PUBLIC_CHAT_MESSAGE";

    public ChatHallRequestProtocol() {}

    public ChatHallRequestProtocol(String result, String message) {
        super(result, message);
    }
}
