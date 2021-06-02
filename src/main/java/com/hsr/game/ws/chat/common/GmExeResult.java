package com.hsr.game.ws.chat.common;

public class GmExeResult {
    public static final GmExeResult OK = new GmExeResult(true, "OK");
    public static final GmExeResult FAIL = new GmExeResult(false, "FAIL");

    private boolean result;
    private String message;

    public GmExeResult(boolean result, String message) {
        this.result = result;
        this.message = message;
    }

    public static GmExeResult failure(String message) {
        return new GmExeResult(false, message);
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
