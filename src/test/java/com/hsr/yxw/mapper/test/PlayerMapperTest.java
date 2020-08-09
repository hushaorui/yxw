package com.hsr.yxw.mapper.test;

import com.alibaba.fastjson.JSONArray;
import com.hsr.yxw.mapper.PlayerMapper;
import com.hsr.yxw.player.pojo.Player;
import com.hsr.yxw.player.common.PlayerQueryVo;
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

    @Test
    public void test_count() {
        try {
            Integer count = playerMapper.count(null);
            print(count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test_selectAll() {
        try {
            List<Player> list = playerMapper.selectAll();
            print(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_selectById() {
        try {
            Player player = playerMapper.selectById(50);
            Player player1 = playerMapper.selectByUsername("admin");
            print(player);
            print(player1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test_selectByVo() {
        try {
            PlayerQueryVo vo = new PlayerQueryVo();
            Player player = new Player();
            vo.setPlayer(player);
            //player.setUsername("test");
            //vo.setFirstResult(10);
            vo.setFirstResult(20);
            vo.setMaxResult(5);
            List<Player> list = playerMapper.selectByVo(vo);
            System.out.println(list.size());
            print(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_deleteById() {
        try {
            playerMapper.deleteById(1L);
            List<Long> list = new ArrayList<>();
            list.add(3L);
            list.add(4L);
            playerMapper.delete(list);
            System.out.println(playerMapper.count(null));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_update() {
        try {
            Player player = playerMapper.selectById(1);
            player.setPassword("password...");
            playerMapper.update(player);
            Player p = playerMapper.selectById(1);
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
