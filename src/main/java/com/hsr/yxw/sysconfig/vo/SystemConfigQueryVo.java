package com.hsr.yxw.sysconfig.vo;

import com.hsr.yxw.common.CommonVo;
import com.hsr.yxw.sysconfig.pojo.SystemConfig;

public class SystemConfigQueryVo extends CommonVo {
    private SystemConfig systemConfig;

    public SystemConfig getSystemConfig() {
        return systemConfig;
    }

    public void setSystemConfig(SystemConfig systemConfig) {
        this.systemConfig = systemConfig;
    }
}
