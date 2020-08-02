package com.hsr.yxw.controller;

import com.hsr.yxw.common.CommonResult;
import com.hsr.yxw.exception.ServiceException;
import com.hsr.yxw.player.pojo.Player;
import com.hsr.yxw.player.service.PlayerService;
import com.hsr.yxw.player.util.PlayerUtil;
import com.hsr.yxw.run.SpringBootUtil;
import com.hsr.yxw.sysconfig.common.SystemSwitch;
import com.hsr.yxw.sysconfig.service.SystemConfigService;
import com.hsr.yxw.util.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {
    @Autowired
    private PlayerService playerService;
    @Autowired
    private SystemConfigService systemConfigService;

    @RequestMapping(value = "index")
    public String index() {
        return "index";
    }
    @RequestMapping(value = "register")
    public String register() {
        return "register";
    }
    @RequestMapping(value = "login")
    public String login(Model model) {
        boolean openRegisterSwitch = systemConfigService.getBoolValueNotNull(SystemSwitch.OPEN_REGISTER_SWITCH);
        model.addAttribute("openRegisterSwitch", openRegisterSwitch);
        return "login";
    }

    /**
     * 登录
     * @param username
     * @param password
     * @param remember
     * @param session
     * @return
     */
    @RequestMapping(value = "toLogin", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult toLogin(String username, String password, Boolean remember, HttpSession session) {
        Player player;
        try {
            player = playerService.login(username, password);
        } catch (ServiceException e) {
            e.printStackTrace();
            return CommonResult.systemError();
        }
        if (player == null) {
            return CommonResult.error("账号或密码错误！");
        }
        session.setAttribute("sessionPlayer", player);
        return CommonResult.success(player.getAdmin() ? "admin/admin-index" : "index");
    }

    @RequestMapping(value = "toRegister", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult register(String username, String password) {
        try {
            boolean openRegisterSwitch = systemConfigService.getBoolValueNotNull(SystemSwitch.OPEN_REGISTER_SWITCH);
            if (! openRegisterSwitch) {
                return CommonResult.error("注册功能未开放，请通知管理员！");
            }
            CommonResult commonResult = PlayerUtil.checkUsernameAndPassword(username, password);
            if (commonResult != null) {
                return commonResult;
            }
            Player player = new Player();
            player.setUsername(username);
            player.setPassword(password);
            playerService.register(player);
            return CommonResult.success("注册成功！");
        } catch (ServiceException e) {
            return CommonResult.error(e.getMessage());
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
    public String getWsUrlPrefix(HttpServletRequest request) {
        String ip = request.getServerName();
        if (ip == null) {
            ip = IpUtils.getLocalIp();
        }
        String prefix = "ws://" + ip + ":" + SpringBootUtil.getServerPort() + "/ws/";
        return prefix;
    }
}
