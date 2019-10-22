package service.setting;
public class Setting {
    // Attributes
	protected int connectionDelay = 30;
    protected int attemptsNumber = 3;
    
    // Operations
    public boolean connectServer(String ip, String password) {
		return false;
    }
    
    public int getAttemptsNumber() {
		return this.attemptsNumber;
    }
    
    public int getConnectionDelay () {
		return this.connectionDelay;
    }
}
