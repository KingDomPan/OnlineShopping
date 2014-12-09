package com.us.os.qd.listeners;

import java.util.Timer;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class ProductPathListener implements ServletContextListener {

	private Timer timer = null;
	private WebApplicationContext appctx;
	public void contextDestroyed(ServletContextEvent event) {
		timer.cancel();
	}

	public void contextInitialized(ServletContextEvent event) {
		appctx=WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		timer = new Timer(true);
		timer.schedule(new ProductPathHelper(appctx), 0,
				5*1*60 * 1000); // 每5分钟执行一次GoogleTimer类
	}
	
}
