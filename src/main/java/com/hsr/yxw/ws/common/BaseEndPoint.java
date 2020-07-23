package com.hsr.yxw.ws.common;

import com.hsr.yxw.exception.ServiceException;
import com.hsr.yxw.pojo.Player;
import com.hsr.yxw.service.PlayerService;
import com.hsr.yxw.ws.heartbeat.HeartBeatResponseProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;

@Component
@ServerEndpoint("/ws/{username}")
public class BaseEndPoint {


    private static PlayerService playerService;
    @Autowired
    public void setUserService(PlayerService playerService){
        BaseEndPoint.playerService = playerService;
    }
    private static WsBaseHandler wsBaseHandler;
    @Autowired
    public void setUserService(WsBaseHandler wsBaseHandler){
        BaseEndPoint.wsBaseHandler = wsBaseHandler;
    }

    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) throws ServiceException {
        System.out.println("新的连接，用户名：" + username);
        Player player = playerService.getPlayerByUsername(username);
        // 创建心跳协议类型的基础协议
        BaseProtocol baseProtocol = new BaseProtocol(WsProtoConstants.heart_beat_protocol);
        if (player == null) {
            // 心跳协议的响应协议
            HeartBeatResponseProtocol heartBeatResponseProtocol = new HeartBeatResponseProtocol(HeartBeatResponseProtocol.CONNECT_FAILED, "用户名 " + username + " 不存在，连接失败");
            baseProtocol.setMessage(heartBeatResponseProtocol.toJsonString());
            // 给连接的用户发送连接失败信息
            wsBaseHandler.sendMessage(session, baseProtocol);
            return;
        }
        PlayerWebSocketPool.addToOnline(player, session);
        baseProtocol.setMessage(HeartBeatResponseProtocol.connectSuccess());
        // 给连接的用户发送信息
        wsBaseHandler.sendMessage(session, baseProtocol);

        System.out.println("在线人数" + PlayerWebSocketPool.count());
        PlayerWebSocketPool.getAllPlayerMap().keySet().forEach(item -> System.out.println("当前所有在线用户：" + item));

    }

    @OnMessage
    public void onMessage(@PathParam("username") String username, String message){
        System.out.println("有新消息： " + message + "  发送者：" + username);
        if (! StringUtils.isEmpty(message)) {
            BaseProtocol baseProtocol;
            try {
                baseProtocol = BaseProtocol.parseStringToProtoCol(message);
                // 能够正确解析 {"":type, "message": "{"type":type, "message":msg....}"}
                if (! wsBaseHandler.containProtocolName(baseProtocol.getType())) {
                    baseProtocol = new BaseProtocol(WsProtoConstants.heart_beat_protocol);
                    HeartBeatResponseProtocol heartBeatResponseProtocol = new HeartBeatResponseProtocol(HeartBeatResponseProtocol.UNKNOWN_PROTO,
                            "未知的基础协议名称：" + baseProtocol.getType());
                    baseProtocol.setMessage(heartBeatResponseProtocol);
                    return;
                }
                // 交给对应的处理类进行处理
                baseProtocol = wsBaseHandler.handle(username, baseProtocol.getType(), baseProtocol.getMessage());
            } catch (Exception e) {
                baseProtocol = new BaseProtocol(WsProtoConstants.heart_beat_protocol);
                baseProtocol.setMessage(HeartBeatResponseProtocol.notFormat(message));
            }
            // 发送响应信息
            wsBaseHandler.sendMessage(username, baseProtocol);
        }
    }

    @OnClose
    public void onClose(@PathParam("username") String username,Session session){
        System.out.println(username + "连接关闭");
        PlayerWebSocketPool.offLine(username);
        System.out.println("在线人数：" + PlayerWebSocketPool.count());
        PlayerWebSocketPool.getAllPlayerMap().keySet().forEach(item -> System.out.println("当前所有在线用户：" + item));
        for (Map.Entry<String, WsPlayer> item : PlayerWebSocketPool.getAllPlayerMap().entrySet()){
            System.out.println("12: {}" + item.getKey());
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable){
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("连接出现异常： {}" + throwable);
    }

}
