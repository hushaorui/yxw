package com.hsr.yxw.ws.chat;

import com.hsr.yxw.ws.common.BaseProtoType;
import com.hsr.yxw.ws.common.IRequestProtocol;

/**
 * 聊天大厅使用的协议
 */
public class ChatHallRequestProtocol extends IRequestProtocol {

    /** 发送公共聊天内容 */
    public static final String SEND_PUBLIC_CHAT_MESSAGE = "send_public_chat_message";
    /** 发送命令行游戏内容 */
    public static final String SEND_CMD_GAME_MESSAGE = "send_cmd_game_message";

    private String message;
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public ChatHallRequestProtocol() {}

    @Override
    public BaseProtoType getBaseType() {
        return BaseProtoType.BASE_CHAT_HALL;
    }
}
