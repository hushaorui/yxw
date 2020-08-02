package com.hsr.yxw.player.service;

import com.hsr.yxw.common.PageBean;
import com.hsr.yxw.exception.ServiceException;
import com.hsr.yxw.player.pojo.Player;
import com.hsr.yxw.player.vo.PlayerQueryVo;

import java.util.List;

public interface PlayerService {
    Player login(String username, String password) throws ServiceException;

    Player getPlayerByUsername(String username) throws ServiceException;

    long count(PlayerQueryVo vo) throws ServiceException;

    List<Player> getAllPlayers() throws ServiceException;

    PageBean<Player> getPlayerPageBean(Integer pageNum, Integer pageSize, PlayerQueryVo vo) throws ServiceException;

    void deletePlayerById(Long id) throws ServiceException;

    void deletePlayers(String ids) throws ServiceException;

    Player getPlayerById(Long id) throws ServiceException;

    void register(Player player) throws ServiceException;

    void updatePlayer(Player player) throws ServiceException;
}
