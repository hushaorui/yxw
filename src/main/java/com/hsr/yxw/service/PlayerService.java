package com.hsr.yxw.service;

import com.hsr.yxw.exception.ServiceException;
import com.hsr.yxw.pojo.Player;

public interface PlayerService {
    Player login(String username, String password) throws ServiceException;

    Player getPlayerByUsername(String username) throws ServiceException;

    long count() throws ServiceException;
}
