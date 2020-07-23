package com.hsr.yxw.service.impl;

import com.hsr.yxw.exception.ServiceException;
import com.hsr.yxw.mapper.PlayerMapper;
import com.hsr.yxw.pojo.Player;
import com.hsr.yxw.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerMapper playerMapper;
    @Override
    public Player login(String username, String password) throws ServiceException {
        if (username == null || password == null || username.trim().length() == 0) {
            return null;
        }
        long now = System.currentTimeMillis();
        username = username.trim();
        try {
            Player player = playerMapper.selectByUsername(username);
            if (player != null) {
                // 验证密码，就用明文密码了，不费劲加密了
                if (password.equals(player.getPassword())) {
                    // 密码正确
                    long lastLoginTime = player.getLastLoginTime();
                    player.setLastLoginTime(now);
                    // 更新下最后登录时间
                    playerMapper.update(player);
                } else {
                    // 密码错误
                    return null;
                }
            }
            return player;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }

    @Override
    public Player getPlayerByUsername(String username) throws ServiceException {
        try {
            return playerMapper.selectByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }

    @Override
    public long count() throws ServiceException {
        try {
            return playerMapper.count();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }
}
