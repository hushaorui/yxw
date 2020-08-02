package com.hsr.yxw.mapper;

import com.hsr.yxw.sysconfig.pojo.SystemConfig;
import com.hsr.yxw.sysconfig.vo.SystemConfigQueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface SystemConfigMapper {

    void createTable();

    void dropTable();

    Integer count(SystemConfigQueryVo vo);

    SystemConfig selectById(Long id);

    SystemConfig selectByConfigKey(String configKey);

    List<SystemConfig> selectByClassify(String classify);

    List<SystemConfig> selectByVo(SystemConfigQueryVo vo);

    void insert(SystemConfig systemConfig);

    void delete(@Param("ids")List<Long> ids);

    void deleteById(Long id);

    void update(SystemConfig systemConfig);

    void updateForNotNull(SystemConfig systemConfig);

    List<String> selectAllClassify();

    List<String> selectAllValueType();
}
