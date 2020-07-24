package com.hsr.yxw.service;

import com.hsr.yxw.exception.ServiceException;
import com.hsr.yxw.pojo.Player;

import java.util.List;

public interface PlayerService {
    Player login(String username, String password) throws ServiceException;

    Player getPlayerByUsername(String username) throws ServiceException;

    long count() throws ServiceException;

    List<Player> getAllPlayers() throws ServiceException;
}
