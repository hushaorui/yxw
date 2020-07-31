package com.hsr.yxw.ws.heartbeat;

import com.hsr.yxw.ws.common.ProtocolIF;

/**
 * 心跳响应协议，其他协议不满足条件时，都可以返回心跳响应协议
 */
public class HeartBeatResponseProtocol extends ProtocolIF {
    /** 连接成功 */
    public static final String CONNECT_SUCCESS = "CONNECT_SUCCESS";
    /** 连接失败 */
    public static final String CONNECT_FAILED = "CONNECT_FAILED";
    /** 不符合格式 */
    public static final String NOT_FORMAT = "NOT_FORMAT";
    /** 空白的协议 */
    public static final String EMPTY_PROTO = "EMPTY_PROTO";
    /** 未知的协议 */
    public static final String UNKNOWN_PROTO = "UNKNOWN_PROTO";
    /** 服务器信息 */
    public static final String SERVER_INFO = "SERVER_INFO";
    /** 账号已经被登录了，无法顶号 */
    private static final String ALREADY_LOGIN = "ALREADY_LOGIN";

    public HeartBeatResponseProtocol() {}

    public HeartBeatResponseProtocol(String type, String message) {
        setType(type);
        setMessage(message);
    }

    /** 未知的协议类型 */
    public static HeartBeatResponseProtocol unknownProto(String protoName) {
        return new HeartBeatResponseProtocol(HeartBeatResponseProtocol.UNKNOWN_PROTO, "未知的协议类型：" + protoName);
    }

    /** 连接成功 */
    public static HeartBeatResponseProtocol connectSuccess() {
        return new HeartBeatResponseProtocol(HeartBeatResponseProtocol.CONNECT_SUCCESS, "连接成功");
    }
    /** 空白的协议内容 */
    public static HeartBeatResponseProtocol emptyProto() {
        return new HeartBeatResponseProtocol(HeartBeatResponseProtocol.EMPTY_PROTO, "空白的协议内容！");
    }
    /** 不符合格式的协议 */
    public static HeartBeatResponseProtocol notFormat(String message) {
        return new HeartBeatResponseProtocol(HeartBeatResponseProtocol.NOT_FORMAT, "不符合格式的协议内容 " + message);
    }
    /** 空白的协议内容 */
    public static HeartBeatResponseProtocol alreadyLogin() {
        return new HeartBeatResponseProtocol(HeartBeatResponseProtocol.ALREADY_LOGIN, "此账号已被登录！");
    }
    /** 不符合格式的协议 */
    public static HeartBeatResponseProtocol notFormat() {
        return notFormat("");
    }
}
