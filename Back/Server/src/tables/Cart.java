package tables;

import utils.Table;
import utils.TableFields;

public class Cart implements TableClass {
	private final static Table table =  new Table(	"Cart",
													new TableFields[]	{	
														new TableFields("idCart", 			TableFields.TYPE_INT, 		11, TableFields.KEY_PRIMARY, TableFields.KEY_AUTOGEN),
														new TableFields("idMachine",	TableFields.TYPE_VARCHAR, 	30)
													});
    // Attributes_T
	private long idCart;
	protected String idMachine;
    
    // Operations
    public Cart() {}
    
    public Cart(String idMachine) {
    	this.idCart = 0;
		this.idMachine = idMachine;
	}   

    public float getBill () {
		return 0;
    }
    
    public String getIdMachine() {
		return this.idMachine;
    }
    
    public void setIdMachine(String idMachine) {
		this.idMachine = idMachine;
    }

	@Override
	public Table getTable() {
		return table;
	}

	@Override
	public Object[] getFieldsValues() {
		return new Object[] {this.idMachine};
	}

	@Override
	public String entityNameClass() {
		return "a setting";
	}

	@Override
	public void setEntityID(Object id) {
		this.idCart = (long) id;
	}

	@Override
	public Object getEntityID() {
		return this.idCart;
	}
}
