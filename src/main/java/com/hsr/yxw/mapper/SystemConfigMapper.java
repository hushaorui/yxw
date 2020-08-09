package com.hsr.yxw.mapper;

import com.hsr.yxw.common.BaseMapper;
import com.hsr.yxw.sysconfig.pojo.SystemConfig;
import com.hsr.yxw.sysconfig.common.SystemConfigQueryVo;
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
