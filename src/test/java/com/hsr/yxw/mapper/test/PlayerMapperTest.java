package com.hsr.yxw.mapper.test;

import com.hsr.yxw.mapper.PlayerMapper;
import com.hsr.yxw.run.YxwApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = YxwApplication.class)
public class PlayerMapperTest {
    @Autowired
    private PlayerMapper playerMapper;

    @Test
    public void test_PlayerMapper() {
        System.out.println(playerMapper);
    }
    @Test
    public void test_dropTable() {
        try {
            playerMapper.dropTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
