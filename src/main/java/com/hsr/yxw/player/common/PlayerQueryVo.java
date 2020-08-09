package com.hsr.yxw.player.common;

import com.hsr.yxw.common.CommonVo;
import com.hsr.yxw.player.pojo.Player;

public class PlayerQueryVo extends CommonVo {
    private Player player;
    private Long createTimeStart;
    private Long createTimeEnd;
    private Long lastLoginTimeStart;
    private Long lastLoginTimeEnd;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
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
