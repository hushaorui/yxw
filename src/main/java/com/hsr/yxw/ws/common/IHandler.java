package com.hsr.yxw.ws.common;

public interface IHandler {
    /**
     * 处理请求
     * @param senderId 发送者id
     * @param jsonString 能够被解析为协议对象的json字符串
     * @return 可为null
     */
    BaseProtocol handle(Long senderId, String jsonString);
}
