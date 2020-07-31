package com.hsr.yxw.player.vo;

import com.hsr.yxw.common.CommonVo;
import com.hsr.yxw.player.pojo.Player;

public class PlayerQueryVo extends CommonVo {
    private Player player;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
