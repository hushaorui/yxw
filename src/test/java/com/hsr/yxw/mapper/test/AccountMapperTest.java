package com.hsr.yxw.mapper.test;

import com.alibaba.fastjson.JSONArray;
import com.hsr.yxw.mapper.AccountMapper;
import com.hsr.yxw.account.pojo.Account;
import com.hsr.yxw.account.common.AccountQueryVo;
import com.hsr.yxw.run.YxwApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = YxwApplication.class)
public class AccountMapperTest {
    @Autowired
    private AccountMapper accountMapper;

    @Test
    public void test_AccountMapper() {
        System.out.println(accountMapper);
    }
    @Test
    public void test_dropTable() {
        try {
            accountMapper.dropTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_count() {
        try {
            Integer count = accountMapper.count(null);
            print(count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test_selectAll() {
        try {
            List<Account> list = accountMapper.selectAll();
            print(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_selectById() {
        try {
            Account account = accountMapper.selectById(50);
            Account account1 = accountMapper.selectByUsername("admin");
            print(account);
            print(account1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test_selectByVo() {
        try {
            AccountQueryVo vo = new AccountQueryVo();
            Account account = new Account();
            vo.setAccount(account);
            //account.setUsername("test");
            //vo.setFirstResult(10);
            vo.setFirstResult(20);
            vo.setMaxResult(5);
            List<Account> list = accountMapper.selectByVo(vo);
            System.out.println(list.size());
            print(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_deleteById() {
        try {
            accountMapper.deleteById(1L);
            List<Long> list = new ArrayList<>();
            list.add(3L);
            list.add(4L);
            accountMapper.delete(list);
            System.out.println(accountMapper.count(null));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_update() {
        try {
            Account account = accountMapper.selectById(1);
            account.setPassword("password...");
            accountMapper.update(account);
            Account p = accountMapper.selectById(1);
            print(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void print(Object object) {
        if (object == null) {
            System.out.println(object);
            return;
        }
        List<Object> list;
        //Map<Object, Object> map = null;
        if (object instanceof List) {
            list = (List<Object>) object;
            for (Object obj : list) {
                System.out.println(JSONArray.toJSONString(obj));
            }
        } else if (object instanceof Map) {
            System.out.println(object);
        } else {
            System.out.println(JSONArray.toJSONString(object));
        }
    }
}