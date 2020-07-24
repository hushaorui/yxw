package com.hsr.yxw.admin.controller;

import com.hsr.yxw.pojo.Player;
import com.hsr.yxw.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AdminPlayerController {
    @Autowired
    private PlayerService playerService;

    @RequestMapping(value="admin/player-list")
    public String playerList(Model model) throws Exception {
        List<Player> players = playerService.getAllPlayers();
        model.addAttribute("players", players);
        return "admin/player-list";
    }
}
