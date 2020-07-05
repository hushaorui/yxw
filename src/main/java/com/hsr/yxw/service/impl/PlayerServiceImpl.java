package com.hsr.yxw.service.impl;

import com.hsr.yxw.config.YxwMainConfig;
import com.hsr.yxw.exception.ServiceException;
import com.hsr.yxw.pojo.Player;
import com.hsr.yxw.service.PlayerService;
import com.hsr.yxw.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private YxwMainConfig yxwMainConfig;
    @Override
    public Player login(String username, String password) throws ServiceException {
        if (username == null || password == null || username.trim().length() == 0) {
            return null;
        }
        long now = System.currentTimeMillis();
        username = username.trim();
        Player player = yxwMainConfig.accounts.get(username);
        if (player == null) {
            player = new Player();
            player.setUsername(username);
            player.setPassword(MD5Utils.md5(password));
            player.setCreateTime(now);
            player.setOnline(true);
            // 设置最后一次登录时间
            player.setLastLoginTime(now);
            yxwMainConfig.accounts.put(player.getUsername(), player);
            return player;
        } else if (MD5Utils.md5(password).equals(player.getPassword())) {
            // 密码正确
            player.setLastLoginTime(now);
            // 更新配置
            // TODO
            player.setOnline(true);
            player.setPassword(null);
            return player;
        }
        return null;
    }

    @Override
    public Player getPlayerByUsername(String username) {
        return yxwMainConfig.accounts.get(username);
    }
}
