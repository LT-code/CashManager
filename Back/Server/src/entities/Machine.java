package entities;

import utils.Table;
import utils.TableFields;

public class Machine implements EntityClass {
	private final static Table table =  new Table(	"Machine",
													new TableFields[]	{	
														new TableFields("idMachine", 		TableFields.TYPE_VARCHAR, 	TableFields.KEY_PRIMARY),
														new TableFields("idSetting",		TableFields.TYPE_INT, 		TableFields.KEY_FOREIGN),
														new TableFields("isAdmin", 			TableFields.TYPE_BOOLEAN),
														new TableFields("password", 		TableFields.TYPE_VARCHAR)
													});
    // Attributes_T
	private long idSetting;
	protected int connectionDelay;
    protected int attemptsNumber;
    
    // Operations
    public Machine() {
    	this.idSetting = 0;
		this.connectionDelay = 30;
		this.attemptsNumber = 3;
	}
    
    public Machine(long idSetting, int connectionDelay, int attemptsNumber) {
    	this.idSetting = idSetting;
		this.connectionDelay = connectionDelay;
		this.attemptsNumber = attemptsNumber;
	}   
    
    public boolean connectServer(String ip, String password) {
		return false;
    }
    
    public int getAttemptsNumber() {
		return this.attemptsNumber;
    }
    
    public int getConnectionDelay () {
		return this.connectionDelay;
    }

	@Override
	public Table getTable() {
		return table;
	}

	@Override
	public Object[] getFieldsValues() {
		return new Object[] {this.connectionDelay, this.attemptsNumber};
	}

	@Override
	public String entityNameClass() {
		return "a setting";
	}

	@Override
	public void setEntityID(Object id) {
		this.idSetting = (long) id;
	}

	@Override
	public Object getEntityID() {
		return this.idSetting;
	}
}
