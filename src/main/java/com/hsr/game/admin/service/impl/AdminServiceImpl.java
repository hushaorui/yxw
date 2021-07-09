package com.hsr.game.admin.service.impl;

import com.hsr.game.admin.service.AdminService;
import com.hsr.game.common.WebConstants;
import com.hsr.game.ws.chat.common.ChatMessageUtils;
import com.hsr.game.ws.chat.pojo.PublicChatMessage;
import com.hsr.game.account.pojo.Account;
import com.hsr.game.exception.ServiceException;
import com.hsr.game.mapper.AccountMapper;
import com.hsr.game.mapper.ChatMessageMapper;
import com.hsr.game.mapper.SystemConfigMapper;
import com.hsr.game.mapper.YxwGameDataItemMapper;
import com.hsr.game.sysconfig.common.SystemSwitch;
import com.hsr.game.sysconfig.pojo.SystemConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private AccountMapper accountMapper;
    private SystemConfigMapper systemConfigMapper;
    private ChatMessageMapper chatMessageMapper;
    private YxwGameDataItemMapper yxwGameDataItemMapper;

    @Autowired
    public AdminServiceImpl(AccountMapper accountMapper, SystemConfigMapper systemConfigMapper, ChatMessageMapper chatMessageMapper,
                            YxwGameDataItemMapper yxwGameDataItemMapper) {
        this.accountMapper = accountMapper;
        this.systemConfigMapper = systemConfigMapper;
        this.chatMessageMapper = chatMessageMapper;
        this.yxwGameDataItemMapper = yxwGameDataItemMapper;
    }

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
                    accountMapper.dropTable();
                } catch (Exception ignore) {}
                try {
                    chatMessageMapper.dropTable();
                } catch (Exception ignore) {}
                try {
                    yxwGameDataItemMapper.dropTable();
                } catch (Exception ignore) {}
            }
            String keyWord = "already exists";
            // 创建表格
            try {
                systemConfigMapper.createTable();
            } catch (Exception e) {
                if (! e.getMessage().contains(keyWord)) {
                    e.printStackTrace();
                }
            }
            try {
                accountMapper.createTable();
            } catch (Exception e) {
                if (! e.getMessage().contains(keyWord)) {
                    e.printStackTrace();
                }
            }
            try {
                chatMessageMapper.createTable();
            } catch (Exception e) {
                if (! e.getMessage().contains(keyWord)) {
                    e.printStackTrace();
                }
            }
            try {
                yxwGameDataItemMapper.createTable();
            } catch (Exception e) {
                if (! e.getMessage().contains(keyWord)) {
                    e.printStackTrace();
                }
            }

            initSystemConfigTable();
            initAccountTable();
            initChatMessageTable();

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }

    private void initChatMessageTable() {
        // 纯测试数据
        Account sender = new Account();
        sender.setId(900L);
        sender.setUsername("TestSendMessage");
        PublicChatMessage publicChatMessage;
        for (int i = 1; i <= 20; i++) {
            publicChatMessage = ChatMessageUtils.createPublicChatMessage(sender, "公共聊天室聊天内容" + i);
            chatMessageMapper.insert(publicChatMessage);
            publicChatMessage = ChatMessageUtils.createSystemChatMessage(sender, "系统公告内容" + i);
            chatMessageMapper.insert(publicChatMessage);
        }
    }

    /**
     * 获取系统默认的一些开关设置
     */
    @Override
    public List<SystemConfig> getDefaultSystemConfigList() {
        List<SystemConfig> list = new ArrayList<>();
        SystemConfig openRegisterSwitchConfig = new SystemConfig(SystemSwitch.OPEN_REGISTER_SWITCH, WebConstants.SYSTEM, true, Boolean.class);
        SystemConfig openPublicChatGMSwitchConfig = new SystemConfig(SystemSwitch.OPEN_PUBLIC_CHAT_GM_SWITCH, WebConstants.SYSTEM, true, Boolean.class);
        list.add(openRegisterSwitchConfig);
        list.add(openPublicChatGMSwitchConfig);
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
    private void initAccountTable() throws Exception {
        int accountCount = accountMapper.count(null);
        if (accountCount == 0) {
            Account admin = new Account("admin", "admin", true, System.currentTimeMillis());
            accountMapper.insert(admin);
            Account account1 = new Account("account1", "account1", false, System.currentTimeMillis());
            Account account2 = new Account("account2", "account2", false, System.currentTimeMillis());
            accountMapper.insert(account1);
            accountMapper.insert(account2);

            // 测试数据
            for (int i = 1; i < 50; i++) {
                Account account = new Account("test"+i, "test"+i, false, System.currentTimeMillis());
                accountMapper.insert(account);
            }
        }
    }
}
