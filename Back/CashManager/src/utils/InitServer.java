package utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import tables.ArticleTable;
import tables.CartTable;
import tables.MachineTable;
import tables.SettingTable;

public class InitServer implements ServletContextListener {
	private final static String CASHMANAGER_DOCKER_PRODUCTION = "CASHMANAGER_DOCKER_PRODUCTION";
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Start server");
		String fileProperties = "dbconfig";
		String envVar = System.getenv(CASHMANAGER_DOCKER_PRODUCTION);
		
		if(envVar != null)
			if(envVar.equals("1"))
				fileProperties = "dbconfigProd";
			
		DBConnector.getDBParam(sce.getServletContext().getResourceAsStream(sce.getServletContext().getInitParameter(fileProperties)));
		
		restetDataBase();
		
		createAllTables();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("End server");		
	}
	
	public static void createAllTables() {
		try {
			SettingTable.createTable();
			ArticleTable.createTable();
			MachineTable.createTable();
			CartTable.createTable();
		}
		catch(Exception e) {
			System.out.println("InitServer Error : " + LogsHandler.getMessageError(e));
		}
	}
	
	public static void restetDataBase() {
		try {
			DBConnector db = new DBConnector(new LogsHandler());			
	    	db.executeSQL("DROP DATABASE IF EXISTS " + db.getDbName() + ";");
	    	db.executeSQL("CREATE DATABASE " + db.getDbName() + ";");
	      	db.close();
	      	System.out.println("Database has been droped and created");
		}
		catch(Exception e) {
			System.out.println("InitServer Error : " + LogsHandler.getMessageError(e));
		}
	}
}