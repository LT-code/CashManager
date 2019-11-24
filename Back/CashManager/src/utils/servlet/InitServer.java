package utils.servlet;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import exception.FailedDBConnection;
import exception.NoResultException;
import exception.ValidatorNotRecpectedException;
import tables.ArticleTable;
import tables.CartArticlesTable;
import tables.CartTable;
import tables.MachineTable;
import tables.PaymentTable;
import tables.PaymentTypeTable;
import tables.SettingTable;
import utils.LogsHandler;
import utils.bdd.DBConnector;

public class InitServer implements ServletContextListener {
	private final static String CASHMANAGER_DOCKER_LOCAL = "CASHMANAGER_DOCKER_LOCAL";
	private final static String CASHMANAGER_DOCKER_PRODUCTION = "CASHMANAGER_DOCKER_PRODUCTION";
	public final static String CASHMANAGER_DB_PASSWORD = "CASHMANAGER_DB_PASSWORD";
	
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
		System.out.println("File Properties choosen : " + fileProperties);
			
		DBConnector.getDBParam(sce.getServletContext().getResourceAsStream(sce.getServletContext().getInitParameter(fileProperties)));

		//restetDataBase();
		
		createAllTables();
		
		System.out.println("Env PORT : " + System.getenv("PORT"));
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("End server");		
	}
	
	public static void createAllTables() {
		createAllTables(0);
	}
	public static void createAllTables(int attemps) {
		try {
			attemps++;
			SettingTable.createTable();
			ArticleTable.createTable();
			MachineTable.createTable();
			CartTable.createTable();
			PaymentTypeTable.createTable();
			CartArticlesTable.createTable();
			PaymentTable.createTable();
			
		} catch (ClassNotFoundException | SQLException | FailedDBConnection | NoResultException | ValidatorNotRecpectedException e) {
			System.out.println("InitServer Error creation tables : " + e.getMessage());
			try { TimeUnit.SECONDS.sleep(5); }catch(Exception e1) {System.out.println(LogsHandler.getMessageError(e1));};
			if(attemps > 10)
				System.exit(-1);
		
			createAllTables(attemps);
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
			System.out.println("InitServer Error reset database : " + e.getMessage());
			try { Thread.sleep(4000); }catch(Exception e1) {};
			restetDataBase();
		}
    	
	}
}