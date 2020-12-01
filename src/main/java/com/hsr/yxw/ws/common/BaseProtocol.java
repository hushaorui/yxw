package com.hsr.yxw.ws.common;

import com.alibaba.fastjson.JSONArray;

/***
 * 基础协议
 */
public class BaseProtocol {
    private BaseProtoType baseType;
    private String protoString;

    private BaseProtocol(BaseProtoType baseType, IResponseProtocol subProto) {
        this.baseType = baseType;
        if (subProto != null) {
            this.protoString = subProto.toJsonString();
        }
    }
    /**
     * 空参构造方法不允许删除，删除会导致解析为json字符串失败，但是也不能调用此构造方法
     */
    private BaseProtocol() {}

    public BaseProtoType getBaseType() {
        return baseType;
    }

    public void setBaseType(BaseProtoType baseType) {
        this.baseType = baseType;
    }

    public void setProtoString(String protoString) {
        this.protoString = protoString;
    }

    public String getProtoString() {
        return protoString;
    }

    public static BaseProtocol buildResponse(IResponseProtocol subProto) {
        if (subProto == null) {
            return new BaseProtocol(BaseProtoType.BASE_HEART_BEAT, subProto);
        }
        return new BaseProtocol(subProto.getBaseType(), subProto);
    }

    public static BaseProtocol parse(String jsonString) {
        try {
            return JSONArray.parseObject(jsonString, BaseProtocol.class);
        } catch (Exception ignore) {
            return null;
        }
    }

    public String toJsonString() {
        return JSONArray.toJSONString(this);
    }

    @Override
    public String toString() {
        return toJsonString();
    }
}