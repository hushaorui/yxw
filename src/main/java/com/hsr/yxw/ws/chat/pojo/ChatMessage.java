package com.hsr.yxw.ws.chat.pojo;

public class ChatMessage {
    // 数据库保存的自增id
    private Long id;
    // 发送者id
    private Long senderId;
    // 发送者 username
    private String senderName;
    // 发送的内容
    private String content;
    // 发送的时间
    private Long sendTime;
    // 接收者id，为null时是所有用户
    private Long receiverId;
    // 接收者 username 为null时是所有用户
    private String username;

}
