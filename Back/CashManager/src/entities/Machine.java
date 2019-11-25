package entities;

import java.util.Map;

import tables.MachineTable;
import utils.bdd.Table;

public class Machine implements EntityClass {
    // Attributes_T
	private String idMachine;
	protected long idSetting;
	protected boolean isAdmin;
    protected String password;
    protected String token;
    
    // Operations    
    public Machine(String idMachine, long idSetting, boolean isAdmin, String password) {
		this.idMachine = idMachine;
		this.idSetting = idSetting;
		this.isAdmin = isAdmin;
		this.password = password;
		this.token = null;
	}
    
    public Machine(Map<String, Object> m) {
		this(	(String) m.get("idMachine"),
				(int) m.get("idSetting"),
				(boolean) m.get("isAdmin"),
				(String) m.get("password"));
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

	public String pGetPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	@Override
	public Object[] fieldsValues() {
		return new Object[] {this.idMachine, this.idSetting, this.isAdmin, this.password, this.token};
	}

	@Override
	public void setId(Object id) {
		this.idMachine = (String) id;
	}
}
