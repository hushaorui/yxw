package com.hsr.yxw.sysconfig.service.impl;

import com.hsr.yxw.common.PageBean;
import com.hsr.yxw.exception.ServiceException;
import com.hsr.yxw.mapper.SystemConfigMapper;
import com.hsr.yxw.sysconfig.pojo.SystemConfig;
import com.hsr.yxw.sysconfig.service.SystemConfigService;
import com.hsr.yxw.sysconfig.vo.SystemConfigQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SystemConfigServiceImpl implements SystemConfigService {
    @Autowired
    private SystemConfigMapper systemConfigMapper;

    @Override
    public PageBean<SystemConfig> getSystemConfigPageBean(Integer pageNum, Integer pageSize, SystemConfigQueryVo vo) throws ServiceException {
        Integer count = systemConfigMapper.count(vo);
        PageBean<SystemConfig> pageBean = new PageBean<>(count, pageSize, pageNum);
        if (vo == null) {
            vo = new SystemConfigQueryVo();
        }
        vo.setFirstResult(pageBean.getFirstResult());
        vo.setMaxResult(pageBean.getPageSize());
        List<SystemConfig> list = systemConfigMapper.selectByVo(vo);
        pageBean.setPageList(list);
        pageBean.setOtherPage();
        return pageBean;
    }

    @Override
    public void deleteSystemConfigs(String ids) throws ServiceException {
        String[] idArray = ids.split("\\s*,\\s*");
        ArrayList<Long> idList = new ArrayList<>(idArray.length);
        for (String idString : idArray) {
            try {
                idList.add(Long.parseLong(idString));
            } catch (NumberFormatException ignore) {
            }
        }
        systemConfigMapper.delete(idList);
    }

    @Override
    public Byte getByteValue(String configKey) throws ServiceException {
        SystemConfig systemConfig = systemConfigMapper.selectByConfigKey(configKey);
        if (systemConfig == null) {
            return null;
        }
        if (Byte.class.getSimpleName().equals(systemConfig.getValueType()) || byte.class.getSimpleName().equals(systemConfig.getValueType())) {
            return Byte.parseByte(systemConfig.getConfigValue());
        }
        return null;
    }

    @Override
    public byte getByteValueNotNull(String configKey) {
        Byte value = null;
        try {
            value = getByteValue(configKey);
        } catch (Exception ignore) {
        }
        if (value != null) {
            return value;
        }
        return 0;
    }

    @Override
    public Short getShortValue(String configKey) throws ServiceException {
        SystemConfig systemConfig = systemConfigMapper.selectByConfigKey(configKey);
        if (systemConfig == null) {
            return null;
        }
        if (Short.class.getSimpleName().equals(systemConfig.getValueType()) || short.class.getSimpleName().equals(systemConfig.getValueType())) {
            return Short.parseShort(systemConfig.getConfigValue());
        }
        return null;
    }

    @Override
    public short getShortValueNotNull(String configKey) {
        Short value = null;
        try {
            value = getShortValue(configKey);
        } catch (Exception ignore) {
        }
        if (value != null) {
            return value;
        }
        return 0;
    }

    @Override
    public Integer getIntegerValue(String configKey) throws ServiceException {
        SystemConfig systemConfig = systemConfigMapper.selectByConfigKey(configKey);
        if (systemConfig == null) {
            return null;
        }
        if (Integer.class.getSimpleName().equals(systemConfig.getValueType()) || int.class.getSimpleName().equals(systemConfig.getValueType())) {
            return Integer.parseInt(systemConfig.getConfigValue());
        }
        return null;
    }

    @Override
    public int getIntValueNotNull(String configKey) {
        Integer value = null;
        try {
            value = getIntegerValue(configKey);
        } catch (Exception ignore) {
        }
        if (value != null) {
            return value;
        }
        return 0;
    }

    @Override
    public Long getLongValue(String configKey) throws ServiceException {
        SystemConfig systemConfig = systemConfigMapper.selectByConfigKey(configKey);
        if (systemConfig == null) {
            return null;
        }
        if (Long.class.getSimpleName().equals(systemConfig.getValueType()) || long.class.getSimpleName().equals(systemConfig.getValueType())) {
            return Long.parseLong(systemConfig.getConfigValue());
        }
        return null;
    }

    @Override
    public long getLongValueNotNull(String configKey) {
        Long value = null;
        try {
            value = getLongValue(configKey);
        } catch (Exception ignore) {
        }
        if (value != null) {
            return value;
        }
        return 0L;
    }

    @Override
    public Boolean getBoolValue(String configKey) throws ServiceException {
        SystemConfig systemConfig = systemConfigMapper.selectByConfigKey(configKey);
        if (systemConfig == null) {
            return null;
        }
        if (Boolean.class.getSimpleName().equals(systemConfig.getValueType()) || boolean.class.getSimpleName().equals(systemConfig.getValueType())) {
            return Boolean.parseBoolean(systemConfig.getConfigValue());
        }
        return null;
    }

    @Override
    public boolean getBoolValueNotNull(String configKey) {
        Boolean value = null;
        try {
            value = getBoolValue(configKey);
        } catch (Exception ignore) {
        }
        if (value != null) {
            return value;
        }
        return false;
    }

    @Override
    public String getStringValue(String configKey) throws ServiceException {
        SystemConfig systemConfig = systemConfigMapper.selectByConfigKey(configKey);
        if (systemConfig != null) {
            return systemConfig.getConfigValue();
        }
        return null;
    }

    @Override
    public String getStringValueNotNull(String configKey) {
        SystemConfig systemConfig = null;
        try {
            systemConfig = systemConfigMapper.selectByConfigKey(configKey);
        } catch (Exception ignore) {
        }
        if (systemConfig != null && systemConfig.getConfigValue() != null) {
            return systemConfig.getConfigValue();
        } else {
            return "";
        }
    }

    @Override
    public Float getFloatValue(String configKey) throws ServiceException {
        SystemConfig systemConfig = systemConfigMapper.selectByConfigKey(configKey);
        if (systemConfig == null) {
            return null;
        }
        if (Float.class.getSimpleName().equals(systemConfig.getValueType()) || float.class.getSimpleName().equals(systemConfig.getValueType())) {
            return Float.parseFloat(systemConfig.getConfigValue());
        }
        return null;
    }

    @Override
    public float getFloatValueNotNull(String configKey) {
        Float value = null;
        try {
            value = getFloatValue(configKey);
        } catch (Exception ignore) {
        }
        if (value != null) {
            return value;
        } else {
            return 0.0f;
        }
    }

    @Override
    public Double getDoubleValue(String configKey) throws ServiceException {
        SystemConfig systemConfig = systemConfigMapper.selectByConfigKey(configKey);
        if (systemConfig == null) {
            return null;
        }
        if (Double.class.getSimpleName().equals(systemConfig.getValueType()) || double.class.getSimpleName().equals(systemConfig.getValueType())) {
            return Double.parseDouble(systemConfig.getConfigValue());
        }
        return null;
    }

    @Override
    public double getDoubleValueNotNull(String configKey) {
        Double value = null;
        try {
            value = getDoubleValue(configKey);
        } catch (Exception ignore) {
        }
        if (value != null) {
            return value;
        } else {
            return 0.0;
        }
    }

    @Override
    public void updateSystemConfig(SystemConfig systemConfig) throws ServiceException {
        SystemConfig existConfig = systemConfigMapper.selectById(systemConfig.getId());
        if (existConfig == null) {
            throw new ServiceException("该id的配置已不存在：" + systemConfig);
        }
        if (String.class.getSimpleName().equals(systemConfig.getValueType())) {
            // 忽略
        } else if (Byte.class.getSimpleName().equals(systemConfig.getValueType())) {
            try {
                Byte.parseByte(systemConfig.getConfigValue());
            } catch (NumberFormatException e) {
                throw new ServiceException("值：" + systemConfig.getConfigValue() + "不符合类型：" + systemConfig.getValueType());
            }
        } else if (Short.class.getSimpleName().equals(systemConfig.getValueType())) {
            try {
                Short.parseShort(systemConfig.getConfigValue());
            } catch (NumberFormatException e) {
                throw new ServiceException("值：" + systemConfig.getConfigValue() + "不符合类型：" + systemConfig.getValueType());
            }
        } else if (Integer.class.getSimpleName().equals(systemConfig.getValueType())) {
            try {
                Integer.parseInt(systemConfig.getConfigValue());
            } catch (NumberFormatException e) {
                throw new ServiceException("值：" + systemConfig.getConfigValue() + "不符合类型：" + systemConfig.getValueType());
            }
        } else if (Long.class.getSimpleName().equals(systemConfig.getValueType())) {
            try {
                Long.parseLong(systemConfig.getConfigValue());
            } catch (NumberFormatException e) {
                throw new ServiceException("值：" + systemConfig.getConfigValue() + "不符合类型：" + systemConfig.getValueType());
            }
        } else if (Boolean.class.getSimpleName().equals(systemConfig.getValueType())) {
            try {
                Boolean.parseBoolean(systemConfig.getConfigValue());
            } catch (NumberFormatException e) {
                throw new ServiceException("值：" + systemConfig.getConfigValue() + "不符合类型：" + systemConfig.getValueType());
            }
        } else if (Float.class.getSimpleName().equals(systemConfig.getValueType())) {
            try {
                Float.parseFloat(systemConfig.getConfigValue());
            } catch (NumberFormatException e) {
                throw new ServiceException("值：" + systemConfig.getConfigValue() + "不符合类型：" + systemConfig.getValueType());
            }
        } else if (Double.class.getSimpleName().equals(systemConfig.getValueType())) {
            try {
                Double.parseDouble(systemConfig.getConfigValue());
            } catch (NumberFormatException e) {
                throw new ServiceException("值：" + systemConfig.getConfigValue() + "不符合类型：" + systemConfig.getValueType());
            }
        } else {
            throw new ServiceException("未知的值类型：" + systemConfig.getValueType());
        }
        existConfig.setClassify(systemConfig.getClassify());
        existConfig.setConfigValue(systemConfig.getConfigValue());
        existConfig.setValueType(systemConfig.getValueType());
        existConfig.setLastUpdateTime(System.currentTimeMillis());
        systemConfigMapper.update(existConfig);
    }

    @Override
    public List<String> selectAllClassify() throws ServiceException {
        return systemConfigMapper.selectAllClassify();
    }

    @Override
    public List<String> selectAllValueType() throws ServiceException {
        return systemConfigMapper.selectAllValueType();
    }
}
