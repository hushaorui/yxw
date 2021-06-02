package com.hsr.game.ws.heartbeat;

import com.alibaba.fastjson.annotation.JSONField;
import com.hsr.game.common.LongIdInfoIF;
import com.hsr.game.ws.common.BaseProtoType;
import com.hsr.game.ws.common.IResponseProtocol;

import java.util.List;
import java.util.Map;

/**
 * 心跳响应协议，其他协议不满足条件时，都可以返回心跳响应协议
 */
public class HeartBeatResponseProtocol extends IResponseProtocol {
    /** 连接成功 */
    public static final String CONNECT_SUCCESS = "connect_success";
    /** 连接失败 */
    public static final String CONNECT_FAILED = "CONNECT_FAILED";
    /** 不符合格式 */
    public static final String NOT_FORMAT = "NOT_FORMAT";
    /** 空白的协议 */
    public static final String EMPTY_PROTO = "EMPTY_PROTO";
    /** 未知的协议 */
    public static final String UNKNOWN_PROTO = "UNKNOWN_PROTO";
    /** 服务器信息 */
    public static final String SERVER_INFO = "server_info";
    /** 账号已经被登录了，无法顶号 */
    private static final String ALREADY_LOGIN = "ALREADY_LOGIN";

    private String message;

    private Map<String, List<? extends LongIdInfoIF>> allCfg; // 所有的配置列表

    public HeartBeatResponseProtocol() {}

    public HeartBeatResponseProtocol(String resType, String message) {
        this.setResType(resType);
        this.message = message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
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

    @JSONField(serialize = false)
    @Override
    public BaseProtoType getBaseType() {
        return BaseProtoType.BASE_HEART_BEAT;
    }

    public Map<String, List<? extends LongIdInfoIF>> getAllCfg() {
        return allCfg;
    }

    public void setAllCfg(Map<String, List<? extends LongIdInfoIF>> allCfg) {
        this.allCfg = allCfg;
    }
}
