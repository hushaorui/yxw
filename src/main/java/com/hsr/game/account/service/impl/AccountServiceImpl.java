package com.hsr.game.account.service.impl;

import com.hsr.game.account.common.AccountQueryVo;
import com.hsr.game.account.pojo.Account;
import com.hsr.game.account.service.AccountService;
import com.hsr.game.common.PageBean;
import com.hsr.game.mapper.AccountMapper;
import com.hsr.game.util.YxwStringUtils;
import com.hsr.game.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Account login(String username, String password) throws ServiceException {
        if (username == null || password == null || username.trim().length() == 0) {
            return null;
        }
        long now = System.currentTimeMillis();
        username = username.trim();
        Account account = accountMapper.selectByUsername(username);
        if (account != null) {
            // 验证密码，就用明文密码了，不费劲加密了
            if (password.equals(account.getPassword())) {
                // 密码正确
                // 更新最后一次登录时间
                Long lastLoginTime = account.getLastLoginTime();
                account.setLastLoginTime(now);
                // 更新下最后登录时间
                accountMapper.update(account);
                // 回显上一次登录时间使用
                account.setLastLoginTime(lastLoginTime);
            } else {
                // 密码错误
                return null;
            }
        }
        return account;
    }

    @Override
    public Account getAccountByUsername(String username) throws ServiceException {
        return accountMapper.selectByUsername(username);
    }

    @Override
    public long count(AccountQueryVo vo) throws ServiceException {
        return accountMapper.count(vo);
    }

    @Override
    public List<Account> getAllAccounts() throws ServiceException {
        return accountMapper.selectAll();
    }

    @Override
    public PageBean<Account> getAccountPageBean(Integer pageNum, Integer pageSize, AccountQueryVo vo) throws ServiceException {
        int count = accountMapper.count(vo);
        PageBean<Account> pageBean = new PageBean<Account>(count, pageSize, pageNum);
        if (vo == null) {
            vo = new AccountQueryVo();
        }
        vo.setFirstResult(pageBean.getFirstResult());
        vo.setMaxResult(pageBean.getPageSize());
        List<Account> accounts = accountMapper.selectByVo(vo);
        pageBean.setOtherPage();
        pageBean.setPageList(accounts);
        return pageBean;
    }

    @Override
    public void deleteAccountById(Long id) throws ServiceException {
        if (id == 1) {
            throw new ServiceException("ID为1的用户禁止删除！");
        }
        accountMapper.deleteById(id);
    }

    @Override
    public void deleteAccounts(String ids) throws ServiceException {
        ArrayList<Long> idList = YxwStringUtils.idStringToList(ids);
        if (idList.contains(1L)) {
            throw new ServiceException("ID为1的用户禁止删除！");
        }
        accountMapper.delete(idList);
    }

    @Override
    public Account getAccountById(Long id) throws ServiceException {
        return accountMapper.selectById(id);
    }

    @Override
    public void register(Account account) throws ServiceException {
        // 调用此方法前必须已经校验了 用户名和密码的格式了
        Account existAccount = accountMapper.selectByUsername(account.getUsername());
        if (existAccount != null) {
            throw new ServiceException("该用户名已经存在！");
        }
        account.setId(null);
        account.setCreateTime(System.currentTimeMillis());
        if (account.getAdmin() == null) {
            account.setAdmin(false);
        }
        accountMapper.insert(account);
    }

    @Override
    public void updateAccount(Account account) throws ServiceException {
        Account existIdAccount = accountMapper.selectById(account.getId());
        if (existIdAccount == null) {
            throw new ServiceException("该用户不存在，id：" + account.getId());
        }
        if (!existIdAccount.getUsername().equals(account.getUsername())) {
            // 用户名有做修改
            if (existIdAccount.getId() == 1) {
                throw new ServiceException("Id为1的用户禁止修改用户名！");
            }
            Account existNameAccount = accountMapper.selectByUsername(account.getUsername());
            if (existNameAccount != null) {
                // 用户名已经被使用了
                throw new ServiceException("该用户名已存在：" + account.getUsername());
            }
        }
        // 目前只可修改这两个值
        existIdAccount.setUsername(account.getUsername());
        existIdAccount.setPassword(account.getPassword());
        if (account.getAdmin() == null) {
            existIdAccount.setAdmin(false);
        } else {
            existIdAccount.setAdmin(account.getAdmin());
        }
        accountMapper.update(existIdAccount);
    }
}
