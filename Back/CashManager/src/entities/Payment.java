package entities;

import java.util.Map;

public class Payment implements EntityClass{
	public final static String STATUS_NOT_DEFINED = "Not defined";
	public final static String STATUS_ALLOWED = "Allowed";
	public final static String STATUS_REFUSED = "Refused";
	
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
		this(	(long) map.get("idCart"),
				(long) map.get("idType"));
		this.status = (String) map.get("status");
	}

	public void setStatus(String status) {
    	this.status = status;
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
