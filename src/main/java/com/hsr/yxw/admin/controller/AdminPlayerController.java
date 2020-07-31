package com.hsr.yxw.admin.controller;

import com.alibaba.druid.util.StringUtils;
import com.hsr.yxw.common.CommonResult;
import com.hsr.yxw.common.PageBean;
import com.hsr.yxw.exception.ServiceException;
import com.hsr.yxw.player.pojo.Player;
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
        model.addAttribute("pageSizes", PageBean.pageSizes);
        return "admin/player-list";
    }
    @RequestMapping(value = "admin/player-delete")
    @ResponseBody
    public CommonResult deletePlayer(String ids) throws Exception {
        if (StringUtils.isEmpty(ids)) {
            return CommonResult.danger("ID不可为空！");
        }
        try {
            playerService.deletePlayers(ids);
            return CommonResult.success("删除成功！");
        } catch (ServiceException e) {
            return CommonResult.danger(e.getMessage());
        }
    }
}
