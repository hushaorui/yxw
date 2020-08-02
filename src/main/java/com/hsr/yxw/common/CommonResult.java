package com.hsr.yxw.common;

public class CommonResult {
    private static final CommonResult systemError = new CommonResult(WebConstants.ERROR, "系统错误，请检查日志！");
    private static final CommonResult success = new CommonResult(WebConstants.SUCCESS, "");
    private String result;
    private String message;

    public CommonResult(String result, String message) {
        this.result = result;
        this.message = message;
    }

    public static CommonResult success(String message) {
        return new CommonResult(WebConstants.SUCCESS, message);
    }
    public static CommonResult success() {
        return success;
    }
    public static CommonResult error(String message) {
        return new CommonResult(WebConstants.ERROR, message);
    }

    public static CommonResult systemError() {
        return systemError;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
