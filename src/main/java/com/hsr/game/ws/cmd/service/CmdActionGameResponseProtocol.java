package com.hsr.game.ws.cmd.service;

import com.hsr.game.ws.common.BaseProtoType;
import com.hsr.game.ws.common.IResponseProtocol;

public class CmdActionGameResponseProtocol extends IResponseProtocol {

    @Override
    public BaseProtoType getBaseType() {
        return BaseProtoType.BASE_CMD_ACTION_GAME;
    }
}
