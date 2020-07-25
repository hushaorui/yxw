package com.hsr.yxw.mapper;

import com.hsr.yxw.pojo.Player;
import com.hsr.yxw.pojo.vo.PlayerQueryVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface PlayerMapper {
    void createTable() throws Exception;

    void dropTable() throws Exception;

    Integer count() throws Exception;

    List<Player> selectAll() throws Exception;

    Player selectById(long id) throws Exception;

    Player selectByUsername(String username) throws Exception;

    List<Player> selectByVo(PlayerQueryVo vo) throws Exception;

    void insert(Player player) throws Exception;

    void deleteById(long id) throws Exception;

    void delete(@Param("ids")List<Long> ids) throws Exception;

    void update(Player player) throws Exception;
}
