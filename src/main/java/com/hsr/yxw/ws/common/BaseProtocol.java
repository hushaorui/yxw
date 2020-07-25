package com.hsr.yxw.ws.common;

/***
 * 基础协议
 */
public class BaseProtocol extends ProtocolIF {
    public BaseProtocol(String type, String message) {
        setType(type);
        setMessage(message);
    }
    public BaseProtocol() {}

    public BaseProtocol(String type) {
        setType(type);
    }
}
