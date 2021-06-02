package com.hsr.game.ws.common;

public abstract class IResponseProtocol extends IProtocol {
    private String resType;

    public String getResType() {
        return resType;
    }

    public void setResType(String resType) {
        this.resType = resType;
    }
}
