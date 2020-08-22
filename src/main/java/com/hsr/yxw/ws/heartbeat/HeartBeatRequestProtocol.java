package com.hsr.yxw.ws.heartbeat;

import com.hsr.yxw.ws.common.ProtocolIF;

/**
 * 每隔一定时间，浏览器会发送心跳协议
 */
public class HeartBeatRequestProtocol extends ProtocolIF {
    /** 心跳 */
    static final String HEART_BEAT = "HEART_BEAT";

    public HeartBeatRequestProtocol() {}
    public HeartBeatRequestProtocol(String type, String message) {
        setType(type);
        setMessage(message);
    }
    // 客户端当前时间
    private long clientTime;

    public long getClientTime() {
        return clientTime;
    }
    public void setClientTime(long clientTime) {
        this.clientTime = clientTime;
    }
}
