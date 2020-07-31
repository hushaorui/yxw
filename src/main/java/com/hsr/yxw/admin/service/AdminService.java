package com.hsr.yxw.admin.service;

import com.hsr.yxw.exception.ServiceException;

public interface AdminService {

    /**
     * 初始化数据库
     * @throws ServiceException
     */
    void initDB(boolean reset) throws ServiceException;
}
