package sources;

import entities.Setting;

public class AdminSetting extends Setting {
    // Operations
    /**
     * RF_060: The connection delay is configurable in a file (server side) but cannot be more than 180
     * seconds
     * @param delay (???) RF_050: If connection delay of 30 seconds between the terminal and the payment server is
     *        exceeded, a new connection attempt must be made
     * @return void
     */
    public void setConnectionDelay(int delay) {
    	this.connectionDelay = delay;
    }
    
    public void setAttemptsNumber(int attempts) {
    	this.attemptsNumber = attempts;
    }
}
