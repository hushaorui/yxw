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

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public long getServerTime() {
        return serverTime;
    }

    public void setServerTime(long serverTime) {
        this.serverTime = serverTime;
    }
    public WsServerInfo() {
        this.playerCount = PlayerWebSocketPool.count();
        this.serverTime = System.currentTimeMillis();
    }
    public String toJsonString() {
        return JSONArray.toJSONString(this);
    }
}
