package com.hsr.yxw.ws.common;

import com.hsr.yxw.player.pojo.Player;

import javax.websocket.Session;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 封装了 websocketSession的玩家类
 */
public class WsPlayer {
    public enum Status {
        //在线
        online,
        //隐身
        hide,
    }
    private Player player;
    private ConcurrentHashMap<String, Session> wsSessions = new ConcurrentHashMap<>();
    /*默认在线状态*/
    private Status status = Status.online;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ConcurrentHashMap<String, Session> getWsSessions() {
        return wsSessions;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
