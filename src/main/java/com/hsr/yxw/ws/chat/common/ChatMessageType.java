package com.hsr.yxw.ws.chat.common;

import java.util.HashMap;

public enum ChatMessageType {
    PUBLIC(0, "公共聊天"), // 公共聊天
    SYSTEM(1, "系统通告"), // 系统通告
    ;
    private int id;
    private String desc;
    private static HashMap<String, String> mapping = new HashMap<>();
    static {
        for (ChatMessageType type : ChatMessageType.values()) {
            mapping.put(type.toString(), type.getDesc());
        }
    }
    public static HashMap<String, String> getMapping() {
        return mapping;
    }
    public int getId() {
        return id;
    }
    public String getDesc() {
        return desc;
    }
    ChatMessageType(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }
}
