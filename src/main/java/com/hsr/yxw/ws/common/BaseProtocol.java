package com.hsr.yxw.ws.common;

import com.alibaba.fastjson.JSONArray;

/***
 * 基础协议
 */
public class BaseProtocol {
    private BaseProtoType baseType;
    private String protoString;

    private BaseProtocol(BaseProtoType baseType) {
        this.baseType = baseType;
    }
    private BaseProtocol(BaseProtoType baseType, IResponseProtocol subProto) {
        this.baseType = baseType;
        this.protoString = subProto.toJsonString();
    }

    public BaseProtoType getBaseType() {
        return baseType;
    }

    public String getProtoString() {
        return protoString;
    }

//    public void setProto(IResponseProtocol subProto) {
//        this.protoString = subProto.toJsonString();
//    }

    public static BaseProtocol buildResponse(IResponseProtocol subProto) {
        return new BaseProtocol(subProto.getResType(), subProto);
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
    public String toString() {
        return JSONArray.toJSONString(this);
    }
}
