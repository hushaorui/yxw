package com.hsr.yxw.util.test;

import com.hsr.yxw.util.IpUtils;
import org.junit.jupiter.api.Test;

public class TestIpUtils {

    @Test
    public void test_getLocalIp() {
        System.out.println(IpUtils.getLocalIp());
    }
}
