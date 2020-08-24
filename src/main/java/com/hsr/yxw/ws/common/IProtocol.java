package com.hsr.yxw.ws.common;

import com.alibaba.fastjson.JSONArray;

import java.lang.reflect.Type;

public abstract class IProtocol {

    public abstract BaseProtoType getBaseType();

    public String toJsonString() {
        return JSONArray.toJSONString(this);
    }
    public String toString() {
        return JSONArray.toJSONString(this);
    }

    public static <T> T parseToObj(String jsonString, Class<?> clazz) {
        try {
            return JSONArray.parseObject(jsonString, (Type) clazz);
        } catch (Exception ignore) {
            return null;
        }
    }
}
