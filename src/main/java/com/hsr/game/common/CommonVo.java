package com.hsr.game.common;

import com.alibaba.fastjson.annotation.JSONField;

public class CommonVo {
    private Integer firstResult;
    private Integer maxResult;

    public Integer getFirstResult() {
        return firstResult;
    }

    public void setFirstResult(Integer firstResult) {
        this.firstResult = firstResult;
    }

    public Integer getMaxResult() {
        return maxResult;
    }

    public void setMaxResult(Integer maxResult) {
        this.maxResult = maxResult;
    }

    @JSONField(serialize = false)
    public void setPagingResult(Integer firstResult, Integer maxResult) {
        this.firstResult = firstResult;
        this.maxResult = maxResult;
    }
}
