package com.hsr.game.ws.cmd.pojo;

public class CmdActionGameResult {
    public static final CmdActionGameResult DEFAULT = new CmdActionGameResult(true, "");

    public CmdActionGameResult(boolean result, String view) {
        this.result = result;
        this.view = view;
    }
    // 结果
    private boolean result;
    // 视图
    private String view;
    public boolean isResult() {
        return result;
    }
    public void setResult(boolean result) {
        this.result = result;
    }
    public String getView() {
        return view;
    }
    public void setView(String view) {
        this.view = view;
    }
}
