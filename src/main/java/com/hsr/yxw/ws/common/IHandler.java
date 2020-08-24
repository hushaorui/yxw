package com.hsr.yxw.ws.common;

public interface IHandler<REQ extends IRequestProtocol, RES extends IResponseProtocol> {
    /**
     * 处理请求
     * @param senderId 发送者id
     * @param requestProtocol 请求协议对象
     * @return 可为null
     */
    RES handle(Long senderId, REQ requestProtocol);

    REQ parseRequest(String message);
}
