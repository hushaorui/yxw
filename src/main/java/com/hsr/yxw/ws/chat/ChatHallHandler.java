package com.hsr.yxw.ws.chat;

import com.alibaba.fastjson.JSONArray;
import com.hsr.yxw.sysconfig.common.SystemSwitch;
import com.hsr.yxw.sysconfig.service.SystemConfigService;
import com.hsr.yxw.ws.chat.common.GmExeResult;
import com.hsr.yxw.ws.chat.service.GmMainService;
import com.hsr.yxw.ws.common.IHandler;
import com.hsr.yxw.ws.common.IResponseProtocol;
import com.hsr.yxw.ws.common.WsAccount;
import com.hsr.yxw.ws.heartbeat.HeartBeatResponseProtocol;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.util.List;

@Component
public class ChatHallHandler implements IHandler<ChatHallRequestProtocol, ChatHallResponseProtocol> {
    private static final Log log = LogFactory.getLog(ChatHallHandler.class);
    private ChatHallService chatHallService;
    private SystemConfigService systemConfigService;
    private GmMainService gmMainService;
    @Autowired
    public ChatHallHandler(ChatHallService chatHallService, SystemConfigService systemConfigService, GmMainService gmMainService) {
        this.chatHallService = chatHallService;
        this.systemConfigService = systemConfigService;
        this.gmMainService = gmMainService;
    }

    @Override
    public IResponseProtocol handle(WsAccount wsAccount, Session session, ChatHallRequestProtocol requestProtocol) {
        if (requestProtocol == null || requestProtocol.getMessage() == null) {
            return null;
        }
        String username = wsAccount.getAccount().getUsername();
        // 发送的内容
        String message = requestProtocol.getMessage();
        if (ChatHallRequestProtocol.SEND_PUBLIC_CHAT_MESSAGE.equals(requestProtocol.getReqType())) {
            // 查看命令行开关是否已打开
            boolean openGM = systemConfigService.getBoolValueNotNull(SystemSwitch.OPEN_PUBLIC_CHAT_GM_SWITCH);
            if (openGM && message.startsWith("*")) {
                // 执行GM命令
                List<GmExeResult> gmExeResultList = gmMainService.executeCmd(message);
                // 日志
                log.info(String.format("玩家名称：%s，执行GM命令：%s", username, JSONArray.toJSONString(gmExeResultList)));
            } else {
                //发送前，需要去除不可见字符，屏蔽字等
                // 发送给所有没有屏蔽公共聊天消息的玩家
                chatHallService.sendPublicChatMessage(wsAccount, message);
                log.info(String.format("成功处理请求，玩家名称：%s，请求结构：%s", username, JSONArray.toJSONString(requestProtocol)));
            }
        } else if (ChatHallRequestProtocol.SEND_CMD_GAME_MESSAGE.equals(requestProtocol.getReqType())) {
            // 命令行游戏信息
            // TODO
        } else {
            log.error(String.format("玩家名称：%s，未知的请求类型：%s", username, requestProtocol.getReqType()));
            return HeartBeatResponseProtocol.unknownProto(requestProtocol.getReqType());
        }
        ChatHallResponseProtocol response = new ChatHallResponseProtocol();
        response.setResType(ChatHallResponseProtocol.SEND_SUCCESS);
        return response;
    }

    @Override
    public ChatHallRequestProtocol parseRequest(String message) {
        return JSONArray.parseObject(message, ChatHallRequestProtocol.class);
    }
}
