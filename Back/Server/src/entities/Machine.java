package entities;

import utils.Table;
import utils.TableFields;

public class Machine implements EntityClass {
	private final static Table table =  new Table(	"Machine",
													new TableFields[]	{	
														new TableFields("idMachine", 		TableFields.TYPE_VARCHAR, 	30, TableFields.KEY_PRIMARY),
														new TableFields("idSetting",		TableFields.TYPE_INT, 		11,	TableFields.KEY_FOREIGN),
														new TableFields("isAdmin", 			TableFields.TYPE_BOOLEAN,	0),
														new TableFields("password", 		TableFields.TYPE_VARCHAR,	30)
													});
    // Attributes_T
	private String idMachine;
	protected long idSetting;
	protected boolean isAdmin;
    protected String password;
    
    // Operations
    public Machine() {};
    
    public Machine(String idMachine, long idSetting, boolean isAdmin, String password) {
		this.idMachine = idMachine;
		this.idSetting = idSetting;
		this.isAdmin = isAdmin;
		this.password = password;
	}
    
    public long getIdSetting() {
		return idSetting;
	}

	public void setIdSetting(long idSetting) {
		this.idSetting = idSetting;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean connectServer(String ip, String password) {
		return false;
    }

	@Override
	public Table getTable() {
		return table;
	}

	@Override
	public Object[] getFieldsValues() {
		return new Object[] {this.idMachine, this.idSetting, this.isAdmin, this.password};
	}

	@Override
	public String entityNameClass() {
		return "a machine";
	}

	@Override
	public void setEntityID(Object id) {
		this.idMachine = (String) id;
	}

	@Override
	public Object getEntityID() {
		return this.idMachine;
	}
}
