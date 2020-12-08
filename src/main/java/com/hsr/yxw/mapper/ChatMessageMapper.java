package com.hsr.yxw.mapper;

import com.hsr.yxw.common.BaseMapper;
import com.hsr.yxw.ws.chat.pojo.PublicChatMessage;
import com.hsr.yxw.ws.chat.common.PublicChatMessageQueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ChatMessageMapper extends BaseMapper<PublicChatMessage, PublicChatMessageQueryVo> {

    String selectContentById(long id);

    /**
     * selectByVo不包含content，此方法包含
     */
    List<PublicChatMessage> selectByVoWithContent(PublicChatMessageQueryVo vo);
}
