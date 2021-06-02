package com.hsr.game.common;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @param <POJO> 基本类
 * @param <VO> 基本查询类
 */
public interface BaseMapper<POJO, VO> {
    // 创建表
    void createTable();
    // 删除表
    void dropTable();
    // 清空表
    void deleteAll();
    // 根据id删除多个
    void delete(@Param("ids") List<Long> ids);
    // 根据id删除一个
    void deleteById(Long id);
    // 根据查询对象查询符合条件的数量
    int count(VO vo);
    // 无条件查询所有数据
    List<POJO> selectAll();
    // 根据id查询
    POJO selectById(long id);
    // 根据查询对象查询
    List<POJO> selectByVo(VO vo);
    // 插入一条数据
    void insert(POJO pojo);
    // 更新对象
    void update(POJO pojo);
    // 更新对象中的非null属性
    void updateForNotNull(POJO pojo);
}
