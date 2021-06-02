package com.hsr.game.ws.common;

public abstract class IRequestProtocol extends IProtocol {
    private String reqType;

    public String getReqType() {
        return reqType;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
    }
}
