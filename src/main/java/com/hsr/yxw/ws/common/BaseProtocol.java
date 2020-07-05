package com.hsr.yxw.ws.common;

import com.alibaba.fastjson.JSONArray;

import java.lang.reflect.Type;

/***
 * 基础协议
 */
public class BaseProtocol {

    private String type;
    private String message;

    public BaseProtocol(String type, String message) {
        this.type = type;
        this.message = message;
    }
    public BaseProtocol(String type) {
        this.type = type;
    }
    public BaseProtocol() {}

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
    public void setMessage(BaseProtocol protocol) {
        this.message = protocol.toJsonString();
    }

    public String toJsonString() {
        return JSONArray.toJSONString(this);
    }

    public static  <T> T parseStringToProtoCol(String jsonString, Class<? extends BaseProtocol> clazz) {
        return JSONArray.parseObject(jsonString, (Type) clazz);
    }
    public static  <T> T parseStringToProtoCol(String jsonString) {
        return JSONArray.parseObject(jsonString, (Type) BaseProtocol.class);
    }

}
