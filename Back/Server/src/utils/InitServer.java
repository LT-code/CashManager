package utils;

import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.reflections.Reflections;

import tables.TableClass;

public class InitServer implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		CM_Log.info("Start server");
		DBConnector.getDBParam(sce.getServletContext().getResourceAsStream("/META-INF/conf/dbconfig.properties"));
		createAllTables();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		CM_Log.info("End server");		
	}
	
	public static void createAllTables() {
		try {
			Reflections reflections = new Reflections("tables");
		    Set<Class<? extends TableClass>> classes = reflections.getSubTypesOf(TableClass.class);
		    for (Class<? extends TableClass> aClass : classes) {
		    	TableClass ec = aClass.newInstance();
		    	ec.createTable();
			    CM_Log.info(ec.getTable().getName() + " DataBase was deleted and created");
		    }
		}
		catch(Exception e) {
			CM_Log.error(e);
		}
	}
}