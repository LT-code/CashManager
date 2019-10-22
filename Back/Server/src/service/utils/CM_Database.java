package service.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import service.type.ResultDB;

public final class CM_Database {
    // Attributes
    private String host = "localhost";
    private int port;
    private String dbName;
    private String user;
    private String password;
    
    // Operations
    public void getDBParam () {
    }
    
    /*
     * 
     */
    public Connection connect() throws ClassNotFoundException {
    	Class.forName("org.mariadb.jdbc.Driver");
    	/* Connexion à la base de données */
    	String url = "jdbc:mariadb://" + this.host + ":" + this.port + "/" + this.dbName;
    	String utilisateur = "java";
    	String motDePasse = "SdZ_eE";
    	Connection connection = null;
    	try {
    		connection = DriverManager.getConnection( url, utilisateur, motDePasse );

    	    /* Ici, nous placerons nos requêtes vers la BDD */
    	    /* ... */

    	} catch ( SQLException e ) {
    	    /* Gérer les éventuelles erreurs ici */
    	}
    	
    	return connection;
    }
    
    /*
     * 
     */
    public void close(Connection connection) {
    	if ( connection != null )
	        try {
	            /* Fermeture de la connexion */
	        	connection.close();
	        } catch ( SQLException ignore ) {
	            /* Si une erreur survient lors de la fermeture, il suffit de l'ignorer. */
	        }
    }
    
    public ResultDB insert(String table, String [] attributes) {
		return null;
    }
    
    public ResultDB update(String table, String [] attributes) {
		return null;
    }
    
    public ResultDB delete(String table, String [] attributes) {
		return null;
    }
    
    public ResultDB get(String table) {
		return null;
    }
    
    public boolean createDataBase() {
		return false;
    }
}
