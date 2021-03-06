package com.hsr.game.sysconfig.service;

import com.hsr.game.common.PageBean;
import com.hsr.game.exception.ServiceException;
import com.hsr.game.sysconfig.common.SystemConfigQueryVo;
import com.hsr.game.sysconfig.pojo.SystemConfig;

import java.util.List;

public interface SystemConfigService {
    PageBean<SystemConfig> getSystemConfigPageBean(Integer pageNum, Integer pageSize, SystemConfigQueryVo vo) throws ServiceException;

    void deleteSystemConfigs(String ids) throws ServiceException;

    Byte getByteValue(String configKey) throws ServiceException;
    byte getByteValueNotNull(String configKey);
    Short getShortValue(String configKey) throws ServiceException;
    short getShortValueNotNull(String configKey);
    Integer getIntegerValue(String configKey) throws ServiceException;
    int getIntValueNotNull(String configKey);
    Long getLongValue(String configKey) throws ServiceException;
    long getLongValueNotNull(String configKey);
    Boolean getBoolValue(String configKey) throws ServiceException;
    boolean getBoolValueNotNull(String configKey);
    String getStringValue(String configKey) throws ServiceException;
    String getStringValueNotNull(String configKey);
    Float getFloatValue(String configKey) throws ServiceException;
    float getFloatValueNotNull(String configKey);
    Double getDoubleValue(String configKey) throws ServiceException;
    double getDoubleValueNotNull(String configKey);

    void updateSystemConfig(SystemConfig systemConfig) throws ServiceException;

    List<String> selectAllClassify() throws ServiceException;
    List<String> selectAllValueType() throws ServiceException;

    void addSystemConfig(SystemConfig systemConfig) throws ServiceException;
}
