package utils;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import exception.FailedDBConnection;
import tables.ArticleTable;
import tables.CartTable;
import tables.MachineTable;
import tables.SettingTable;

public class InitServer implements ServletContextListener {
	private final static String CASHMANAGER_DOCKER_LOCAL = "CASHMANAGER_DOCKER_PRODUCTION";
	private final static String CASHMANAGER_DOCKER_PRODUCTION = "CASHMANAGER_DOCKER_PRODUCTION";
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Start server");
		String fileProperties = "dbconfig";
		String envVarProd = System.getenv(CASHMANAGER_DOCKER_PRODUCTION);
		String envVarLocal = System.getenv(CASHMANAGER_DOCKER_LOCAL);
		
		if(envVarLocal != null) {
			if(envVarLocal.equals("1"))
				fileProperties = "dbconfigLocal";
		}
		else
			if(envVarProd != null)
				if(envVarProd.equals("1"))
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
		} catch (ClassNotFoundException | SQLException | FailedDBConnection e) {
			System.out.println("InitServer Error : " + e.getMessage());
			try { TimeUnit.SECONDS.sleep(4); }catch(Exception e1) {System.out.println(LogsHandler.getMessageError(e1));};
			createAllTables();
		}
	}
	
	public static void restetDataBase() {
		try {
			DBConnector db = new DBConnector(new LogsHandler());
			db.executeSQL("DROP DATABASE IF EXISTS " + db.getDbName() + ";");
			db.executeSQL("CREATE DATABASE " + db.getDbName() + ";");
	      	db.close();
	      	System.out.println("Database has been droped and created");
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("InitServer Error : " + e.getMessage());
			try { Thread.sleep(4000); }catch(Exception e1) {};
			restetDataBase();
		}
    	
	}
}