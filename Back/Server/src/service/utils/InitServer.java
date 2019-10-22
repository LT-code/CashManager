package service.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitServer implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Start server");	
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("End server");		
	}
}