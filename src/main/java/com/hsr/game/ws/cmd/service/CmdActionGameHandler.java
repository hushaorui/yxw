package com.hsr.game.ws.cmd.service;

import com.hsr.game.common.CommonResult;
import com.hsr.game.ws.cmd.pojo.CmdActionGameResult;
import com.hsr.game.ws.common.IHandler;
import com.hsr.game.ws.common.IResponseProtocol;
import com.hsr.game.ws.common.WsAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.Session;

@Component
public class CmdActionGameHandler implements IHandler<CmdActionGameRequestProtocol, CmdActionGameResponseProtocol> {
    private CmdActionGameService cmdActionGameService;

    @Autowired
    public CmdActionGameHandler(CmdActionGameService cmdActionGameService) {
        this.cmdActionGameService = cmdActionGameService;
    }
    @Override
    public IResponseProtocol handle(WsAccount wsAccount, Session session, CmdActionGameRequestProtocol request) {
        String reqType = request.getReqType();
        String cmdString = request.getCmdString();
        if (CmdActionGameRequestProtocol.SEND_CMD_MESSAGE.equals(reqType)) {
            // 接收到命令行信息
            CmdActionGameResult result = cmdActionGameService.handleCmdMsg(wsAccount, cmdString);
            // TODO
        }
        return null;
    }

    @Override
    public CmdActionGameRequestProtocol parseRequest(String message) {
        return null;
    }
}
