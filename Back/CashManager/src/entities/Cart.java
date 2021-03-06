package entities;

import java.util.Map;

public class Cart implements EntityClass {
    // Attributes_T
	private long idCart;
	protected String idMachine;
    
    // Operations
    public Cart(String idMachine) {
    	this.idCart = 0;
		this.idMachine = idMachine;
	}  
    
    public Cart(Map<String, Object> m) {
		this((String) m.get("idMachine"));
	}

	@Override
	public void setId(Object id) {
		this.idCart = (long) id;
	}

	@Override
	public Object getId() {
		return this.idCart;
	}
    
    public String getIdMachine() {
		return this.idMachine;
    }
    
    public void setIdMachine(String idMachine) {
		this.idMachine = idMachine;
    }

	@Override
	public Object[] fieldsValues() {
		return new Object[] {this.idMachine};
	}
}
