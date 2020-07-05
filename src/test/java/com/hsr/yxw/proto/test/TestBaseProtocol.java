package com.hsr.yxw.proto.test;

import com.hsr.yxw.ws.common.BaseProtocol;
import org.junit.jupiter.api.Test;

public class TestBaseProtocol {

    @Test
    public void test_toJsonString() {
        BaseProtocol commonProtocol = new BaseProtocol("typeValue", "messageValue");
        String jsonString = commonProtocol.toJsonString();
        System.out.println(jsonString);
        BaseProtocol p2 = BaseProtocol.parseStringToProtoCol(jsonString, BaseProtocol.class);
        System.out.println(p2.getType());
        System.out.println(p2.getMessage());
    }
}
