package com.hsr.yxw.admin.controller;

import com.hsr.yxw.common.CommonResult;
import com.hsr.yxw.common.PageBean;
import com.hsr.yxw.exception.ServiceException;
import com.hsr.yxw.pojo.Player;
import com.hsr.yxw.pojo.vo.PlayerQueryVo;
import com.hsr.yxw.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;

@Controller
public class AdminPlayerController {
    private static LinkedHashMap<Integer, String> pageSizes = new LinkedHashMap<>();
    static {
        for (int i = 10; i <= 50; i += 5) {
            pageSizes.put(i, String.valueOf(i));
        }
        pageSizes.put(Integer.MAX_VALUE, "最大");
    }

    @Autowired
    private PlayerService playerService;

    @RequestMapping(value="admin/player-list")
    public String playerList(Integer pageNum, Integer pageSize, PlayerQueryVo vo, Model model) throws Exception {
        PageBean<Player> pageBean = playerService.getPlayerPageBean(pageNum, pageSize, vo);
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("pageSizes", pageSizes);
        return "admin/player-list";
    }
    @RequestMapping(value = "admin/player-delete")
    @ResponseBody
    public CommonResult deletePlayer(Long id) throws Exception {
        if (id == null) {
            return CommonResult.danger("ID不可为空！");
        }
        try {
            playerService.deletePlayerById(id);
            return CommonResult.success("删除成功！");
        } catch (ServiceException e) {
            return CommonResult.danger(e.getMessage());
        }
    }
}
