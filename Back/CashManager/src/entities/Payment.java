package entities;

import java.util.Arrays;
import java.util.Map;

import org.json.JSONObject;

import utils.HttpService;
import utils.LogsHandler;
import utils.servlet.HttpStatus;

public class Payment implements EntityClass {
	public final static String STATUS_NOT_DEFINED = "Not defined";
	public final static String STATUS_ALLOWED = "Allowed";
	public final static String STATUS_REFUSED = "Refused";
	
	public final static String PaymentServerURI = "https://4a884f77-71c9-4256-9d55-46a7b2da4400.mock.pstmn.io/payment";
	
	public final static String[] number = {"62328736792", "290738930704"};
	public final static String[] pin = {"1234", "4321"};
	public final static String[] code = {"67527836939323"};
	
    // Attributes
    private long idPayment;
    private long idCart;
    private long idType;
    private int numberAttempts;
    private String status;
    
    public Payment(long idCart, long idType) {
    	this.idCart = idCart;
    	this.idType = idType;
    	this.status = STATUS_NOT_DEFINED;
    	this.numberAttempts = 0;
    }
    
    public Payment(Map<String, Object> map) {
		this(	(int) map.get("idCart"),
				(int) map.get("idType"));
		this.status = (String) map.get("status");
		this.idPayment = (int) map.get("idPayment");
		this.numberAttempts =  (int) map.get("numberAttempts");
	}

    public void setIdType(long idType) {
    	this.idType = idType;
    }
    
	public void setStatus(String status) {
    	this.status = status;
    }
    
    public long getIdCart() {
		return idCart;
	}

	public long getIdType() {
		return idType;
	}

	public String getStatus() {
		return status;
	}
	
	public int getNumberAttempts() {
		return numberAttempts;
	}

	@Override
	public void setId(Object id) {
		this.idPayment = (long) id;
		
	}
    
	@Override
	public Object getId() {
		return idPayment;
	}
    
	@Override
	public Object[] fieldsValues() {
		return  new Object[]{idCart, idType, status, numberAttempts};
	}
	
	/*
	 * Call a bank mock server and check if the payment is allowed or not
	 */
    public boolean pay(JSONObject params, int maxAttempts, LogsHandler log) {
    	JSONObject bodyParams = new JSONObject();
    	String status = "400";
    	
    	switch((int) idType) {
	    	case 1:
	    		bodyParams.put("number", params.getString("number"));
	    		bodyParams.put("pin", params.getString("pin"));
	    		for(int i = 0; i < number.length; i++)
	    			if(number[i].equals(params.getString("number")))
	    				if(pin[i].equals(params.getString("pin")))
	    					status = "200";
	    		break;
	    	case 2:
	    		bodyParams.put("code", params.getString("code"));
	    		if(Arrays.stream(code).anyMatch(t -> t.equals(params.getString("code"))))
	    			status = "200";
    	}
    	
    	// send the payment variables to the bank server and check the validity
    	JSONObject response = HttpService.sendPost(PaymentServerURI, "", bodyParams, "", "x-mock-response-code", status);
    	if(response.getInt("status") == HttpStatus.SUCCESS || response.getInt("status") == HttpStatus.BAD_REQUEST) {
    		this.numberAttempts++;
    		
    		if(this.numberAttempts >= maxAttempts || response.getString("payment_status").equals(STATUS_ALLOWED))
    			this.status = response.getString("payment_status");
    			
    		return this.status.equals(STATUS_ALLOWED);
    	}
    	else
    		log.addError("Error during the connection with the payment server.", HttpStatus.INTERNAL_ERROR);
    	
    	System.out.println(response);
    	return false;
    		
    }
}
