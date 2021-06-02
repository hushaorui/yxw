package com.hsr.game.ws.cmd.service;

import com.hsr.game.common.WebConstants;
import com.hsr.game.ws.cmd.pojo.CmdActionGameResult;
import com.hsr.game.ws.common.WsAccount;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

@Service
public class CmdActionGameService {
    private static final Log log = LogFactory.getLog(CmdActionGameService.class);

    /**
     * 处理命令行
     * @param wsAccount 账号
     * @param cmdString 命令
     * @return 执行结果
     */
    public CmdActionGameResult handleCmdMsg(WsAccount wsAccount, String cmdString) {
        if (cmdString == null) {
            return CmdActionGameResult.DEFAULT;
        }
        cmdString = cmdString.trim();
        if (cmdString.length() <= 0) {
            return CmdActionGameResult.DEFAULT;
        }
        // 将命令按分号切割
        String[] cmdArray = cmdString.split(WebConstants.SEMICOLON_SPLIT_REGEX);
        // 遍历检查这些命令
        for (String command : cmdArray) {
            // TODO
        }
        return null;
    }
}
