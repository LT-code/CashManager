package entities;

import utils.Table;

public interface EntityClass {
	public Object[] fieldsValues();
	
	public void setId(Object id);
	
	public Object getId();
	
	public Table table();
}
