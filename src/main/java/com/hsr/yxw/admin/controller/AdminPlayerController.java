package com.hsr.yxw.admin.controller;

import com.hsr.yxw.pojo.Player;
import com.hsr.yxw.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedHashMap;
import java.util.List;

@Controller
public class AdminPlayerController {
    private static LinkedHashMap<String, String> pageSizes = new LinkedHashMap<>();
    static {
        for (int i = 10; i <= 50; i += 5) {
            pageSizes.put(String.valueOf(i), String.valueOf(i));
        }
        pageSizes.put("", "最大");
    }

    @Autowired
    private PlayerService playerService;

    @RequestMapping(value="admin/player-list")
    public String playerList(Integer pageNum, Integer pageSize, Model model) throws Exception {
        List<Player> players = playerService.getAllPlayers();
        long totalCount = playerService.count();
        model.addAttribute("players", players);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pageSizes", pageSizes);
        return "admin/player-list";
    }
}
