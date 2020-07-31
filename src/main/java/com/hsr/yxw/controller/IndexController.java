package com.hsr.yxw.controller;

import com.alibaba.druid.util.StringUtils;
import com.hsr.yxw.common.CommonResult;
import com.hsr.yxw.common.WebConstants;
import com.hsr.yxw.exception.ServiceException;
import com.hsr.yxw.player.pojo.Player;
import com.hsr.yxw.run.SpringBootUtil;
import com.hsr.yxw.player.service.PlayerService;
import com.hsr.yxw.util.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    /**
     * 登录
     * @param username
     * @param password
     * @param remember
     * @param session
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "toLogin", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult toLogin(String username, String password, Boolean remember, HttpSession session) throws ServiceException {
        Player player = playerService.login(username, password);
        if (player == null) {
            return CommonResult.danger();
        }
        session.setAttribute("sessionPlayer", player);
        return CommonResult.success(player.getAdmin() ? "admin/admin-index" : "index");
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult register(String username, String password) throws Exception {
        try {
            if (StringUtils.isEmpty(username)) {
                return CommonResult.danger("用户名不可为空！");
            }
            if (StringUtils.isEmpty(password)) {
                return CommonResult.danger("密码不可为空！");
            }
            if (password.contains(" ")) {
                return CommonResult.danger("密码不可含有空格！");
            }
            if (! username.matches(WebConstants.CHINESE_ENGLISH_NUMBER_REGEX)) {
                return CommonResult.danger("用户名只能含有中文、数字、大小写字母！");
            }
            Player player = new Player();
            player.setUsername(username);
            player.setPassword(password);
            playerService.register(player);
            return CommonResult.success();
        } catch (ServiceException e) {
            return CommonResult.danger(e.getMessage());
        }
    }
    /**
     * 注销
     * @param session
     * @return
     */
    @RequestMapping(value = "logout")
    public String logout(HttpSession session) {
        session.removeAttribute("sessionPlayer");
        return "redirect:login";
    }

    /**
     * 获取websocket路径前缀
     * @return
     */
    @RequestMapping(value="getWsUrlPrefix")
    @ResponseBody
    public String getWsUrlPrefix() {
        String prefix = "ws://" + IpUtils.getLocalIp() + ":" + SpringBootUtil.getServerPort() + "/ws/";
        return prefix;
    }
}
