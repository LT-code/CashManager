package entities;

public class PaymentType implements EntityClass{	
    // Attributes
    private long idType;
    private String name;
    
    public PaymentType(String name) {
    	this.idType = 0;
    	this.name = name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    @Override
	public void setId(Object id) {
		this.idType = (long) id;	
    }
	
	@Override
	public Object getId() {
		return idType;
	}
    
	@Override
	public Object[] fieldsValues() {
		return  new Object[]{name};
	}
}
