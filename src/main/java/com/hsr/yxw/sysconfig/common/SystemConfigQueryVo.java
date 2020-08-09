package com.hsr.yxw.sysconfig.common;

import com.hsr.yxw.common.CommonVo;
import com.hsr.yxw.sysconfig.pojo.SystemConfig;

public class SystemConfigQueryVo extends CommonVo {
    private SystemConfig systemConfig;

    private String configKeyLike;
    private String classifyLike;
    private String configValueLike;
    private String valueTypeLike;
    private Long lastUpdateTimeStart;
    private Long lastUpdateTimeEnd;

    public SystemConfig getSystemConfig() {
        return systemConfig;
    }

    public void setSystemConfig(SystemConfig systemConfig) {
        this.systemConfig = systemConfig;
    }

    public String getConfigKeyLike() {
        return configKeyLike;
    }

    public void setConfigKeyLike(String configKeyLike) {
        this.configKeyLike = configKeyLike;
    }

    public String getClassifyLike() {
        return classifyLike;
    }

    public void setClassifyLike(String classifyLike) {
        this.classifyLike = classifyLike;
    }

    public String getConfigValueLike() {
        return configValueLike;
    }

    public void setConfigValueLike(String configValueLike) {
        this.configValueLike = configValueLike;
    }

    public String getValueTypeLike() {
        return valueTypeLike;
    }

    public void setValueTypeLike(String valueTypeLike) {
        this.valueTypeLike = valueTypeLike;
    }

    public Long getLastUpdateTimeStart() {
        return lastUpdateTimeStart;
    }

    public void setLastUpdateTimeStart(Long lastUpdateTimeStart) {
        this.lastUpdateTimeStart = lastUpdateTimeStart;
    }

    public Long getLastUpdateTimeEnd() {
        return lastUpdateTimeEnd;
    }

    public void setLastUpdateTimeEnd(Long lastUpdateTimeEnd) {
        this.lastUpdateTimeEnd = lastUpdateTimeEnd;
    }
}
