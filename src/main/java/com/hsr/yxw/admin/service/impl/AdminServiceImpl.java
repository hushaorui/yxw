package com.hsr.yxw.admin.service.impl;

import com.hsr.yxw.admin.service.AdminService;
import com.hsr.yxw.common.WebConstants;
import com.hsr.yxw.exception.ServiceException;
import com.hsr.yxw.mapper.ChatMessageMapper;
import com.hsr.yxw.mapper.AccountMapper;
import com.hsr.yxw.mapper.SystemConfigMapper;
import com.hsr.yxw.account.pojo.Account;
import com.hsr.yxw.sysconfig.common.SystemSwitch;
import com.hsr.yxw.sysconfig.pojo.SystemConfig;
import com.hsr.yxw.ws.chat.common.ChatMessageUtils;
import com.hsr.yxw.ws.chat.pojo.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private SystemConfigMapper systemConfigMapper;
    @Autowired
    private ChatMessageMapper chatMessageMapper;
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
            }
            // 创建表格
            try {
                systemConfigMapper.createTable();
            } catch (Exception ignore) {}
            try {
                accountMapper.createTable();
            } catch (Exception ignore) {}
            try {
                chatMessageMapper.createTable();
            } catch (Exception ignore) {}

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
        Account receiver = new Account();
        receiver.setId(901L);
        receiver.setUsername("TestReceiveMessage");
        ChatMessage chatMessage;
        for (int i = 1; i <= 20; i++) {
            chatMessage = ChatMessageUtils.createPublicChatMessage(sender, "公共聊天室聊天内容" + i);
            chatMessageMapper.insert(chatMessage);
            chatMessage = ChatMessageUtils.createPrivateChatMessage(sender, receiver, "私聊聊天内容" + i);
            chatMessageMapper.insert(chatMessage);
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