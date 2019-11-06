package entities;

import tables.MachineTable;
import utils.Table;

public class Machine implements EntityClass {
    // Attributes_T
	private String idMachine;
	protected long idSetting;
	protected boolean isAdmin;
    protected String password;
    
    // Operations    
    public Machine(String idMachine, long idSetting, boolean isAdmin, String password) {
		this.idMachine = idMachine;
		this.idSetting = idSetting;
		this.isAdmin = isAdmin;
		this.password = password;
	}
    
    @Override
	public Object getId() {
		return this.idMachine;
	}

	@Override
	public Table table() {
		return MachineTable.getTable();
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
	public Object[] fieldsValues() {
		return new Object[] {this.idMachine, this.idSetting, this.isAdmin, this.password};
	}

	@Override
	public void setId(Object id) {
		this.idMachine = (String) id;
	}
}
