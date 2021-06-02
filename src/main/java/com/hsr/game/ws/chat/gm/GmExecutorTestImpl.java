package com.hsr.game.ws.chat.gm;

import com.hsr.game.common.CommandLine;
import com.hsr.game.ws.chat.common.GmExeResult;
import com.hsr.game.ws.chat.common.GmExecutor;

public class GmExecutorTestImpl extends GmExecutor {

    public GmExeResult test1() {
        System.out.println("test1.....");
        return GmExeResult.OK;
    }

    public GmExeResult test2(CommandLine commandLine) {
        System.out.println(commandLine.getOption("userId"));
        return GmExeResult.OK;
    }
}
