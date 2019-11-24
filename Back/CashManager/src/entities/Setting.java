package entities;

import tables.SettingTable;
import utils.bdd.Table;

public class Setting implements EntityClass {
    // Attributes_T
	private long idSetting;
	protected int connectionDelay;
    protected int attemptsNumber;
    
    // Operations
    public Setting() {
    	this.idSetting = 0;
		this.connectionDelay = 30;
		this.attemptsNumber = 3;
	}
    
    public Setting(int connectionDelay, int attemptsNumber) {
    	this.idSetting = 0;
		this.connectionDelay = connectionDelay;
		this.attemptsNumber = attemptsNumber;
	}
    
    @Override
	public void setId(Object id) {
		this.idSetting = (long) id;
	}

	@Override
	public Object getId() {
		return this.idSetting;
	}
	
    public int getAttemptsNumber() {
		return this.attemptsNumber;
    }
    
    public int getConnectionDelay () {
		return this.connectionDelay;
    }

	@Override
	public Table table() {
		return SettingTable.getTable();
	}

	@Override
	public Object[] fieldsValues() {
		return new Object[] {this.connectionDelay, this.attemptsNumber};
	}
}
