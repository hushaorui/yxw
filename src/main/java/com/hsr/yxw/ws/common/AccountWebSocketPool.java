package com.hsr.yxw.ws.common;

import com.hsr.yxw.account.pojo.Account;
import lombok.extern.slf4j.Slf4j;

import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class AccountWebSocketPool {

    //在线用户websocket连接
    private static final Map<Long, WsAccount> ONLINE_ACCOUNTS = new ConcurrentHashMap<>();

    /**
     * 新增一个在线连接
     * @param account
     * @param session
     */
    public static void addToOnline(Account account, Session session){
        if (account != null && session != null) {
            WsAccount wsAccount = ONLINE_ACCOUNTS.get(account.getId());
            if (wsAccount == null) {
                wsAccount = new WsAccount();
                wsAccount.setAccount(account);
                ONLINE_ACCOUNTS.put(account.getId(), wsAccount);
            }
            wsAccount.getWsSessions().put(session.getId(), session);
        } else {
            throw new RuntimeException("代码错误！");
        }
    }

    /**
     * session离线，用户离线返回true
     * @param id
     */
    public static boolean offLine(Long id, Session session){
        WsAccount wsAccount = ONLINE_ACCOUNTS.get(id);
        if (wsAccount != null) {
            wsAccount.getWsSessions().remove(session.getId());
            if (wsAccount.getWsSessions().isEmpty()){
                // 如果该用户没有任何一个连接，则该用户离线，将之移出
                ONLINE_ACCOUNTS.remove(id);
                return true;
            }
        }
        return false;
    }
    /**
     * 获取在线人数
     * @return
     */
    public static int count(){
        return ONLINE_ACCOUNTS.size();
    }

    /**
     * 获取在线session池 (此方法不安全)
     * @return
     */
    public static Map<Long, WsAccount> getAllAccountMap(){
        return ONLINE_ACCOUNTS;
    }
    public static WsAccount getWsAccount(Long id) {
        return ONLINE_ACCOUNTS.get(id);
    }
}