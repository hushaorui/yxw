package com.hsr.yxw.mapper;

import com.hsr.yxw.common.BaseMapper;
import com.hsr.yxw.account.pojo.Account;
import com.hsr.yxw.account.common.AccountQueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface AccountMapper extends BaseMapper<Account, AccountQueryVo> {
    // 根据用户名查询
    Account selectByUsername(String username);
}