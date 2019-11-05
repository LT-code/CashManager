package utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import tables.ArticleTable;
import tables.CartTable;
import tables.MachineTable;
import tables.SettingTable;

public class InitServer implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Start server");
		DBConnector.getDBParam(sce.getServletContext().getResourceAsStream("/META-INF/conf/dbconfig.properties"));
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