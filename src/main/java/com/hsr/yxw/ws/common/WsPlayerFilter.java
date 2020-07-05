package com.hsr.yxw.ws.common;

/**
 * 过滤玩家
 */
public interface WsPlayerFilter {

    boolean doFilter(WsPlayer wsPlayer);
}
