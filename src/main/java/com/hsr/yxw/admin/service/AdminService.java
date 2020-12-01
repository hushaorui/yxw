package com.hsr.yxw.admin.service;

import com.hsr.yxw.exception.ServiceException;
import com.hsr.yxw.account.pojo.Account;
import com.hsr.yxw.sysconfig.pojo.SystemConfig;

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