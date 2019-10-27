import Settings;

public class AdminSettings extends Settings {
    // Operations
    /**
     * RF_060: The connection delay is configurable in a file (server side) but cannot be more than 180
     * seconds
     * @param delay (???) RF_050: If connection delay of 30 seconds between the terminal and the payment server is
     *        exceeded, a new connection attempt must be made
     * @return void
     */
    public final void setConnectionDelay (int delay) {
    }
    public final void setAttemptsNumber (int attempts) {
    }
}
