package entities;

import tables.ArticleTable;
import utils.Table;

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
    
    public void setStatus(String status) {
    	this.status = status;
    }
    
    @Override
	public void setId(Object id) {
		this.idPayment = (long) idPayment;
		
	}
    
	@Override
	public Object getId() {
		return idPayment;
	}
    
	@Override
	public Object[] fieldsValues() {
		return  new Object[]{idCart, idType, status};
	}
	
	@Override
	public Table table() {
		return ArticleTable.getTable();
	}
}
