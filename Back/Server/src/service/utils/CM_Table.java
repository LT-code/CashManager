package service.utils;


public abstract class CM_Table {
	private String tableName;
	private String id;
	private String[] tableFields;
	private boolean idAutoGen;
	
	public CM_Table(String tableName, boolean idAutoGen, String id, String...tableFields) {
		this.tableName = tableName;
		this.id = id;
		this.tableFields = tableFields;
		this.idAutoGen = idAutoGen;
	}
	
	public abstract Object[] getTableFieldValues();
	
	public abstract Object getTableIdValues();
	
	public abstract void setId(int id);

	//===================================================================================
    /*
     * 
     */
	public String getTableName() {
		return this.tableName;
	}
	
	//===================================================================================
    /*
     * 
     */
	public String getTableField() {
		String res = "";
		for ( int i = 0; i < this.tableFields.length; i++ )
			res += this.tableFields[i] + ", ";
		return res.substring(0, res.length() - 2);
	}
	
	//===================================================================================
    /*
     * 
     */
	public String getTableFieldPrepared() {
		String res = "";
		for ( int i = 0; i < this.tableFields.length; i++ )
			res += "?, ";
		return res.substring(0, res.length() - 2);
	}
	
	//===================================================================================
    /*
     * 
     */
	public String getTableIdForSet() {
		return this.id + " = ?";
	}
	
	//===================================================================================
    /*
     * 
     */
	public String getTableFieldForSet() {
		String res = "";
		Object[] values = this.getTableFieldValues();
		for ( int i = 0; i < this.tableFields.length; i++ )
			res += this.tableFields[i] + " = ?, ";
	    
		return res.substring(0, res.length() - 2);
	}
	
	//===================================================================================
    /*
     * 
     */
    public boolean insert() {
		int id = CM_Database.executePreparedSQL	(
													"INSERT INTO " + this.getTableName() +" (" + this.getTableField() + ") " +
													"VALUES (" + this.getTableFieldPrepared() + ");",
													this.idAutoGen,
													this.getTableFieldValues()
												);
		boolean res = id  > 0;
		if(this.idAutoGen && res) this.setId(id);
		CM_Log.debug("Insert " + (res ? "success" : "error") + " in " + this.getTableName());
		return res;
    }
    
    //===================================================================================
    /*
     * 
     */
    public boolean update() {
    	boolean res = CM_Database.executePreparedSQL	(
															"UPDATE " + this.getTableName() + " " +
															"SET " + this.getTableFieldForSet() + " " +
															"WHERE " + this.getTableIdForSet() + ";",
															false,
															addArrayElem(this.getTableFieldValues(), this.getTableIdValues())
														) > 0;

		CM_Log.debug("Update " + (res ? "success" : "error") + " in " + this.getTableName());
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
    public boolean delete() {
    	boolean res = CM_Database.executePreparedSQL	(
															"DELETE FROM " + this.getTableName() +" " +
															"WHERE " + this.getTableIdForSet() + ";",
															false,
															new Object[]{this.getTableIdValues()}
														) > 0;
														
		CM_Log.debug("Delete " + (res ? "success" : "error") + " in " + this.getTableName());
		return res;
    }
}
