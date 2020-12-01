package com.hsr.yxw.ws.common;

/**
 * 过滤玩家
 */
public interface WsAccountFilter {

    boolean doFilter(WsAccount wsAccount);
}