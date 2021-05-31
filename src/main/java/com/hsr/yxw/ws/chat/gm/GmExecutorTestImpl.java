package com.hsr.yxw.ws.chat.gm;

import com.hsr.yxw.common.CommandLine;
import com.hsr.yxw.ws.chat.common.GmExeResult;
import com.hsr.yxw.ws.chat.common.GmExecutor;

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
