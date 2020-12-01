package com.hsr.yxw.account.common;

import com.alibaba.druid.util.StringUtils;
import com.hsr.yxw.common.CommonResult;
import com.hsr.yxw.common.WebConstants;

public abstract class AccountUtil {

    /**
     * 校验用户名和密码的格式
     * @param username
     * @param password
     * @return 返回null，则表示校验成功
     */
    public static CommonResult checkUsernameAndPassword(String username, String password) {
        if (StringUtils.isEmpty(username)) {
            return CommonResult.error("用户名不可为空！");
        }
        if (StringUtils.isEmpty(password)) {
            return CommonResult.error("密码不可为空！");
        }
        if (! username.matches(WebConstants.CHINESE_ENGLISH_NUMBER_REGEX)) {
            return CommonResult.error("用户名只能含有中文、数字、大小写字母！");
        }
        if (! password.matches(WebConstants.CHINESE_ENGLISH_NUMBER_REGEX)) {
            return CommonResult.error("密码只能含有中文、数字、大小写字母！");
        }
        if (username.length() > 36) {
            return CommonResult.error("用户名长度不可大于36！");
        }
        if (password.length() > 36) {
            return CommonResult.error("密码长度不可大于36！");
        }
        return null;
    }
}