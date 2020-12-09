package com.hsr.yxw.listener;

import com.hsr.yxw.admin.service.AdminService;
import com.hsr.yxw.card.config.YxwCardManager;
import com.hsr.yxw.exception.ServiceException;
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
	private YxwCardManager yxwCardManager;

	@Autowired
	public ApplicationReadyEventListener(AdminService adminService, YxwCardManager yxwCardManager) {
		this.adminService = adminService;
		this.yxwCardManager = yxwCardManager;
	}

 
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		try {
			adminService.initDB(true);
			log.info("初始化数据库完成");
			yxwCardManager.initYxwCard();
			log.info("初始化yxw卡牌完成");
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}
