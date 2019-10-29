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

public class DBConnector {
	private final static String propertiesFileForTest = (new File("./")).getAbsolutePath() + "/WebContent/META-INF/conf/dbconfig.properties";
    
	// Attributes
    private static String host;
    private static int port;
    private static String dbName;
    private static String user;
    private static String password;
    
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet result;
    private Statement statement;
   
   public DBConnector() throws ClassNotFoundException, SQLException {
	   connection = null;
	   preparedStatement = null;
	   result = null;
	   statement = null;
	   this.connect();
   }
    
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
    public Connection connect() throws ClassNotFoundException, SQLException {
    	System.out.println("---------------------");
    	String url = "jdbc:mariadb://" + DBConnector.host + ":" + DBConnector.port + "/" + DBConnector.dbName;

		Class.forName("org.mariadb.jdbc.Driver");
		connection = DriverManager.getConnection(
													url,
													DBConnector.user,
													DBConnector.password
												);
	    CM_Log.debug("Connect to the DataBase " + url);

    	
    	return connection;
    }
    
    //===================================================================================
    /*
     * 	Close the dataBase connection
     */
    public void close() {
    	if ( connection != null )
	        try {
	        	connection.close();
	        	//CM_Log.debug("DataBase closed");
	        } catch ( SQLException ignore ) {
	        	CM_Log.error(ignore);
	        }

    	if ( statement != null )
	        try {
	        	statement.close();
	        	//CM_Log.debug("Statement closed");
	        } catch ( SQLException ignore ) {
	        	CM_Log.error(ignore);
	        }
    	
    	if ( preparedStatement != null )
	        try {
	        	preparedStatement.close();
	        	//CM_Log.debug("Statement closed");
	        } catch ( SQLException ignore ) {
	        	CM_Log.error(ignore);
	        }

    	if ( result != null )
	        try {
	        	result.close();
	        	//CM_Log.debug("ResultSet closed");
	        } catch ( SQLException ignore ) {
	        	CM_Log.error(ignore);
	        }    	
    }

    
    //===================================================================================
    /*
     * Prepare and execute sql request
     */
    public int executePreparedSQL(String sql, boolean returnGeneratedKeys, Object[] values) throws NoResultException, SQLException {
	   
        /* Récupération d'une connexion depuis la Factory */
        this.initialisationRequetePreparee( sql, returnGeneratedKeys, values);
      
        int statut = preparedStatement.executeUpdate();
        
        /* Analyse du statut retourné par la requête d'insertion */
        if ( statut == 0 ) {
            throw new NoResultException( "The Database returned an empty result set.");
        }
        
        if(returnGeneratedKeys) {
        	/* Récupération de l'id auto-généré par la requête d'insertion s */
	        result = preparedStatement.getGeneratedKeys();
	        if (result.next())
                return result.getInt(1);
        }     

	    return -1;
    }
    
    //===================================================================================
    /*
     * 
     */
    public void executeSQL(String query) throws SQLException {
		statement = connection.createStatement();
		statement.executeQuery(query);
    }

    
    //===================================================================================
    /*
     * Prepare and execute sql requestMySQLMySQL
     */
    public ArrayList<Map<String, Object>> executeQuerySQL(String sql, Object[] values) throws SQLException {
	    ArrayList<Map<String, Object>> rows = new ArrayList<>();
	    	
        /* Récupération d'une connexion depuis la Factory */
        this.initialisationRequetePreparee( sql, true, values);
      
        
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
    	
	    return rows;
    }
    
    //===================================================================================
    /*
     * Initialise la requête préparée basée sur la connexion passée en argument,
     * avec la requête SQL et les objets donnés.
     */
    private void initialisationRequetePreparee( String sql, boolean returnGeneratedKeys, Object[] objets) throws SQLException {
        preparedStatement = connection.prepareStatement( sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS );
        //preparedStatement.setTimestamp(1, new java.sql.Timestamp(new java.util.Date().getTime())); 
        for ( int i = 0; i < objets.length; i++ )
            preparedStatement.setObject( i + 1, objets[i] );
           
        CM_Log.debug(sql + " " + Arrays.asList(objets));
    }
}
