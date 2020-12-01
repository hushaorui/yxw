package com.hsr.yxw.util.test;

import com.alibaba.fastjson.JSONArray;
import com.hsr.yxw.ws.chat.common.ChatMessageType;
import org.junit.Test;

public class TestChatMessageType {

    @Test
    public void test_ChatMessageType() {
        ChatMessageType type = ChatMessageType.PRIVATE;
        System.out.println("ToString: " + type.toString());
        System.out.println("ValueOf: " + type);
        System.out.println("JsonArray: " + JSONArray.toJSONString(type));
        System.out.println("JsonArray: " + JSONArray.toJSONString(ChatMessageType.getMapping()));
    }
}