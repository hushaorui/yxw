package com.hsr.yxw.account.common;

import com.hsr.yxw.common.CommonVo;
import com.hsr.yxw.account.pojo.Account;

public class AccountQueryVo extends CommonVo {
    private Account account;
    private Long createTimeStart;
    private Long createTimeEnd;
    private Long lastLoginTimeStart;
    private Long lastLoginTimeEnd;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Long getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(Long createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public Long getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Long createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public Long getLastLoginTimeStart() {
        return lastLoginTimeStart;
    }

    public void setLastLoginTimeStart(Long lastLoginTimeStart) {
        this.lastLoginTimeStart = lastLoginTimeStart;
    }

    public Long getLastLoginTimeEnd() {
        return lastLoginTimeEnd;
    }

    public void setLastLoginTimeEnd(Long lastLoginTimeEnd) {
        this.lastLoginTimeEnd = lastLoginTimeEnd;
    }
}