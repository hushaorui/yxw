package com.hsr.yxw.mapper;

import com.hsr.yxw.common.BaseMapper;
import com.hsr.yxw.ws.chat.pojo.ChatMessage;
import com.hsr.yxw.ws.chat.common.ChatMessageQueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface ChatMessageMapper extends BaseMapper<ChatMessage, ChatMessageQueryVo> {

    String selectContentById(long id);
}
