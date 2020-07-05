package com.hsr.yxw.config;

import com.hsr.yxw.common.YxwConfig;
import com.hsr.yxw.pojo.Player;
import com.hsr.yxw.util.MD5Utils;

import java.util.HashMap;

public class YxwMainConfig implements YxwConfig {
    public HashMap<String, Player> accounts;

    @Override
    public void init() {
        accounts = new HashMap<>();
        Player player = new Player();
        player.setUsername("1");
        player.setPassword(MD5Utils.md5("1"));
        player.setOnline(false);
        player.setCreateTime(System.currentTimeMillis());
        accounts.put(player.getUsername(), player);
    }
}
