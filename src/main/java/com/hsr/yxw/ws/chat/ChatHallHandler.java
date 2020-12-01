package com.hsr.yxw.ws.chat;

import com.alibaba.fastjson.JSONArray;
import com.hsr.yxw.ws.common.IHandler;
import com.hsr.yxw.ws.common.IResponseProtocol;
import com.hsr.yxw.ws.common.WsAccount;
import com.hsr.yxw.ws.heartbeat.HeartBeatResponseProtocol;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.Session;

@Component
public class ChatHallHandler implements IHandler<ChatHallRequestProtocol, ChatHallResponseProtocol> {
    private static final Log log = LogFactory.getLog(ChatHallHandler.class);
    @Autowired
    private ChatHallService chatHallService;

    @Override
    public IResponseProtocol handle(WsAccount wsAccount, Session session, ChatHallRequestProtocol requestProtocol) {
        if (requestProtocol == null) {
            return null;
        }
        if (ChatHallRequestProtocol.SEND_PUBLIC_CHAT_MESSAGE.equals(requestProtocol.getReqType())) {
            // 发送给所有没有屏蔽公共聊天消息的玩家
            chatHallService.sendPublicChatMessage(wsAccount, requestProtocol.getMessage());
        } else {
            log.error(String.format("玩家名称：%s，未知的请求类型：%s", wsAccount.getAccount().getUsername(), requestProtocol.getReqType()));
            return HeartBeatResponseProtocol.unknownProto(requestProtocol.getReqType());
        }
        log.info(String.format("成功处理请求，玩家名称：%s，请求结构：%s", wsAccount.getAccount().getUsername(), JSONArray.toJSONString(requestProtocol)));
        return null;
    }

    @Override
    public ChatHallRequestProtocol parseRequest(String message) {
        return JSONArray.parseObject(message, ChatHallRequestProtocol.class);
    }
}