package com.hsr.yxw.ws.chat;

import com.hsr.yxw.ws.common.BaseProtocol;

/**
 * 聊天大厅响应使用的协议
 */
public class ChatHallResponseProtocol extends BaseProtocol {

    /** 接收公共聊天内容 */
    public static final String RECEIVE_PUBLIC_CHAT_MESSAGE = "RECEIVE_PUBLIC_CHAT_MESSAGE";

    public ChatHallResponseProtocol() {}

    public ChatHallResponseProtocol(String result, String message) {
        super(result, message);
    }
}
