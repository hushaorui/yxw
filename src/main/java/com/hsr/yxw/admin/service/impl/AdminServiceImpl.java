package com.hsr.yxw.admin.service.impl;

import com.hsr.yxw.admin.service.AdminService;
import com.hsr.yxw.card.common.YxwCardType;
import com.hsr.yxw.card.pojo.YxwCard;
import com.hsr.yxw.common.WebConstants;
import com.hsr.yxw.exception.ServiceException;
import com.hsr.yxw.mapper.ChatMessageMapper;
import com.hsr.yxw.mapper.AccountMapper;
import com.hsr.yxw.mapper.SystemConfigMapper;
import com.hsr.yxw.account.pojo.Account;
import com.hsr.yxw.mapper.YxwCardMapper;
import com.hsr.yxw.sysconfig.common.SystemSwitch;
import com.hsr.yxw.sysconfig.pojo.SystemConfig;
import com.hsr.yxw.ws.chat.common.ChatMessageUtils;
import com.hsr.yxw.ws.chat.pojo.PublicChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private AccountMapper accountMapper;
    private SystemConfigMapper systemConfigMapper;
    private ChatMessageMapper chatMessageMapper;
    private YxwCardMapper yxwCardMapper;

    @Autowired
    public AdminServiceImpl(AccountMapper accountMapper, SystemConfigMapper systemConfigMapper, ChatMessageMapper chatMessageMapper,
                            YxwCardMapper yxwCardMapper) {
        this.accountMapper = accountMapper;
        this.systemConfigMapper = systemConfigMapper;
        this.chatMessageMapper = chatMessageMapper;
        this.yxwCardMapper = yxwCardMapper;
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
                    yxwCardMapper.dropTable();
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
            try {
                yxwCardMapper.createTable();
            } catch (Exception ignore) {}

            initSystemConfigTable();
            initAccountTable();
            initChatMessageTable();

            initYxwCardTable();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }

    private void initYxwCardTable() {
        YxwCard yxwCard = new YxwCard();
        yxwCard.setCardName("斧王");
        yxwCard.setCardType(YxwCardType.MONSTER);
        yxwCard.setDescription("攻击力高的战士");
        yxwCard.setLocalImgUrl("");
        yxwCard.setRemoteImgUrl("https://baike.baidu.com/pic/%E5%88%80%E6%96%A7%E6%88%98%E5%A3%AB/3730906/0/91ae68c69804f6089c163d5b#aid=0&pic=91ae68c69804f6089c163d5b");
        yxwCardMapper.insert(yxwCard);
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
