package com.hsr.yxw.ws.chat.service;

import com.hsr.yxw.common.PageBean;
import com.hsr.yxw.mapper.ChatMessageMapper;
import com.hsr.yxw.util.YxwStringUtils;
import com.hsr.yxw.ws.chat.common.ChatMessageType;
import com.hsr.yxw.ws.chat.pojo.PublicChatMessage;
import com.hsr.yxw.ws.chat.common.PublicChatMessageQueryVo;
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

    public PublicChatMessage save(ChatMessageType messageType, long senderId, String senderName, Long sendTime, Long receiverId, String receiverName, String content) {
        PublicChatMessage publicChatMessage = new PublicChatMessage();
        publicChatMessage.setSenderId(senderId);
        publicChatMessage.setSenderName(senderName);
        if (sendTime == null || sendTime <= 0) {
            sendTime = System.currentTimeMillis();
        }
        publicChatMessage.setSendTime(sendTime);
        if (messageType == null) {
            messageType = ChatMessageType.PUBLIC;
        }
        publicChatMessage.setMessageType(messageType);
        publicChatMessage.setContent(content);
        chatMessageMapper.insert(publicChatMessage);
        return publicChatMessage;
    }

    public PageBean<PublicChatMessage> getChatMessagePageBean(Integer pageNum, Integer pageSize, PublicChatMessageQueryVo vo) {
        int count = chatMessageMapper.count(vo);
        PageBean<PublicChatMessage> pageBean = new PageBean<PublicChatMessage>(count, pageSize, pageNum);
        if (vo == null) {
            vo = new PublicChatMessageQueryVo();
        }
        vo.setFirstResult(pageBean.getFirstResult());
        vo.setMaxResult(pageBean.getPageSize());
        List<PublicChatMessage> publicChatMessages = chatMessageMapper.selectByVo(vo);
        pageBean.setOtherPage();
        pageBean.setPageList(publicChatMessages);
        return pageBean;
    }

    /**
     * 查询最后几条聊天记录
     * @param lastSize
     * @param vo
     * @return
     */
    public List<PublicChatMessage> getLastChatMessage(Integer lastSize, PublicChatMessageQueryVo vo) {
        int totalCount = chatMessageMapper.count(vo);
        int firstResult;
        if (lastSize >= totalCount) {
            firstResult = 0;
            lastSize = totalCount;
        } else {
            firstResult = totalCount - lastSize;
        }
        if (vo == null) {
            vo = new PublicChatMessageQueryVo();
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
