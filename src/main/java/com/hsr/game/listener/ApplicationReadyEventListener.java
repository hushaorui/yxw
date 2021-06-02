package com.hsr.game.listener;

import com.hsr.game.yxw.service.YxwGameInfoManager;
import com.hsr.game.admin.service.AdminService;
import com.hsr.game.exception.ServiceException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * spring容器初始化后的一些操作
 */
@Component
public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {
	private static final Log log = LogFactory.getLog(ApplicationReadyEventListener.class);

	private AdminService adminService;
	private YxwGameInfoManager yxwGameInfoManager;

	@Autowired
	public ApplicationReadyEventListener(AdminService adminService, YxwGameInfoManager yxwGameInfoManager) {
		this.adminService = adminService;
		this.yxwGameInfoManager = yxwGameInfoManager;
	}

 
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		try {
			adminService.initDB(true);
			log.info("初始化数据库完成");
			yxwGameInfoManager.initYxwCard();
			log.info("初始化yxw卡牌完成");
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}
