package com.hsr.yxw.ws.heartbeat.pojo;

import com.alibaba.fastjson.JSONArray;
import com.hsr.yxw.ws.common.PlayerWebSocketPool;

/**
 * 提供给每个玩家的服务器信息类
 */
public class WsServerInfo {
    /*玩家在线人数*/
    private int playerCount;
    /**服务器时间*/
    private long serverTime;
    /**(信息从客户端到服务端)延迟: ms*/
    private long ping;

    public int getPlayerCount() {
        return playerCount;
    }
    public long getServerTime() {
        return serverTime;
    }
    public long getPing() {
        return ping;
    }
    public WsServerInfo(long clientTime) {
        this.playerCount = PlayerWebSocketPool.count();
        this.serverTime = System.currentTimeMillis();
        this.ping = serverTime - clientTime;
        if (this.ping < 0) {
            this.ping = - this.ping;
        }
    }
    public String toJsonString() {
        return JSONArray.toJSONString(this);
    }
}
