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
        try{
            Integer count = systemConfigMapper.count();
            if (count == 0) {
                return null;
            }
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
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteSystemConfigs(String ids) throws ServiceException {
        try {
            String[] idArray = ids.split("\\s*,\\s*");
            ArrayList<Long> idList = new ArrayList<>(idArray.length);
            for (String idString : idArray) {
                try {
                    idList.add(Long.parseLong(idString));
                } catch (NumberFormatException ignore) {}
            }
            systemConfigMapper.delete(idList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }

    @Override
    public Byte getByteValue(String configKey) throws ServiceException {
        try {
            SystemConfig systemConfig = systemConfigMapper.selectByConfigKey(configKey);
            if (systemConfig == null) {
                return null;
            }
            if (Byte.class.getName().equals(systemConfig.getValueType()) || byte.class.getName().equals(systemConfig.getValueType())) {
                return Byte.parseByte(systemConfig.getConfigValue());
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        return null;
    }

    @Override
    public byte getByteValueNotNull(String configKey) throws ServiceException {
        return 0;
    }

    @Override
    public Short getShortValue(String configKey) throws ServiceException {
        return null;
    }

    @Override
    public short getShortValueNotNull(String configKey) throws ServiceException {
        return 0;
    }

    @Override
    public Integer getIntegerValue(String configKey) throws ServiceException {
        return null;
    }

    @Override
    public int getIntValueNotNull(String configKey) throws ServiceException {
        return 0;
    }

    @Override
    public Long getLongValue(String configKey) throws ServiceException {
        return null;
    }

    @Override
    public long getLongValueNotNull(String configKey) throws ServiceException {
        return 0;
    }

    @Override
    public Boolean getBoolValue(String configKey) throws ServiceException {
        return null;
    }

    @Override
    public boolean getBoolValueNotNull(String configKey) throws ServiceException {
        return false;
    }

    @Override
    public String getStringValue(String configKey) throws ServiceException {
        return null;
    }

    @Override
    public Float getFloatValue(String configKey) throws ServiceException {
        return null;
    }

    @Override
    public float getFloatValueNotNull(String configKey) throws ServiceException {
        return 0;
    }

    @Override
    public Double getDoubleValue(String configKey) throws ServiceException {
        return null;
    }

    @Override
    public double getDoubleValueNotNull(String configKey) throws ServiceException {
        return 0;
    }
}
