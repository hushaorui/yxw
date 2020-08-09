package com.hsr.yxw.player.service.impl;

import com.hsr.yxw.common.PageBean;
import com.hsr.yxw.exception.ServiceException;
import com.hsr.yxw.mapper.PlayerMapper;
import com.hsr.yxw.player.pojo.Player;
import com.hsr.yxw.player.common.PlayerQueryVo;
import com.hsr.yxw.player.service.PlayerService;
import com.hsr.yxw.util.YxwStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerMapper playerMapper;

    @Override
    public Player login(String username, String password) throws ServiceException {
        if (username == null || password == null || username.trim().length() == 0) {
            return null;
        }
        long now = System.currentTimeMillis();
        username = username.trim();
        Player player = playerMapper.selectByUsername(username);
        if (player != null) {
            // 验证密码，就用明文密码了，不费劲加密了
            if (password.equals(player.getPassword())) {
                // 密码正确
                // 更新最后一次登录时间
                Long lastLoginTime = player.getLastLoginTime();
                player.setLastLoginTime(now);
                // 更新下最后登录时间
                playerMapper.update(player);
                // 回显上一次登录时间使用
                player.setLastLoginTime(lastLoginTime);
            } else {
                // 密码错误
                return null;
            }
        }
        return player;
    }

    @Override
    public Player getPlayerByUsername(String username) throws ServiceException {
        return playerMapper.selectByUsername(username);
    }

    @Override
    public long count(PlayerQueryVo vo) throws ServiceException {
        return playerMapper.count(vo);
    }

    @Override
    public List<Player> getAllPlayers() throws ServiceException {
        return playerMapper.selectAll();
    }

    @Override
    public PageBean<Player> getPlayerPageBean(Integer pageNum, Integer pageSize, PlayerQueryVo vo) throws ServiceException {
        int count = playerMapper.count(vo);
        PageBean<Player> pageBean = new PageBean<Player>(count, pageSize, pageNum);
        if (vo == null) {
            vo = new PlayerQueryVo();
        }
        vo.setFirstResult(pageBean.getFirstResult());
        vo.setMaxResult(pageBean.getPageSize());
        List<Player> players = playerMapper.selectByVo(vo);
        pageBean.setOtherPage();
        pageBean.setPageList(players);
        return pageBean;
    }

    @Override
    public void deletePlayerById(Long id) throws ServiceException {
        if (id == 1) {
            throw new ServiceException("ID为1的用户禁止删除！");
        }
        playerMapper.deleteById(id);
    }

    @Override
    public void deletePlayers(String ids) throws ServiceException {
        ArrayList<Long> idList = YxwStringUtils.idStringToList(ids);
        if (idList.contains(1L)) {
            throw new ServiceException("ID为1的用户禁止删除！");
        }
        playerMapper.delete(idList);
    }

    @Override
    public Player getPlayerById(Long id) throws ServiceException {
        return playerMapper.selectById(id);
    }

    @Override
    public void register(Player player) throws ServiceException {
        // 调用此方法前必须已经校验了 用户名和密码的格式了
        Player existPlayer = playerMapper.selectByUsername(player.getUsername());
        if (existPlayer != null) {
            throw new ServiceException("该用户名已经存在！");
        }
        player.setId(null);
        player.setCreateTime(System.currentTimeMillis());
        if (player.getAdmin() == null) {
            player.setAdmin(false);
        }
        playerMapper.insert(player);
    }

    @Override
    public void updatePlayer(Player player) throws ServiceException {
        Player existIdPlayer = playerMapper.selectById(player.getId());
        if (existIdPlayer == null) {
            throw new ServiceException("该用户不存在，id：" + player.getId());
        }
        if (!existIdPlayer.getUsername().equals(player.getUsername())) {
            // 用户名有做修改
            if (existIdPlayer.getId() == 1) {
                throw new ServiceException("Id为1的用户禁止修改用户名！");
            }
            Player existNamePlayer = playerMapper.selectByUsername(player.getUsername());
            if (existNamePlayer != null) {
                // 用户名已经被使用了
                throw new ServiceException("该用户名已存在：" + player.getUsername());
            }
        }
        // 目前只可修改这两个值
        existIdPlayer.setUsername(player.getUsername());
        existIdPlayer.setPassword(player.getPassword());
        if (player.getAdmin() == null) {
            existIdPlayer.setAdmin(false);
        } else {
            existIdPlayer.setAdmin(player.getAdmin());
        }
        playerMapper.update(existIdPlayer);
    }
}
