package com.hsr.yxw.ws.heartbeat;

import com.hsr.yxw.ws.common.BaseProtocol;

/**
 * 每隔一定时间，浏览器会发送心跳协议
 */
public class HeartBeatRequestProtocol extends BaseProtocol {
    /** 心跳 */
    public static final String HEART_BEAT = "HEART_BEAT";
    public HeartBeatRequestProtocol() {}
    public HeartBeatRequestProtocol(String result, String message) {
        super(result, message);
    }
}
