package entities;

import utils.DBConnector;
import utils.Table;

public interface EntityClass {
	public String entityNameClass();
		
	public Table getTable();
	
	public Object[] getFieldsValues();
	
	public void setEntityID(Object id);
	
	public Object getEntityID();
	
    public default boolean createTable() {    	
    	boolean res = DBConnector.executeSQL("drop table if exists " + this.getTable().getName() + ";");
      	return res && DBConnector.executeSQL(this.getTable().getTableToSQL());
    }
}
