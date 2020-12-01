package com.hsr.yxw.ws.common;

import com.hsr.yxw.account.pojo.Account;

import javax.websocket.Session;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 封装了 websocketSession的玩家类
 */
public class WsAccount {
    public enum Status {
        //在线
        online,
        //隐身
        hide,
    }
    private Account account;
    private ConcurrentHashMap<String, Session> wsSessions = new ConcurrentHashMap<>();
    /*默认在线状态*/
    private Status status = Status.online;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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