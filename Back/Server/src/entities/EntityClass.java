package entities;

import utils.Table;

public interface EntityClass {
	public String entityNameClass();
		
	public Table getTable();
	
	public Object[] getFieldsValues();
	
	public void setEntityID(Object id);
	
	public Object getEntityID();
}
