package com.hsr.yxw.util.test;

import com.hsr.yxw.common.WebConstants;
import com.hsr.yxw.util.IpUtils;
import org.junit.jupiter.api.Test;

public class TestIpUtils {

    @Test
    public void test_getLocalIp() {
        System.out.println(IpUtils.getLocalIp());
    }

    @Test
    public void test_pattern() {
        String regex = WebConstants.CHINESE_ENGLISH_NUMBER_REGEX;
        String str = "";
        String str1 = "abc";
        String str2 = "中国";
        String str3 = "123456";
        String str4 = "abc中国";
        String str5 = "abc123456";
        String str6 = "中国123456";

        //不满足的
        String str7 = "<";
        String str8 = "123>";
        String str9 = ";;d";
        String str10 = "jel,klk";
        System.out.println("字符串：" + str + " 是否符合：" + str.matches(regex));
        System.out.println("字符串：" + str1 + " 是否符合：" + str1.matches(regex));
        System.out.println("字符串：" + str2 + " 是否符合：" + str2.matches(regex));
        System.out.println("字符串：" + str3 + " 是否符合：" + str3.matches(regex));
        System.out.println("字符串：" + str4 + " 是否符合：" + str4.matches(regex));
        System.out.println("字符串：" + str5 + " 是否符合：" + str5.matches(regex));
        System.out.println("字符串：" + str6 + " 是否符合：" + str6.matches(regex));
        System.out.println("字符串：" + str7 + " 是否符合：" + str7.matches(regex));
        System.out.println("字符串：" + str8 + " 是否符合：" + str8.matches(regex));
        System.out.println("字符串：" + str9 + " 是否符合：" + str9.matches(regex));
        System.out.println("字符串：" + str10 + " 是否符合：" + str10.matches(regex));
    }
}
