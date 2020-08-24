package com.hsr.yxw.ws.common;

import com.alibaba.fastjson.JSONArray;

public abstract class IResponseProtocol extends IProtocol {
    private String resType;

    public String getResType() {
        return resType;
    }

    public void setResType(String resType) {
        this.resType = resType;
    }

    public String toJsonString() {
        return JSONArray.toJSONString(this);
    }
}
