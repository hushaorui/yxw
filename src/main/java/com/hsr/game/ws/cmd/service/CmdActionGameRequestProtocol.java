package com.hsr.game.ws.cmd.service;

import com.hsr.game.ws.common.BaseProtoType;
import com.hsr.game.ws.common.IRequestProtocol;

public class CmdActionGameRequestProtocol extends IRequestProtocol {
    public static final String SEND_CMD_MESSAGE = "send_cmd_message"; // 发送命令行信息
    private String cmdString;
    public String getCmdString() {
        return cmdString;
    }
    public void setCmdString(String cmdString) {
        this.cmdString = cmdString;
    }

    @Override
    public BaseProtoType getBaseType() {
        return BaseProtoType.BASE_CMD_ACTION_GAME;
    }
}
