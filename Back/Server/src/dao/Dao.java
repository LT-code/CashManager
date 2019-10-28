package dao;

import java.util.ArrayList;
import java.util.Map;

import entities.EntityClass;
import exception.NoResultException;
import service.utils.CM_Database;
import service.utils.CM_Log;

public abstract class Dao {
	//===================================================================================
    /*
     * 
     */
    public boolean add(EntityClass entityClass) throws NoResultException {
    	long id = CM_Database.executePreparedSQL(
													"INSERT INTO " + entityClass.getTable().getName() +" (" + entityClass.getTable().getFields() + ") " +
													"VALUES (" + entityClass.getTable().getFieldsPrepared() + ");",
													true,
													entityClass.getFieldsValues()
												);
		boolean res = id  > 0;
		entityClass.setEntityID(id);
		CM_Log.debug("Insert " + (res ? "success" : "error") + " in " + entityClass.getTable().getName());
		
		return res;
    }

    
    //===================================================================================
    /*
     * 
     */
    public boolean update(EntityClass entityClass) throws NoResultException {
    	boolean res = CM_Database.executePreparedSQL(
														"UPDATE " + entityClass.getTable().getName() + " " +
														"SET " + entityClass.getTable().getSetFields() + " " +
														"WHERE " + entityClass.getTable().getSetID() + ";",
														false,
														addArrayElem(entityClass.getFieldsValues(), entityClass.getEntityID())
													) > 0;

		CM_Log.debug("Update " + (res ? "success" : "error") + " in " + entityClass.getTable().getName());
		return res;
    }
    //------------------------------------------------------------
    private Object[] addArrayElem(Object[] arr, Object elem) {
    	Object[] newArray = new Object[arr.length+1];
        System.arraycopy(arr, 0, newArray, 0, arr.length);
        newArray[arr.length] = elem;
    	return newArray;
    }
    

    //===================================================================================
    /*
     * 
     */
    public boolean delete(EntityClass entityClass) throws NoResultException {
    	boolean res = CM_Database.executePreparedSQL(
														"DELETE FROM " + entityClass.getTable().getName() +" " +
														"WHERE " + entityClass.getTable().getSetID() + ";",
														false,
														new Object[]{entityClass.getEntityID()}
													) > 0;
				
		CM_Log.debug("Delete " + (res ? "success" : "error") + " in " + entityClass.getTable().getName());
		return res;
	}
    
    //===================================================================================
    /*
     * 
     */
    public boolean createTable(Class<EntityClass> c) throws InstantiationException, IllegalAccessException {
    	EntityClass ec = c.newInstance();
    	ec.getTable().getName();
    	ec.getTable().getTableToSQL();
    	
    	return false;
    }
    
    //===================================================================================
    /*
     * 
     */
    protected ArrayList<Map<String, Object>> query(String queryString, Object[] values) {
  		return CM_Database.executeSQLRequest(queryString, values);
    }
    //------------------------------------------------------------
    protected ArrayList<Map<String, Object>> query(String queryString) {
    	return query(queryString, new Object[]{});
    }
}
