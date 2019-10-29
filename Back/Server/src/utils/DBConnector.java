package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import exception.NoResultException;
import service.utils.CM_Log;

public class DBConnector {
	private final static String propertiesFileForTest = (new File("./")).getAbsolutePath() + "/WebContent/META-INF/conf/dbconfig.properties";
    
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
            
 	        DBConnector.host = properties.getProperty( "host" );
 	        DBConnector.dbName = properties.getProperty( "dbName" );
 	        DBConnector.port = Integer.parseInt(properties.getProperty( "port" ));
 	        DBConnector.user = properties.getProperty( "user" );
 	        DBConnector.password = properties.getProperty( "password" );
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
    	String url = "jdbc:mariadb://" + DBConnector.host + ":" + DBConnector.port + "/" + DBConnector.dbName;

    	try {
    		Class.forName("org.mariadb.jdbc.Driver");
    		connection = DriverManager.getConnection(
    													url,
    													DBConnector.user,
    													DBConnector.password
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
	        	//CM_Log.debug("DataBase closed");
	        } catch ( SQLException ignore ) {
	        	CM_Log.error(ignore);
	        }
    }
    
    public static void close(Connection connection, Statement statement) {
    	close(connection);
    	
    	if ( statement != null )
	        try {
	        	statement.close();
	        	//CM_Log.debug("Statement closed");
	        } catch ( SQLException ignore ) {
	        	CM_Log.error(ignore);
	        }
    }
    
    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
    	close(connection, statement);
    	
    	if ( resultSet != null )
	        try {
	        	resultSet.close();
	        	//CM_Log.debug("ResultSet closed");
	        } catch ( SQLException ignore ) {
	        	CM_Log.error(ignore);
	        }    	
    }

    
    //===================================================================================
    /*
     * Prepare and execute sql request
     */
    public static int executePreparedSQL(String sql, boolean returnGeneratedKeys, Object[] values) throws NoResultException {
    	Connection connexion = null;
	    PreparedStatement preparedStatement = null;
    	ResultSet valeursAutoGenerees = null;
	    	    
	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = DBConnector.connect();
	        preparedStatement = DBConnector.initialisationRequetePreparee( connexion, sql, returnGeneratedKeys, values);
	      
	        int statut = preparedStatement.executeUpdate();
	        
	        /* Analyse du statut retourné par la requête d'insertion */
	        if ( statut == 0 ) {
	            throw new NoResultException( "The Database returned an empty result set.");
	        }
	        
	        if(returnGeneratedKeys) {
	        	/* Récupération de l'id auto-généré par la requête d'insertion s */
		        valeursAutoGenerees = preparedStatement.getGeneratedKeys();
		        if (valeursAutoGenerees.next())
	                return valeursAutoGenerees.getInt(1);
	        }     
	    } catch ( SQLException e ) {
	        CM_Log.error( e );
	    } finally {
	        DBConnector.close( connexion, preparedStatement, valeursAutoGenerees );
	    }
    	
	    return -1;
    }
    
    //===================================================================================
    /*
     * 
     */
    public static boolean executeSQL(String query) {
    	Connection c = null;
    	Statement statement = null;
    	ResultSet resultat = null;
    	
    	try {
    		c = DBConnector.connect();
    		statement = c.createStatement();
    		resultat = statement.executeQuery(query);
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

    
    //===================================================================================
    /*
     * Prepare and execute sql requestMySQLMySQL
     */
    public static ArrayList<Map<String, Object>> executeQuerySQL(String sql, Object[] values) {
    	Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet result = null;

	    ArrayList<Map<String, Object>> rows = new ArrayList<>();
	    	    
	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = DBConnector.connect();
	        preparedStatement = DBConnector.initialisationRequetePreparee( connexion, sql, true, values);
	      
	        
	        result = preparedStatement.executeQuery();;
	        ResultSetMetaData md = preparedStatement.getMetaData();
	        int columns = md.getColumnCount();
	        while (result.next()){
	            Map<String, Object> row = new HashMap<String, Object>(columns);
	            for(int i = 1; i <= columns; ++i){
	                row.put(md.getColumnName(i), result.getObject(i));
	            }
	            rows.add(row);
	        }
	    } catch ( SQLException e ) {
	        CM_Log.error( e );
	    } finally {
	        DBConnector.close( connexion, preparedStatement, result );
	    }
    	
	    return rows;
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
