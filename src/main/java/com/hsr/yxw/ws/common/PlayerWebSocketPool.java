package com.hsr.yxw.ws.common;

import com.hsr.yxw.player.pojo.Player;
import lombok.extern.slf4j.Slf4j;

import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class PlayerWebSocketPool {

    //在线用户websocket连接
    private static final Map<Long, WsPlayer> ONLINE_PLAYERS = new ConcurrentHashMap<>();

    /**
     * 新增一个在线连接
     * @param player
     * @param session
     */
    public static boolean addToOnline(Player player, Session session){
        if (player != null && session != null){
            if (ONLINE_PLAYERS.containsKey(player.getId())) {
                // 已经登录了，不允许登录
                return false;
            }
            // 在线状态是无法登陆的，防止挤号
            WsPlayer wsPlayer = new WsPlayer();
            wsPlayer.setPlayer(player);
            wsPlayer.setWsSession(session);
            ONLINE_PLAYERS.put(player.getId(), wsPlayer);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 离线
     * @param id
     */
    public static void offLine(Long id){
        if (id != null){
            ONLINE_PLAYERS.remove(id);
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
    public static Map<Long, WsPlayer> getAllPlayerMap(){
        return ONLINE_PLAYERS;
    }
    public static WsPlayer getWsPlayer(Long id) {
        return ONLINE_PLAYERS.get(id);
    }
}