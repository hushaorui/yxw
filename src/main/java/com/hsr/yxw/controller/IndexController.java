package com.hsr.yxw.controller;

import com.hsr.yxw.common.CommonResult;
import com.hsr.yxw.exception.ServiceException;
import com.hsr.yxw.account.pojo.Account;
import com.hsr.yxw.account.service.AccountService;
import com.hsr.yxw.account.common.AccountUtil;
import com.hsr.yxw.game.data.YxwGameDataContainer;
import com.hsr.yxw.game.data.YxwGameDataType;
import com.hsr.yxw.game.info.YxwGameFigure;
import com.hsr.yxw.game.info.YxwGameFigureInfo;
import com.hsr.yxw.game.service.YxwGameDataManager;
import com.hsr.yxw.game.service.YxwGameInfoManager;
import com.hsr.yxw.run.SpringBootUtil;
import com.hsr.yxw.sysconfig.common.SystemSwitch;
import com.hsr.yxw.sysconfig.service.SystemConfigService;
import com.hsr.yxw.util.IpUtils;
import com.hsr.yxw.ws.chat.pojo.PublicChatMessage;
import com.hsr.yxw.ws.chat.service.ChatMessageService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {
    private static final Log log = LogFactory.getLog(IndexController.class);
    private AccountService accountService;
    private SystemConfigService systemConfigService;
    private ChatMessageService chatMessageService;
    private YxwGameDataManager yxwGameDataManager;
    private YxwGameInfoManager yxwGameInfoManager;

    @Autowired
    public IndexController(AccountService accountService, SystemConfigService systemConfigService, ChatMessageService chatMessageService,
                           YxwGameDataManager yxwGameDataManager, YxwGameInfoManager yxwGameInfoManager) {
        this.accountService = accountService;
        this.systemConfigService = systemConfigService;
        this.chatMessageService = chatMessageService;
        this.yxwGameDataManager = yxwGameDataManager;
        this.yxwGameInfoManager = yxwGameInfoManager;
    }

    @RequestMapping(value = "index")
    public String index(Model model, HttpSession session) {
        List<PublicChatMessage> lastChatMessages = chatMessageService.getLastChatMessage(20, null);
        model.addAttribute("lastChatMessages", lastChatMessages);
        Account account = (Account) session.getAttribute("sessionAccount");
        long userId = account.getId();
        YxwGameDataContainer yxwGameDataContainer = yxwGameDataManager.getYxwGameDataContainer(userId);
        Map<Long, YxwGameFigure> figureMap = yxwGameDataContainer.getFigureMap();
        model.addAttribute("figures", figureMap.values());
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
     */
    @RequestMapping(value = "toLogin", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult toLogin(String username, String password, Boolean remember, HttpSession session) {
        Account account;
        try {
            account = accountService.login(username, password);
        } catch (ServiceException e) {
            e.printStackTrace();
            return CommonResult.systemError();
        }
        if (account == null) {
            return CommonResult.error("账号或密码错误！");
        }
        session.setAttribute("sessionAccount", account);
        return CommonResult.success(account.getAdmin() ? "admin/admin-index" : "index");
    }

    @RequestMapping(value = "toRegister", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult register(String username, String password) {
        try {
            boolean openRegisterSwitch = systemConfigService.getBoolValueNotNull(SystemSwitch.OPEN_REGISTER_SWITCH);
            if (! openRegisterSwitch) {
                return CommonResult.error("注册功能未开放，请通知管理员！");
            }
            CommonResult commonResult = AccountUtil.checkUsernameAndPassword(username, password);
            if (commonResult != null) {
                return commonResult;
            }
            Account account = new Account();
            account.setUsername(username);
            account.setPassword(password);
            accountService.register(account);
            return CommonResult.success("注册成功！");
        } catch (ServiceException e) {
            return CommonResult.error(e.getMessage());
        }
    }
    /**
     * 注销
     */
    @RequestMapping(value = "logout")
    public String logout(HttpSession session) {
        session.removeAttribute("sessionAccount");
        return "redirect:login";
    }

    /**
     * 获取websocket路径前缀
     */
    @RequestMapping(value="getWsUrlPrefix")
    @ResponseBody
    public String getWsUrlPrefix(HttpServletRequest request) {
        String ip = request.getServerName();
        if (ip == null) {
            ip = IpUtils.getLocalIp();
        }
        return "ws://" + ip + ":" + SpringBootUtil.getServerPort() + "/ws/";
    }
}
