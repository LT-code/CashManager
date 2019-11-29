package entities;

import java.util.Map;

import org.json.JSONObject;

import utils.HttpService;
import utils.LogsHandler;
import utils.servlet.HttpStatus;

public class Payment implements EntityClass {
	public final static String STATUS_NOT_DEFINED = "Not defined";
	public final static String STATUS_ALLOWED = "Allowed";
	public final static String STATUS_REFUSED = "Refused";
	
	public final static String PaymentServerURI = "";
	
    // Attributes
    private long idPayment;
    private long idCart;
    private long idType;
    private String status;
    
    public Payment(long idCart, long idType) {
    	this.idCart = idCart;
    	this.idType = idType;
    	this.status = STATUS_NOT_DEFINED;
    }
    
    public Payment(Map<String, Object> map) {
		this(	(int) map.get("idCart"),
				(int) map.get("idType"));
		this.status = (String) map.get("status");
		this.idPayment = (int) map.get("idPayment");
	}
    
    public boolean pay(JSONObject params, LogsHandler log) {
    	JSONObject bodyParams = new JSONObject();
    	switch((int) idType) {
	    	case 1:
	    		bodyParams.put("number", bodyParams.getString("number"));
	    		bodyParams.put("pin", bodyParams.getString("pin"));
	    	case 2:
	    		bodyParams.put("code", bodyParams.getString("code"));
    	}
    		
    	JSONObject response = HttpService.sendPost(PaymentServerURI, "", bodyParams, "");
    	if(response.getInt("status") == HttpStatus.SUCCESS) {
    		this.status = response.getString("payment_status");
    		return this.status.equals(STATUS_ALLOWED);
    	}
    	else
    		log.addError("Error during the connection with the payment server.", HttpStatus.INTERNAL_ERROR);
    	
    	return false;
    		
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
		return  new Object[]{idCart, idType, status};
	}
}
