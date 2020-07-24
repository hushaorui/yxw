package com.hsr.yxw.listener;

import com.hsr.yxw.mapper.PlayerMapper;
import com.hsr.yxw.pojo.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * spring容器初始化后的一些操作
 */
@Component
public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {

	private static boolean resetDB = true;

	@Autowired
	private PlayerMapper playerMapper;
 
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		if (resetDB) {
			resetDB();
		}
	}

	/**
	 * 每次启动，清空数据，初始化
	 */
	public void resetDB() {
		try {
			playerMapper.dropTable();
			playerMapper.create();
			Player admin = new Player("admin", "admin", true, System.currentTimeMillis());
			playerMapper.insert(admin);
			Player player1 = new Player("player1", "player1", false, System.currentTimeMillis());
			Player player2 = new Player("player2", "player2", false, System.currentTimeMillis());
			playerMapper.insert(player1);
			playerMapper.insert(player2);

			for (int i = 1; i < 50; i++) {
				Player player = new Player("test"+i, "test"+i, false, System.currentTimeMillis());
				playerMapper.insert(player);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}