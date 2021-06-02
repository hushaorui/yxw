package com.hsr.game.mapper;

import com.hsr.game.common.BaseMapper;
import com.hsr.game.account.pojo.Account;
import com.hsr.game.account.common.AccountQueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface AccountMapper extends BaseMapper<Account, AccountQueryVo> {
    // 根据用户名查询
    Account selectByUsername(String username);
}
