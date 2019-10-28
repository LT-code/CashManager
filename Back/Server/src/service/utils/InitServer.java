package service.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitServer implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		CM_Log.info("Start server");
		CM_Database.getDBParam(sce.getServletContext().getResourceAsStream("/META-INF/conf/dbconfig.properties"));
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		CM_Log.info("End server");		
	}
}