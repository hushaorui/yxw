package com.hsr.yxw.ws.heartbeat;

import com.hsr.yxw.ws.common.BaseProtoType;
import com.hsr.yxw.ws.common.IRequestProtocol;

/**
 * 每隔一定时间，浏览器会发送心跳协议
 */
public class HeartBeatRequestProtocol extends IRequestProtocol {
    /** 心跳 */
    static final String SUB_HEART_BEAT = "SUB_HEART_BEAT";

    // 客户端当前时间
    private long clientTime;

    public long getClientTime() {
        return clientTime;
    }
    public void setClientTime(long clientTime) {
        this.clientTime = clientTime;
    }

    @Override
    public BaseProtoType getBaseType() {
        return BaseProtoType.BASE_HEART_BEAT;
    }
}