package com.hsr.yxw.controller;

import com.hsr.yxw.common.CommonResult;
import com.hsr.yxw.exception.ServiceException;
import com.hsr.yxw.pojo.Player;
import com.hsr.yxw.run.SpringBootUtil;
import com.hsr.yxw.service.PlayerService;
import com.hsr.yxw.util.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.spring5.context.SpringContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {
    @Autowired
    private PlayerService playerService;

    @RequestMapping(value = "index")
    public String index() {
        return "index";
    }
    @RequestMapping(value = "login")
    public String login() {
        return "login";
    }
    @RequestMapping(value = "toLogin", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult toLogin(String username, String password, Boolean remember, HttpSession session) throws ServiceException {
        Player player = playerService.login(username, password);
        if (player == null) {
            return CommonResult.danger();
        }
        session.setAttribute("sessionPlayer", player);
        return CommonResult.success();
    }

    @RequestMapping(value = "logout")
    public String logout(HttpSession session) {
        session.removeAttribute("sessionPlayer");
        return "redirect:login";
    }
    @RequestMapping(value="getWsUrlPrefix")
    @ResponseBody
    public String getWsUrlPrefix() {
        String prefix = "ws://" + IpUtils.getLocalIp() + ":" + SpringBootUtil.getServerPort() + "/ws/";
        return prefix;
    }
}
