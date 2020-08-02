package com.hsr.yxw.admin.controller;

import com.alibaba.druid.util.StringUtils;
import com.hsr.yxw.common.CommonResult;
import com.hsr.yxw.common.PageBean;
import com.hsr.yxw.exception.ServiceException;
import com.hsr.yxw.player.pojo.Player;
import com.hsr.yxw.player.util.PlayerUtil;
import com.hsr.yxw.player.vo.PlayerQueryVo;
import com.hsr.yxw.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;

@Controller
public class AdminPlayerController {

    @Autowired
    private PlayerService playerService;

    @RequestMapping(value="admin/player-list")
    public String playerList(Integer pageNum, Integer pageSize, PlayerQueryVo vo, Model model) throws Exception {
        PageBean<Player> pageBean = playerService.getPlayerPageBean(pageNum, pageSize, vo);
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("playerQueryVo", vo);
        model.addAttribute("pageSizes", PageBean.pageSizes);
        return "admin/player-list";
    }
    @RequestMapping(value = "admin/player-delete")
    @ResponseBody
    public CommonResult deletePlayer(String ids) {
        if (StringUtils.isEmpty(ids)) {
            return CommonResult.error("ID不可为空！");
        }
        try {
            playerService.deletePlayers(ids);
            return CommonResult.success("删除成功！");
        } catch (ServiceException e) {
            return CommonResult.error(e.getMessage());
        }
    }
    @RequestMapping(value = "admin/player-update")
    @ResponseBody
    public CommonResult updatePlayer(Player player) throws Exception {
        try {
            if (player == null || player.getId() == null) {
                return CommonResult.error("用户id不可为空！");
            }
            CommonResult commonResult = PlayerUtil.checkUsernameAndPassword(player.getUsername(), player.getPassword());
            if (commonResult != null) {
                return commonResult;
            }
            playerService.updatePlayer(player);
            return CommonResult.success("修改成功！");
        } catch (ServiceException e) {
            return CommonResult.error(e.getMessage());
        }
    }
}
