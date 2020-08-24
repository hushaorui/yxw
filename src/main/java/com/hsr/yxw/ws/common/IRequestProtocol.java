package com.hsr.yxw.ws.common;

import com.alibaba.fastjson.JSONArray;

public abstract class IRequestProtocol extends IProtocol {
    private String reqType;

    public String getReqType() {
        return reqType;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
    }

    public String toJsonString() {
        return JSONArray.toJSONString(this);
    }
}
