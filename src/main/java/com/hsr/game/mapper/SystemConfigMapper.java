package com.hsr.game.mapper;

import com.hsr.game.common.BaseMapper;
import com.hsr.game.sysconfig.pojo.SystemConfig;
import com.hsr.game.sysconfig.common.SystemConfigQueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface SystemConfigMapper extends BaseMapper<SystemConfig, SystemConfigQueryVo> {

    SystemConfig selectByConfigKey(String configKey);

    List<SystemConfig> selectByClassify(String classify);

    List<String> selectAllClassify();

    List<String> selectAllValueType();
}
