package com.hsr.yxw.mapper;

import com.hsr.yxw.common.BaseMapper;
import com.hsr.yxw.player.pojo.Player;
import com.hsr.yxw.player.common.PlayerQueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface PlayerMapper extends BaseMapper<Player, PlayerQueryVo> {
    // 根据用户名查询
    Player selectByUsername(String username);
}
