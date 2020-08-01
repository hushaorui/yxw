package com.hsr.yxw.common;

public class CommonResult {
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
        return success(null);
    }
    public static CommonResult error(String message) {
        return new CommonResult(WebConstants.ERROR, message);
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
