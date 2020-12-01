package com.hsr.yxw.ws.common;

import javax.websocket.Session;

public interface IHandler<REQ extends IRequestProtocol, RES extends IResponseProtocol> {
    /**
     * 处理请求
     * @param wsAccount 发送者
     * @param requestProtocol 请求协议对象
     * @return 可为null
     */
    IResponseProtocol handle(WsAccount wsAccount, Session session, REQ requestProtocol);

    REQ parseRequest(String message);
}