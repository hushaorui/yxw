package com.hsr.yxw.ws.chat.pojo;

import com.hsr.yxw.ws.chat.common.ChatMessageType;

public class ChatMessage {
    // 数据库保存的自增id
    private Long id;
    // 发送者id
    private Long senderId;
    // 发送者 username
    private String senderName;
    // 发送的时间
    private Long sendTime;
    // 聊天信息类型
    private ChatMessageType messageType;
    // 接收者id，为null时是所有用户
    private Long receiverId;
    // 接收者 receiverName 为null时是所有用户
    private String receiverName;
    // 发送的内容
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public Long getSendTime() {
        return sendTime;
    }

    public void setSendTime(Long sendTime) {
        this.sendTime = sendTime;
    }

    public ChatMessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(ChatMessageType messageType) {
        this.messageType = messageType;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}