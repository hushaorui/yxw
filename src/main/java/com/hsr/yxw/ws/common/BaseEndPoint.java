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
        // 创建心跳协议类型的基础协议
        BaseProtocol baseProtocol = new BaseProtocol(WsProtoConstants.heart_beat_protocol);
        if (player == null) {
            // 心跳协议的响应协议
            HeartBeatResponseProtocol heartBeatResponseProtocol = new HeartBeatResponseProtocol(HeartBeatResponseProtocol.CONNECT_FAILED, "用户id " + id + " 不存在，连接失败");
            baseProtocol.setMessage(heartBeatResponseProtocol.toJsonString());
            // 给连接的用户发送连接失败信息
            wsCommonService.sendMessage(session, baseProtocol);
            return;
        }
        boolean success = PlayerWebSocketPool.addToOnline(player, session);
        if (success) {
            baseProtocol.setMessage(HeartBeatResponseProtocol.connectSuccess());
        } else {
            baseProtocol.setMessage(HeartBeatResponseProtocol.alreadyLogin());
        }
        // 给连接的用户发送信息
        wsCommonService.sendMessage(session, baseProtocol);

        System.out.println("在线人数" + PlayerWebSocketPool.count());
        PlayerWebSocketPool.getAllPlayerMap().keySet().forEach(item -> System.out.println("当前所有在线用户：" + item));

    }

    @OnMessage
    public void onMessage(@PathParam("id") Long id, String message){
        if (! StringUtils.isEmpty(message)) {
            BaseProtocol baseProtocol;
            try {
                baseProtocol = BaseProtocol.parseStringToProtoCol(message, BaseProtocol.class);
                if (baseProtocol == null) {
                    return;
                }
                // 能够正确解析 {"":type, "message": "{"type":type, "message":msg....}"}
                if (! wsBaseHandler.containProtocolName(baseProtocol.getType())) {
                    baseProtocol = new BaseProtocol(WsProtoConstants.heart_beat_protocol);
                    HeartBeatResponseProtocol heartBeatResponseProtocol = new HeartBeatResponseProtocol(HeartBeatResponseProtocol.UNKNOWN_PROTO,
                            "未知的基础协议名称：" + baseProtocol.getType());
                    baseProtocol.setMessage(heartBeatResponseProtocol);
                    return;
                }
                if (StringUtils.isEmpty(baseProtocol.getMessage())) {
                    // 空白的协议
                    baseProtocol.setMessage(HeartBeatResponseProtocol.emptyProto());
                } else {
                    // 交给对应的处理类进行处理
                    baseProtocol = wsBaseHandler.handle(id, baseProtocol.getType(), baseProtocol.getMessage());
                }
            } catch (Exception e) {
                baseProtocol = new BaseProtocol(WsProtoConstants.heart_beat_protocol);
                baseProtocol.setMessage(HeartBeatResponseProtocol.notFormat(message));
            }
            // 发送响应信息，可能为空，处理器可自行发送信息
            if (baseProtocol != null) {
                wsCommonService.sendMessage(id, baseProtocol);
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
