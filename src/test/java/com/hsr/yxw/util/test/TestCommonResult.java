package com.hsr.yxw.util.test;

import com.alibaba.fastjson.JSONArray;
import com.hsr.game.common.CommonResult;
import org.junit.Test;

public class TestCommonResult {

    @Test
    public void test_CommonResult() {
        CommonResult commonResult = CommonResult.addSuccess();
        String jsonString = JSONArray.toJSONString(commonResult);
        System.out.println(jsonString);
    }
}
