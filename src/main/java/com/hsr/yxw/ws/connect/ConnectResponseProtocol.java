package com.hsr.yxw.ws.connect;

import com.hsr.yxw.ws.common.BaseProtocol;

public class ConnectResponseProtocol extends BaseProtocol {
    /** 连接成功 */
    public static final String CONNECT_SUCCESS = "CONNECT_SUCCESS";
    /** 连接失败 */
    public static final String CONNECT_FAILED = "CONNECT_FAILED";
    /** 不符合格式 */
    public static final String NOT_FORMAT = "NOT_FORMAT";
    /** 未知的协议 */
    public static final String UNKNOWN_PROTO = "UNKNOWN_PROTO";
    /** 服务器信息 */
    public static final String SERVER_INFO = "SERVER_INFO";

    public ConnectResponseProtocol() {}

    public ConnectResponseProtocol(String result, String message) {
        super(result, message);
    }
}
