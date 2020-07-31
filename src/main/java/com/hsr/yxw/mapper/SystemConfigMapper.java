package com.hsr.yxw.mapper;

import com.hsr.yxw.sysconfig.pojo.SystemConfig;
import com.hsr.yxw.sysconfig.vo.SystemConfigQueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface SystemConfigMapper {

    void createTable() throws Exception;

    void dropTable() throws Exception;

    Integer count() throws Exception;

    SystemConfig selectById(Long id) throws Exception;

    SystemConfig selectByConfigKey(String configKey) throws Exception;

    List<SystemConfig> selectByClassify(String classify) throws Exception;

    List<SystemConfig> selectByVo(SystemConfigQueryVo vo) throws Exception;

    void insert(SystemConfig systemConfig) throws Exception;
}
