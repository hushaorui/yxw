package com.hsr.yxw.ws.connect;

import com.hsr.yxw.ws.common.BaseProtocol;
import com.hsr.yxw.ws.common.IHandler;
import org.springframework.stereotype.Component;

@Component
public class ConnectHandler implements IHandler {

    @Override
    public BaseProtocol handle(String message) {
        return null;
    }
}
