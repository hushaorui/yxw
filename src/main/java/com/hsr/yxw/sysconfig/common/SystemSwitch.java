package com.hsr.yxw.sysconfig.common;

/**
 * 系统的开关
 */
public interface SystemSwitch {
    /** 开放注册，开放后可在登录界面跳转注册界面并注册，否则只能在admin添加新的用户 */
    String OPEN_REGISTER_SWITCH = "openRegisterSwitch";
}