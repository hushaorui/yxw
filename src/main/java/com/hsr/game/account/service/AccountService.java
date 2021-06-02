package com.hsr.game.account.service;

import com.hsr.game.account.pojo.Account;
import com.hsr.game.common.PageBean;
import com.hsr.game.exception.ServiceException;
import com.hsr.game.account.common.AccountQueryVo;

import java.util.List;

public interface AccountService {
    Account login(String username, String password) throws ServiceException;

    Account getAccountByUsername(String username) throws ServiceException;

    long count(AccountQueryVo vo) throws ServiceException;

    List<Account> getAllAccounts() throws ServiceException;

    PageBean<Account> getAccountPageBean(Integer pageNum, Integer pageSize, AccountQueryVo vo) throws ServiceException;

    void deleteAccountById(Long id) throws ServiceException;

    void deleteAccounts(String ids) throws ServiceException;

    Account getAccountById(Long id) throws ServiceException;

    void register(Account account) throws ServiceException;

    void updateAccount(Account account) throws ServiceException;
}
