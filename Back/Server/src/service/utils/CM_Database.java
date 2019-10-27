package service.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.NotActiveException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.omg.CORBA.portable.ApplicationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import service.type.ResultDB;

public class CM_Database {
	private final static String propertiesFileForTest = (new File("./")).getAbsolutePath() + "/WebContent/WEB-INF/conf/dbconfig.properties";
    // Attributes
    private static String host;
    private static int port;
    private static String dbName;
    private static String user;
    private static String password;
    
    //private static Connection connection;
    
    // Operations
    //===================================================================================
    /*
     * /WEB-INF/conf/dbconfig.properties
     */
    public static void getDBParam(InputStream inStream) {
    	try {
        	Properties properties = new Properties();
            properties.load(inStream == null ? new FileInputStream(new File(propertiesFileForTest)) : inStream );
            
 	        CM_Database.host = properties.getProperty( "host" );
 	        CM_Database.dbName = properties.getProperty( "dbName" );
 	        CM_Database.port = Integer.parseInt(properties.getProperty( "port" ));
 	        CM_Database.user = properties.getProperty( "user" );
 	        CM_Database.password = properties.getProperty( "password" );
 	        CM_Log.info("DB parameters has been gotten");
        }
        catch (Exception e) {
		   CM_Log.error(e);
		}
    }
    // when server start, the path is different
    public static void getDBParam () { getDBParam(null); }
    
    
    //===================================================================================
    /*
     * 	Connect to the data base
     * 	Need the execution of getDBParam to works
     */
    public static Connection connect() {
    	Connection connection = null;
    	System.out.println("---------------------");
    	String url = "jdbc:mariadb://" + CM_Database.host + ":" + CM_Database.port + "/" + CM_Database.dbName;

    	try {
    		Class.forName("org.mariadb.jdbc.Driver");
    		connection = DriverManager.getConnection(
    													url,
    													CM_Database.user,
    													CM_Database.password
    												);
    	    CM_Log.debug("Connect to the DataBase " + url);

    	} catch ( SQLException | ClassNotFoundException e ) {
    	    CM_Log.error(e);
    	}
    	
    	return connection;
    }
    
    //===================================================================================
    /*
     * 	Close the dataBase connection
     */
    public static void close(Connection connection) {
    	if ( connection != null )
	        try {
	        	connection.close();
	        	CM_Log.debug("DataBase closed");
	        } catch ( SQLException ignore ) {
	        	CM_Log.error(ignore);
	        }
    }
    
    public static void close(Connection connection, Statement statement) {
    	close(connection);
    	
    	if ( statement != null )
	        try {
	        	statement.close();
	        	CM_Log.debug("Statement closed");
	        } catch ( SQLException ignore ) {
	        	CM_Log.error(ignore);
	        }
    }
    
    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
    	close(connection, statement);
    	
    	if ( resultSet != null )
	        try {
	        	resultSet.close();
	        	CM_Log.debug("ResultSet closed");
	        } catch ( SQLException ignore ) {
	        	CM_Log.error(ignore);
	        }    	
    }

    
    //===================================================================================
    /*
     * 
     */
    public static boolean createDataBase() {
    	Connection c = null;
    	Statement statement = null;
    	ResultSet resultat = null;
    	
    	try {
    		c = CM_Database.connect();
    		statement = c.createStatement();
    		resultat = statement.executeQuery( 	"drop table if exists Setting;\n");
    		resultat = statement.executeQuery( 	"create table Setting (" + 
							    				"   idSetting       	INT AUTO_INCREMENT PRIMARY KEY not null," + 
							    				"   connectionDelay		INT                            not null," + 
							    				"   maxAttemps      	INT                            not null" + 
							    				");" );
    		CM_Log.debug("Creation tables is a success");
    		return true;
    	}
    	catch (SQLException e) {
			CM_Log.error(e);
		}
    	finally {
    		close(c, statement, resultat);
    	}
 
		return false;
    }
    
    //###################################################################################
    // Private
    //###################################################################################
  
    //===================================================================================
    /*
     * Prepare and execute sql request
     */
    public static int executePreparedSQL(String sql, boolean returnGeneratedKeys, Object[] fieldValues) {
    	Connection connexion = null;
	    PreparedStatement preparedStatement = null;
    	ResultSet valeursAutoGenerees = null;
	    	    
	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = CM_Database.connect();
	        preparedStatement = CM_Database.initialisationRequetePreparee( connexion, sql, returnGeneratedKeys, fieldValues);
	      
	        int statut = preparedStatement.executeUpdate();
	        
	        /* Analyse du statut retourné par la requête d'insertion */
	        if ( statut == 0 ) {
	            CM_Log.error( "Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table." );
	            return -1;
	        }
	        
	        if(returnGeneratedKeys) {
	        	/* Récupération de l'id auto-généré par la requête d'insertion s */
		        valeursAutoGenerees = preparedStatement.getGeneratedKeys();
		        if (valeursAutoGenerees.next())
	                return valeursAutoGenerees.getInt(1);
	        }
	        else
	        	return 1;
	        
	       
	    } catch ( SQLException e ) {
	        CM_Log.error( e );
	    } finally {
	        CM_Database.close( connexion, preparedStatement, valeursAutoGenerees );
	    }
    	
	    return -1;
    }
    
    //===================================================================================
    /*
     * Initialise la requête préparée basée sur la connexion passée en argument,
     * avec la requête SQL et les objets donnés.
     */
    private static PreparedStatement initialisationRequetePreparee( Connection connexion, String sql, boolean returnGeneratedKeys, Object[] objets) throws SQLException {
        PreparedStatement preparedStatement = connexion.prepareStatement( sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS );
        //preparedStatement.setTimestamp(1, new java.sql.Timestamp(new java.util.Date().getTime())); 
        for ( int i = 0; i < objets.length; i++ )
            preparedStatement.setObject( i + 1, objets[i] );
           
        CM_Log.debug(sql + " " + Arrays.asList(objets));
        return preparedStatement;
    }
}
