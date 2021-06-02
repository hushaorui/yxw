package com.hsr.game.admin.service;

import com.hsr.game.exception.ServiceException;
import com.hsr.game.sysconfig.pojo.SystemConfig;

import java.util.List;

public interface AdminService {

    /**
     * 初始化数据库
     * @throws ServiceException
     */
    void initDB(boolean reset) throws ServiceException;

    /**
     * 获得初始默认的系统配置列表，防止在删除后无法恢复
     * @return
     * @throws ServiceException
     */
    List<SystemConfig> getDefaultSystemConfigList();
}
