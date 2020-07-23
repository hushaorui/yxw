package com.hsr.yxw.ws.common;

import com.hsr.yxw.pojo.Player;
import lombok.extern.slf4j.Slf4j;

import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class PlayerWebSocketPool {

    //在线用户websocket连接
    private static final Map<String, WsPlayer> ONLINE_PLAYERS = new ConcurrentHashMap<>();

    /**
     * 新增一个在线连接
     * @param player
     * @param session
     */
    public static void addToOnline(Player player, Session session){
        if (player != null && session != null){
            // 在线状态是无法登陆的，防止挤号
            WsPlayer wsPlayer = new WsPlayer();
            wsPlayer.setPlayer(player);
            wsPlayer.setWsSession(session);
            ONLINE_PLAYERS.put(player.getUsername(), wsPlayer);
        }
    }

    /**
     * 离线
     * @param key
     */
    public static void offLine(String key){
        if (!key.isEmpty()){
            ONLINE_PLAYERS.remove(key);
        }
    }

    /**
     * 获取在线人数
     * @return
     */
    public static int count(){
        return ONLINE_PLAYERS.size();
    }

    /**
     * 获取在线session池 (此方法不安全)
     * @return
     */
    public static Map<String, WsPlayer> getAllPlayerMap(){
        return ONLINE_PLAYERS;
    }
    public static WsPlayer getWsPlayer(String username) {
        return ONLINE_PLAYERS.get(username);
    }
}