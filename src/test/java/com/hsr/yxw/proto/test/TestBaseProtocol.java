package com.hsr.yxw.proto.test;

import com.alibaba.fastjson.JSONArray;
import com.hsr.game.ws.common.BaseProtocol;
import com.hsr.game.ws.heartbeat.HeartBeatResponseProtocol;
import org.junit.jupiter.api.Test;

public class TestBaseProtocol {

    @Test
    public void test_toJsonString() {
        HeartBeatResponseProtocol response = HeartBeatResponseProtocol.connectSuccess();
        BaseProtocol baseProtocol = BaseProtocol.buildResponse(response);
        String message = baseProtocol.toJsonString();
        System.out.println("base: " + message);
        BaseProtocol baseProtocol2 = BaseProtocol.parse(message);
        if (baseProtocol2 != null) {
            System.out.println("base: " + baseProtocol2.toJsonString());
        } else {
            System.out.println("null");
        }
        String resMsg = response.toJsonString();
        System.out.println("resMsg: " + resMsg);
        HeartBeatResponseProtocol response2 = JSONArray.parseObject(resMsg, HeartBeatResponseProtocol.class);
        if (response2 != null) {
            System.out.println("resMsg: " + response2.toJsonString());
        } else {
            System.out.println("null");
        }
    }
}
