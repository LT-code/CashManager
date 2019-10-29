package service.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.reflections.Reflections;

import entities.EntityClass;
import utils.DBConnector;

public class InitServer implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		CM_Log.info("Start server");
		DBConnector.getDBParam(sce.getServletContext().getResourceAsStream("/META-INF/conf/dbconfig.properties"));
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		CM_Log.info("End server");		
	}
	
	public static void createAllTables() throws InstantiationException, IllegalAccessException {
		Reflections reflections = new Reflections("entities");
	    Set<Class<? extends EntityClass>> classes = reflections.getSubTypesOf(EntityClass.class);
	    for (Class<? extends EntityClass> aClass : classes)
	        aClass.newInstance().createTable();
	}
}