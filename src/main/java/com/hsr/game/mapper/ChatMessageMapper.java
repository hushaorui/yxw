package com.hsr.game.mapper;

import com.hsr.game.common.BaseMapper;
import com.hsr.game.ws.chat.common.PublicChatMessageQueryVo;
import com.hsr.game.ws.chat.pojo.PublicChatMessage;
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
