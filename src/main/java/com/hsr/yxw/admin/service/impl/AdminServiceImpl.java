package com.hsr.yxw.admin.service.impl;

import com.hsr.yxw.admin.service.AdminService;
import com.hsr.yxw.common.WebConstants;
import com.hsr.yxw.exception.ServiceException;
import com.hsr.yxw.mapper.PlayerMapper;
import com.hsr.yxw.mapper.SystemConfigMapper;
import com.hsr.yxw.player.pojo.Player;
import com.hsr.yxw.sysconfig.common.SystemSwitch;
import com.hsr.yxw.sysconfig.pojo.SystemConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private PlayerMapper playerMapper;
    @Autowired
    private SystemConfigMapper systemConfigMapper;
    @Override
    public void initDB(boolean reset) throws ServiceException {
        try {
            // 目前没有找到 判断表存在才删除表的sql语句，只能每个都try catch一下
            if (reset) {
                // 删除表格
                try {
                    systemConfigMapper.dropTable();
                } catch (Exception ignore) {}
                try {
                    playerMapper.dropTable();
                } catch (Exception ignore) {}
            }
            // 创建表格
            try {
                systemConfigMapper.createTable();
            } catch (Exception ignore) {}
            try {
                playerMapper.createTable();
            } catch (Exception ignore) {}

            initSystemConfigTable();
            initPlayerTable();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }

    @Override
    public List<SystemConfig> getDefaultSystemConfigList() {
        List<SystemConfig> list = new ArrayList<>();
        SystemConfig openRegisterSwitchConfig = new SystemConfig(SystemSwitch.OPEN_REGISTER_SWITCH, WebConstants.SYSTEM, true, Boolean.class);
        list.add(openRegisterSwitchConfig);
        return list;
    }

    private void initSystemConfigTable() throws Exception {
        int count = systemConfigMapper.count(null);
        if (count == 0) {
            // 放入初始默认系统配置
            for (SystemConfig config : getDefaultSystemConfigList()) {
                systemConfigMapper.insert(config);
            }

            // 测试数据
            SystemConfig systemConfig;
            for (int i = 1; i < 30; i++) {
                systemConfig = new SystemConfig("testKey" + i, "test", "testValue" + i, String.class);
                systemConfigMapper.insert(systemConfig);
            }
        }
    }
    private void initPlayerTable() throws Exception {
        int playerCount = playerMapper.count(null);
        if (playerCount == 0) {
            Player admin = new Player("admin", "admin", true, System.currentTimeMillis());
            playerMapper.insert(admin);
            Player player1 = new Player("player1", "player1", false, System.currentTimeMillis());
            Player player2 = new Player("player2", "player2", false, System.currentTimeMillis());
            playerMapper.insert(player1);
            playerMapper.insert(player2);

            // 测试数据
            for (int i = 1; i < 50; i++) {
                Player player = new Player("test"+i, "test"+i, false, System.currentTimeMillis());
                playerMapper.insert(player);
            }
        }
    }
}
