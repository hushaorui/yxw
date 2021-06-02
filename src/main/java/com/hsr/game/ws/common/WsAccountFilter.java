package com.hsr.game.ws.common;

/**
 * 过滤玩家
 */
public interface WsAccountFilter {

    boolean doFilter(WsAccount wsAccount);
}
