package com.hsr.yxw.sysconfig.service;

import com.hsr.yxw.common.PageBean;
import com.hsr.yxw.exception.ServiceException;
import com.hsr.yxw.sysconfig.pojo.SystemConfig;
import com.hsr.yxw.sysconfig.vo.SystemConfigQueryVo;

public interface SystemConfigService {
    PageBean<SystemConfig> getSystemConfigPageBean(Integer pageNum, Integer pageSize, SystemConfigQueryVo vo) throws ServiceException;

    void deleteSystemConfigs(String ids) throws ServiceException;

    Byte getByteValue(String configKey) throws ServiceException;
    byte getByteValueNotNull(String configKey) throws ServiceException;
    Short getShortValue(String configKey) throws ServiceException;
    short getShortValueNotNull(String configKey) throws ServiceException;
    Integer getIntegerValue(String configKey) throws ServiceException;
    int getIntValueNotNull(String configKey) throws ServiceException;
    Long getLongValue(String configKey) throws ServiceException;
    long getLongValueNotNull(String configKey) throws ServiceException;
    Boolean getBoolValue(String configKey) throws ServiceException;
    boolean getBoolValueNotNull(String configKey) throws ServiceException;
    String getStringValue(String configKey) throws ServiceException;
    Float getFloatValue(String configKey) throws ServiceException;
    float getFloatValueNotNull(String configKey) throws ServiceException;
    Double getDoubleValue(String configKey) throws ServiceException;
    double getDoubleValueNotNull(String configKey) throws ServiceException;
}
