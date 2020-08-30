package com.hsr.yxw.ws.common;

import javax.websocket.Session;

public interface IHandler<REQ extends IRequestProtocol, RES extends IResponseProtocol> {
    /**
     * 处理请求
     * @param wsPlayer 发送者
     * @param requestProtocol 请求协议对象
     * @return 可为null
     */
    IResponseProtocol handle(WsPlayer wsPlayer, Session session, REQ requestProtocol);

    REQ parseRequest(String message);
}
