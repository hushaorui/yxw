package com.hsr.game.admin.controller;

import com.alibaba.druid.util.StringUtils;
import com.hsr.game.common.CommonResult;
import com.hsr.game.common.PageBean;
import com.hsr.game.exception.ServiceException;
import com.hsr.game.account.pojo.Account;
import com.hsr.game.account.service.AccountService;
import com.hsr.game.account.common.AccountUtil;
import com.hsr.game.account.common.AccountQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminAccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value="admin/account-list")
    public String accountList(Integer pageNum, Integer pageSize, AccountQueryVo vo, Model model) throws Exception {
        PageBean<Account> pageBean = accountService.getAccountPageBean(pageNum, pageSize, vo);
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("accountQueryVo", vo);
        model.addAttribute("pageSizes", PageBean.pageSizes);
        return "admin/account-list";
    }
    @RequestMapping(value = "admin/account-delete")
    @ResponseBody
    public CommonResult deleteAccount(String ids) {
        if (StringUtils.isEmpty(ids)) {
            return CommonResult.error("ID不可为空！");
        }
        try {
            accountService.deleteAccounts(ids);
            return CommonResult.deleteSuccess();
        } catch (ServiceException e) {
            return CommonResult.error(e.getMessage());
        }
    }
    @RequestMapping(value="admin/account-add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult addAccount(Account account) {
        try {
            CommonResult commonResult = AccountUtil.checkUsernameAndPassword(account.getUsername(), account.getPassword());
            if (commonResult != null) {
                return commonResult;
            }
            accountService.register(account);
            return CommonResult.addSuccess();
        } catch (ServiceException e) {
            return CommonResult.error(e.getMessage());
        }
    }

    @RequestMapping(value = "admin/account-update")
    @ResponseBody
    public CommonResult updateAccount(Account account) {
        try {
            if (account == null || account.getId() == null) {
                return CommonResult.error("用户id不可为空！");
            }
            CommonResult commonResult = AccountUtil.checkUsernameAndPassword(account.getUsername(), account.getPassword());
            if (commonResult != null) {
                return commonResult;
            }
            accountService.updateAccount(account);
            return CommonResult.updateSuccess();
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
    }
}
