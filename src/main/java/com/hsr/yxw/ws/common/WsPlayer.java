package com.hsr.yxw.ws.common;

import com.hsr.yxw.pojo.Player;

import javax.websocket.Session;

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
    private Session wsSession;
    /*默认在线状态*/
    private Status status = Status.online;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Session getWsSession() {
        return wsSession;
    }

    public void setWsSession(Session wsSession) {
        this.wsSession = wsSession;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
