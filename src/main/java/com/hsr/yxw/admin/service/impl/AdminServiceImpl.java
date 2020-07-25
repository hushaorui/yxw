package com.hsr.yxw.admin.service.impl;

import com.hsr.yxw.admin.service.AdminService;
import com.hsr.yxw.exception.ServiceException;
import com.hsr.yxw.mapper.PlayerMapper;
import com.hsr.yxw.pojo.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private PlayerMapper playerMapper;
    @Override
    public void resetDB() throws ServiceException {
        try {
            // 目前没有找到 判断表存在才删除表的sql语句，只能每个都try catch一下
            try {
                playerMapper.dropTable();
            } catch (Exception ignore) {}
            playerMapper.create();
            Player admin = new Player("admin", "admin", true, System.currentTimeMillis());
            playerMapper.insert(admin);
            Player player1 = new Player("player1", "player1", false, System.currentTimeMillis());
            Player player2 = new Player("player2", "player2", false, System.currentTimeMillis());
            playerMapper.insert(player1);
            playerMapper.insert(player2);

            for (int i = 1; i < 50; i++) {
                Player player = new Player("test"+i, "test"+i, false, System.currentTimeMillis());
                playerMapper.insert(player);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }
}
