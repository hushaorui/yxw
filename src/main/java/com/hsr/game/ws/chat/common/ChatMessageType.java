package com.hsr.game.ws.chat.common;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum ChatMessageType {
    PUBLIC(0, "公共聊天"), // 公共聊天
    SYSTEM(1, "系统通告"), // 系统通告
    ;
    private int id;
    private String desc;
    private static Map<String, String> mapping;
    static {
        mapping = new HashMap<>();
        ChatMessageType[] values = ChatMessageType.values();
        Map<String, String> tempMap = new HashMap<>(values.length, 1.5f);
        for (ChatMessageType type : values) {
            tempMap.put(type.toString(), type.getDesc());
        }
        mapping = Collections.unmodifiableMap(tempMap);
    }
    public static Map<String, String> getMapping() {
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
