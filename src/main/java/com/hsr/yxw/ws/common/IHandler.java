package com.hsr.yxw.ws.common;

public interface IHandler {
    BaseProtocol handle(String username, String message);
}
