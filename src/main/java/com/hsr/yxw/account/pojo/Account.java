package com.hsr.yxw.account.pojo;

import java.io.Serializable;

public class Account implements Serializable {
    private static final long serialVersionUID = 0L;
    private Long id;
    // 用户名，唯一
    private String username;
    // 密码
    private String password;
    // 是否有root权限
    private Boolean admin;
    // 创建时间
    private Long createTime;
    // 最后一次登录时间
    private Long lastLoginTime;

    public Account() {}

    public Account(String username, String password, Boolean admin, Long createTime) {
        this.username = username;
        this.password = password;
        this.admin = admin;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}