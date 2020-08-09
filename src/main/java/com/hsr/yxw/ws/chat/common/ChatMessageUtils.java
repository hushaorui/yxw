package com.hsr.yxw.ws.chat.common;

import com.hsr.yxw.player.pojo.Player;
import com.hsr.yxw.ws.chat.pojo.ChatMessage;

public abstract class ChatMessageUtils {

    /**
     * 创建公共聊天信息对象
     * @param sender 发送者
     * @param content 内容
     * @return 对象
     */
    public static ChatMessage createPublicChatMessage(Player sender, String content) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent(content);
        chatMessage.setSenderId(sender.getId());
        chatMessage.setSenderName(sender.getUsername());
        chatMessage.setSendTime(System.currentTimeMillis());
        chatMessage.setMessageType(ChatMessageType.PUBLIC);
        return chatMessage;
    }

    /**
     * 创建私聊信息对象
     * @param sender 发送者
     * @param receiver 接收者
     * @param content 内容
     * @return 对象
     */
    public static ChatMessage createPrivateChatMessage(Player sender, Player receiver, String content) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent(content);
        chatMessage.setSenderId(sender.getId());
        chatMessage.setSenderName(sender.getUsername());
        chatMessage.setSendTime(System.currentTimeMillis());
        chatMessage.setMessageType(ChatMessageType.PRIVATE);
        chatMessage.setReceiverId(receiver.getId());
        chatMessage.setReceiverName(receiver.getUsername());
        return chatMessage;
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
