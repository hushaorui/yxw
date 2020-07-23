package com.hsr.yxw.listener;

import com.hsr.yxw.mapper.PlayerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * spring容器初始化后的一些操作
 */
@Component
public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {

	private boolean cleanup = true;

	@Autowired
	private PlayerMapper playerMapper;
 
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		try {
			if (cleanup) {
				playerMapper.dropTable();
				playerMapper.create();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}