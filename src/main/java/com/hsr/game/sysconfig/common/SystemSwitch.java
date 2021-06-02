package com.hsr.game.sysconfig.common;

/**
 * 系统的开关
 */
public interface SystemSwitch {
    /** 开放注册，开放后可在登录界面跳转注册界面并注册，否则只能在admin添加新的用户 */
    String OPEN_REGISTER_SWITCH = "openRegisterSwitch";
    /** 开放公共聊天栏输入GM指令 */
    String OPEN_PUBLIC_CHAT_GM_SWITCH = "openPublicChatGMSwitch";
}
