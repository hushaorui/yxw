package com.hsr.yxw.listener;

import com.hsr.yxw.admin.service.AdminService;
import com.hsr.yxw.exception.ServiceException;
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
	private AdminService adminService;
 
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		if (resetDB) {
			try {
				adminService.resetDB();
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
	}
}