package service.setting;

import service.utils.CM_Table;

public class Setting extends CM_Table {
    // Attributes_T
	private int idSetting;
	protected int connectionDelay;
    protected int attemptsNumber;
    
    // Operations
    public Setting() {
		super("Setting", true, "idSetting", "connectionDelay", "maxAttemps");
		this.idSetting = 0;
		this.connectionDelay = 30;
		this.attemptsNumber = 3;
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
	public Object[] getTableFieldValues() {
		return new Object[] {this.connectionDelay, this.attemptsNumber};
	}

	@Override
	public Object getTableIdValues() {
		return this.idSetting;
	}

	@Override
	public void setId(int id) {
		this.idSetting = id;
		
	}
}
