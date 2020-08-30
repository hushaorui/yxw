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
    public static void addToOnline(Player player, Session session){
        if (player != null && session != null) {
            WsPlayer wsPlayer = ONLINE_PLAYERS.get(player.getId());
            if (wsPlayer == null) {
                wsPlayer = new WsPlayer();
                wsPlayer.setPlayer(player);
                ONLINE_PLAYERS.put(player.getId(), wsPlayer);
            }
            wsPlayer.getWsSessions().put(session.getId(), session);
        } else {
            throw new RuntimeException("代码错误！");
        }
    }

    /**
     * session离线，用户离线返回true
     * @param id
     */
    public static boolean offLine(Long id, Session session){
        WsPlayer wsPlayer = ONLINE_PLAYERS.get(id);
        if (wsPlayer != null) {
            wsPlayer.getWsSessions().remove(session.getId());
            if (wsPlayer.getWsSessions().isEmpty()){
                // 如果该用户没有任何一个连接，则该用户离线，将之移出
                ONLINE_PLAYERS.remove(id);
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