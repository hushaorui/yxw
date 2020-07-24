package com.hsr.yxw.mapper;

import com.hsr.yxw.pojo.Player;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface PlayerMapper {
    @Update("create table player(" +
                "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)primary key," +
                "username varchar(255) unique," +
                "admin boolean," +
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

    @Insert("insert into player(username,password,admin,createTime) values(#{o.username}, #{o.password}, #{o.admin}, #{o.createTime})")
    void insert(@Param("o")Player obj) throws Exception;

    @Delete("delete from player where id = #{value}")
    void deleteById(long id) throws Exception;

    @Delete("<script>delete from player where id in <foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>#{item}</foreach></script>")
    void delete(@Param("ids")List<Long> ids) throws Exception;

    @Update("update player set username = #{o.username}, password = #{o.password}, lastLoginTime = #{o.lastLoginTime,jdbcType=BIGINT}, admin = #{o.admin}" +
            "where id = #{o.id}")
    void update(@Param("o")Player obj) throws Exception;
}
