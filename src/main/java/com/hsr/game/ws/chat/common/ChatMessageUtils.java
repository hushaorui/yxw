package com.hsr.game.ws.chat.common;

import com.hsr.game.ws.chat.pojo.PublicChatMessage;
import com.hsr.game.account.pojo.Account;

public abstract class ChatMessageUtils {

    /**
     * 创建公共聊天信息对象
     * @param sender 发送者
     * @param content 内容
     * @return 对象
     */
    public static PublicChatMessage createPublicChatMessage(Account sender, String content) {
        PublicChatMessage publicChatMessage = new PublicChatMessage();
        publicChatMessage.setContent(content);
        publicChatMessage.setSenderId(sender.getId());
        publicChatMessage.setSenderName(sender.getUsername());
        publicChatMessage.setSendTime(System.currentTimeMillis());
        publicChatMessage.setMessageType(ChatMessageType.PUBLIC);
        return publicChatMessage;
    }

    /**
     * 创建系统公告信息对象
     * @param sender 发送者
     * @param content 内容
     * @return 对象
     */
    public static PublicChatMessage createSystemChatMessage(Account sender, String content) {
        PublicChatMessage publicChatMessage = new PublicChatMessage();
        publicChatMessage.setContent(content);
        publicChatMessage.setSenderId(sender.getId());
        publicChatMessage.setSenderName(sender.getUsername());
        publicChatMessage.setSendTime(System.currentTimeMillis());
        publicChatMessage.setMessageType(ChatMessageType.SYSTEM);
        return publicChatMessage;
    }

    /**
     * 校验聊天内容
     * @param content 聊天内容
     * @return 校验不通过返回null，通过，返回转义后的内容
     */
    public static String checkContent(String content) {
        if (content == null || "".equals(content.trim())) {
            // 为空或只含有空白字符
            return null;
        }
        if (content.length() > 200) {
            return null;
        }
        // TODO 这里可能需要将聊天内容进行转义，暂时只转义 < >
        content = content.replaceAll("<", "&lt;");
        content = content.replaceAll(">", "&gt;");
        return content;
    }

}
