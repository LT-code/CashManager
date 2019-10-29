package tables;

import java.sql.SQLException;

import utils.DBConnector;
import utils.Table;

public interface TableClass {
	public String entityNameClass();
		
	public Table getTable();
	
	public Object[] getFieldsValues();
	
	public void setEntityID(Object id);
	
	public Object getEntityID();
	
    public default void createTable() throws SQLException, ClassNotFoundException {    
    	DBConnector db = new DBConnector();
    	db.executeSQL("drop table if exists " + this.getTable().getName() + ";");
      	db.executeSQL(this.getTable().getTableToSQL());
      	db.close();
    }
}
