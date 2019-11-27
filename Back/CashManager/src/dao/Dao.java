package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import entities.EntityClass;
import exception.InvalidNumberReslut;
import exception.NoResultException;
import utils.LogsHandler;
import utils.bdd.DBConnector;
import utils.bdd.Table;

public abstract class Dao {
	DBConnector db;
	LogsHandler errorHandler;
	Table table;
	
	public Dao(DBConnector db, LogsHandler errorHandler, Table table) {
		this.table = table; 
		this.db = db;
		this.errorHandler = errorHandler;
	}
	
	//===================================================================================
    /*
     * 
     */
    public boolean add(EntityClass entityClass) throws NoResultException, SQLException {
    	long id = db.executePreparedSQL(
											"INSERT INTO " + table.getName() +" (" + table.getFields() + ") " +
											"VALUES (" + table.getFieldsPrepared() + ");",
											table.hasAutoGeneratedField(),
											entityClass.fieldsValues()
										);
		boolean res = id  > 0;
		if(table.hasAutoGeneratedField())
			entityClass.setId(id);
		errorHandler.addDebug("Insert " + (res ? "success" : "error") + " in " + table.getName() + " | result: " + id);
		
		return res;
    }

    
    //===================================================================================
    /*
     * 
     */
    public boolean update(EntityClass entityClass) throws NoResultException, SQLException {
    	boolean res = db.executePreparedSQL(
												"UPDATE " + table.getName() + " " +
												"SET " + table.getFieldsSet() + " " +
												"WHERE " + table.getIDSet() + ";",
												false,
												addArrayElem(entityClass.fieldsValues(), entityClass.getId())
											) > 0;

		errorHandler.addDebug("Update " + (res ? "success" : "error") + " in " + table.getName());
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
    public boolean delete(EntityClass entityClass) throws NoResultException, SQLException {
    	boolean res = db.executePreparedSQL(
												"DELETE FROM " + table.getName() +" " +
												"WHERE " + table.getIDSet() + ";",
												false,
												new Object[]{entityClass.getId()}
											) > 0;
				
		errorHandler.addDebug("Delete " + (res ? "success" : "error") + " in " + table.getName());
		return res;
	}
        
    //===================================================================================
    /*
     * 
     */
    protected ArrayList<Map<String, Object>> queryList(String queryString, Object[] values) throws SQLException {
  		return db.executeQuerySQL(queryString, values);
    }
    //------------------------------------------------------------
    protected ArrayList<Map<String, Object>> queryList(String queryString) throws SQLException {
    	return queryList(queryString, new Object[]{});
    }
    
    //===================================================================================
    /*
     * 
     */
    protected Map<String, Object> query(String queryString, Object[] values) throws SQLException, InvalidNumberReslut {
    	ArrayList<Map<String, Object>> res = db.executeQuerySQL(queryString, values);
    	if(res.size() != 1)
    		throw new InvalidNumberReslut(res.size());
    	return res.get(0);
    }
    //------------------------------------------------------------
    protected Map<String, Object> query(String queryString) throws SQLException, InvalidNumberReslut {
    	return query(queryString, new Object[]{});
    }
    
    public Table getTable() {
    	return table;
    }

	public Map<String, Object> get(Object id) throws SQLException, InvalidNumberReslut {
		return query("Select * from " + table.getName() + " where " + table.getIDSet(), new Object[]{id});
	}
}
