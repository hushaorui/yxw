package com.hsr.yxw.ws.common;

import com.alibaba.fastjson.JSONArray;

import java.lang.reflect.Type;

public class ProtocolIF {
    private String type;
    private String message;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public void setMessage(ProtocolIF protocol) {
        this.message = protocol.toJsonString();
    }

    public String toJsonString() {
        return JSONArray.toJSONString(this);
    }

    public static  <T> T parseStringToProtoCol(String jsonString, Class<? extends ProtocolIF> clazz) {
        return JSONArray.parseObject(jsonString, (Type) clazz);
    }
    public static  <T> T parseStringToProtoCol(String jsonString) {
        return JSONArray.parseObject(jsonString, (Type) ProtocolIF.class);
    }
}
