package com.hsr.yxw.player.service.impl;

import com.hsr.yxw.common.PageBean;
import com.hsr.yxw.exception.ServiceException;
import com.hsr.yxw.mapper.PlayerMapper;
import com.hsr.yxw.player.pojo.Player;
import com.hsr.yxw.player.vo.PlayerQueryVo;
import com.hsr.yxw.player.service.PlayerService;
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
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }

    @Override
    public Player getPlayerByUsername(String username) throws ServiceException {
        try {
            return playerMapper.selectByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }

    @Override
    public long count() throws ServiceException {
        try {
            return playerMapper.count();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Player> getAllPlayers() throws ServiceException {
        try {
            return playerMapper.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }

    @Override
    public PageBean<Player> getPlayerPageBean(Integer pageNum, Integer pageSize, PlayerQueryVo vo) throws ServiceException {
        try {
            Integer count = playerMapper.count();
            if (count == 0) {
                return null;
            }
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
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }
    @Override
    public void deletePlayerById(Long id) throws ServiceException {
        try {
            if (id == 1) {
                throw new ServiceException("ID为1的用户禁止删除！");
            }
            playerMapper.deleteById(id);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deletePlayers(String ids) throws ServiceException {
        try {
            String[] idArray = ids.split("\\s*,\\s*");
            ArrayList<Long> idList = new ArrayList<>(idArray.length);
            for (String idString : idArray) {
                try {
                    if ("1".equals(idString)) {
                        throw new ServiceException("ID为1的用户禁止删除！");
                    }
                    idList.add(Long.parseLong(idString));
                } catch (NumberFormatException ignore) {}
            }
            playerMapper.delete(idList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }

    @Override
    public Player getPlayerById(Long id) throws ServiceException {
        try {
            return playerMapper.selectById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }

    @Override
    public void register(Player player) throws ServiceException {
        // 调用此方法前必须已经校验了 用户名和密码的格式了
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }
}
