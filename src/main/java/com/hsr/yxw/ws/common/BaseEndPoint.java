package com.hsr.yxw.ws.common;

import com.hsr.yxw.exception.ServiceException;
import com.hsr.yxw.player.pojo.Player;
import com.hsr.yxw.player.service.PlayerService;
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
@ServerEndpoint("/ws/{id}")
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
    private static WsCommonService wsCommonService = WsCommonService.getInstance();

    @OnOpen
    public void onOpen(@PathParam("id") Long id, Session session) throws ServiceException {
        System.out.println("新的连接，用户id：" + id);
        Player player = playerService.getPlayerById(id);
        HeartBeatResponseProtocol heartBeatResponseProtocol;
        // 创建心跳协议类型的基础协议
        if (player == null) {
            // 心跳协议的响应协议
            heartBeatResponseProtocol = new HeartBeatResponseProtocol(HeartBeatResponseProtocol.CONNECT_FAILED, "用户id " + id + " 不存在，连接失败");
            // 给连接的用户发送连接失败信息
            wsCommonService.sendMessage(session, heartBeatResponseProtocol);
            return;
        }
        boolean success = PlayerWebSocketPool.addToOnline(player, session);
        if (success) {
            heartBeatResponseProtocol = HeartBeatResponseProtocol.connectSuccess();
        } else {
            heartBeatResponseProtocol = HeartBeatResponseProtocol.alreadyLogin();
        }
        // 给连接的用户发送信息
        wsCommonService.sendMessage(session, heartBeatResponseProtocol);
        System.out.println("在线人数" + PlayerWebSocketPool.count());
        PlayerWebSocketPool.getAllPlayerMap().keySet().forEach(item -> System.out.println("当前所有在线用户：" + item));
    }

    @OnMessage
    public void onMessage(@PathParam("id") Long id, String message){
        if (! StringUtils.isEmpty(message)) {
            IResponseProtocol responseProtocol;
            try {
                BaseProtocol baseProtocol = BaseProtocol.parse(message);
                if (baseProtocol == null) {
                    return;
                }
                // 能够正确解析 {"":type, "message": "{"type":type, "message":msg....}"}
                if (! wsBaseHandler.containSubProtocol(baseProtocol.getBaseType())) {
                    responseProtocol = new HeartBeatResponseProtocol(HeartBeatResponseProtocol.UNKNOWN_PROTO,
                            "未知的基础协议名称：" + baseProtocol.getBaseType());
                    wsCommonService.sendMessage(id, responseProtocol);
                    return;
                }
                if (StringUtils.isEmpty(baseProtocol.getProtoString())) {
                    // 空白的协议
                    responseProtocol = HeartBeatResponseProtocol.emptyProto();
                } else {
                    // 交给对应的处理类进行处理
                    responseProtocol = wsBaseHandler.handle(id, baseProtocol.getBaseType(), baseProtocol.getProtoString());
                }
            } catch (Exception e) {
                responseProtocol = HeartBeatResponseProtocol.notFormat(message);
            }
            // 发送响应信息，可能为空，处理器可自行发送信息
            if (responseProtocol != null) {
                wsCommonService.sendMessage(id, responseProtocol);
            }
        }
    }

    @OnClose
    public void onClose(@PathParam("id") Long id,Session session){
        System.out.println(id + " 连接关闭");
        PlayerWebSocketPool.offLine(id);
        System.out.println("在线人数：" + PlayerWebSocketPool.count());
        PlayerWebSocketPool.getAllPlayerMap().keySet().forEach(item -> System.out.println("当前所有在线用户：" + item));
        for (Map.Entry<Long, WsPlayer> item : PlayerWebSocketPool.getAllPlayerMap().entrySet()){
            System.out.println("剩余用户id: " + item.getKey());
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
