import PaymentFactory;

public class Payment {
    // Associations
    private PaymentFactory call;
    // Attributes
    /**
     * The amount of the payment
     */
    public float amount;
    // Operations
    public abstract void pay ();
    /**
     * RF_070:  If after two successive attempts the connection is not effective, the payment process must be canceled
     * 
     * RF_080:  The number of attempts before canceling action is configurable in a file (server side)
     * 
     * RF_090:  The payment is canceled after three successive wrong card or cheque
     * 
     * RF_120: The payment can be canceled at any moment
     * 
     * @return void
     */
    public void cancel () {
    }
    /**
     * RF_110: The payment server must allow or refuse the payment
     * @return void
     */
    public void allow () {
    }
    /**
     * RF_110: The payment server must allow or refuse the payment
     * @return void
     */
    public void refuse () {
    }
}
