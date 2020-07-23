package com.hsr.yxw.mapper;

import com.hsr.yxw.pojo.Player;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface PlayerMapper {
    @Update("create table player(" +
                "id bigint," +
                "username varchar(255) unique," +
                "password varchar(255)," +
                "createTime bigint," +
                "lastLoginTime bigint" +
            ")")
    void create() throws Exception;

    @Delete("drop table player")
    void dropTable() throws Exception;

    @Select("select count(1) from player")
    Integer count() throws Exception;

    @Select("select * from player")
    List<Player> selectAll() throws Exception;

    @Select("select * from player where id = #{value}")
    Player selectById(long id) throws Exception;

    @Select("select * from player where username = #{value}")
    Player selectByUsername(String username) throws Exception;

    @Select("select * from player order by id asc {limit #{limit} offset #{offset}}")
    List<Player> selectSeveral(@Param("limit") int limit, @Param("offset")int offset) throws Exception;

    @Insert("insert into player(id,username,password,createTime,lastLoginTime) values(#{o.id}, #{o.username}, #{o.password}, #{o.createTime}, #{lastLoginTime})")
    void insert(@Param("o")Player obj) throws Exception;

    @Delete("<script>delete from player where id in <foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>#{item}</foreach></script>")
    void delete(@Param("ids")List<String> ids) throws Exception;

    @Update("update player set username = #{o.username}, password = #{o.password}, lastLoginTime = #{o.lastLoginTime} where id = #{o.id}")
    void update(@Param("o")Player obj) throws Exception;
}
