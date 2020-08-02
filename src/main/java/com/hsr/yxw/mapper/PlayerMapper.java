package com.hsr.yxw.mapper;

import com.hsr.yxw.player.pojo.Player;
import com.hsr.yxw.player.vo.PlayerQueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface PlayerMapper {
    void createTable();

    void dropTable();

    Integer count(PlayerQueryVo vo);

    List<Player> selectAll();

    Player selectById(long id);

    Player selectByUsername(String username);

    List<Player> selectByVo(PlayerQueryVo vo);

    void insert(Player player);

    void deleteById(long id);

    void delete(@Param("ids")List<Long> ids);

    void update(Player player);

    void updateForNotNull(Player player);
}
