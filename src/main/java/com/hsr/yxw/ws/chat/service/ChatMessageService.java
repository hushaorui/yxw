package com.hsr.yxw.ws.chat.service;

import com.hsr.yxw.common.PageBean;
import com.hsr.yxw.mapper.ChatMessageMapper;
import com.hsr.yxw.util.YxwStringUtils;
import com.hsr.yxw.ws.chat.common.ChatMessageType;
import com.hsr.yxw.ws.chat.pojo.ChatMessage;
import com.hsr.yxw.ws.chat.common.ChatMessageQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatMessageService {

    private ChatMessageMapper chatMessageMapper;

    @Autowired
    public ChatMessageService(ChatMessageMapper chatMessageMapper) {
        this.chatMessageMapper = chatMessageMapper;
    }

    public ChatMessage save(ChatMessageType messageType, long senderId, String senderName, Long sendTime, Long receiverId, String receiverName, String content) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setSenderId(senderId);
        chatMessage.setSenderName(senderName);
        if (sendTime == null || sendTime <= 0) {
            sendTime = System.currentTimeMillis();
        }
        chatMessage.setSendTime(sendTime);
        if (messageType == null) {
            messageType = ChatMessageType.PUBLIC;
        }
        chatMessage.setMessageType(messageType);
        chatMessage.setReceiverId(receiverId);
        chatMessage.setReceiverName(receiverName);
        chatMessage.setContent(content);
        chatMessageMapper.insert(chatMessage);
        return chatMessage;
    }

    public PageBean<ChatMessage> getChatMessagePageBean(Integer pageNum, Integer pageSize, ChatMessageQueryVo vo) {
        int count = chatMessageMapper.count(vo);
        PageBean<ChatMessage> pageBean = new PageBean<ChatMessage>(count, pageSize, pageNum);
        if (vo == null) {
            vo = new ChatMessageQueryVo();
        }
        vo.setFirstResult(pageBean.getFirstResult());
        vo.setMaxResult(pageBean.getPageSize());
        List<ChatMessage> chatMessages = chatMessageMapper.selectByVo(vo);
        pageBean.setOtherPage();
        pageBean.setPageList(chatMessages);
        return pageBean;
    }

    /**
     * 查询最后几条聊天记录
     * @param lastSize
     * @param vo
     * @return
     */
    public List<ChatMessage> getLastChatMessage(Integer lastSize, ChatMessageQueryVo vo) {
        int totalCount = chatMessageMapper.count(vo);
        int firstResult;
        if (lastSize >= totalCount) {
            firstResult = 0;
            lastSize = totalCount;
        } else {
            firstResult = totalCount - lastSize;
        }
        if (vo == null) {
            vo = new ChatMessageQueryVo();
        }
        vo.setFirstResult(firstResult);
        vo.setMaxResult(lastSize);
        return chatMessageMapper.selectByVo(vo);
    }

    public void deleteChatMessages(String ids) {
        ArrayList<Long> idList = YxwStringUtils.idStringToList(ids);
        chatMessageMapper.delete(idList);
    }
    public void deleteChatMessage(Long id) {
        chatMessageMapper.deleteById(id);
    }

    public String selectContentById(long id) {
        return chatMessageMapper.selectContentById(id);
    }
}
