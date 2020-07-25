package com.hsr.yxw.admin.service;

import com.hsr.yxw.exception.ServiceException;

public interface AdminService {

    /**
     * 重置整个数据库
     * @throws ServiceException
     */
    void resetDB() throws ServiceException;
}
