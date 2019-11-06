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
		
		String propertiesFileName = "dbconfig.properties";
		String val = System.getenv(CASHMANAGER_DOCKER_PRODUCTION);
		
		if(val != null)
			if(val.equals("1")) {
				System.out.println("Production properties");
				propertiesFileName = "prod." + propertiesFileName;
			}
			else
				System.out.println("Classic properties");
			
		DBConnector.getDBParam(sce.getServletContext().getResourceAsStream("/META-INF/conf/" + propertiesFileName));
		
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
	    	db.executeSQL("DROP DATABASE " + db.getDbName() + ";");
	    	db.executeSQL("CREATE DATABASE " + db.getDbName() + ";");
	      	db.close();
		}
		catch(Exception e) {
			System.out.println("InitServer Error : " + LogsHandler.getMessageError(e));
		}
	}
}