package utils;

public class TableFields {
	// key 
	public final static int KEY_NO = 2;
	public final static int KEY_PRIMARY = 1;
	public final static int KEY_FOREIGN = 2;
	
	// types
	public final static int TYPE_INT = 1;
	public final static int TYPE_VARCHAR = 2;
	public final static int TYPE_BOOLEAN = 3;
	
	private String name;
	private int type;
	private int keyType;
	
	public TableFields(String name, int type) {
		this.name = name;
		this.type = type;
		this.keyType = TableFields.KEY_NO;
	}
	
	public TableFields(String name, int type, int keyType) {
		this.name = name;
		this.type = type;
		this.keyType = keyType;
	}
	
	public String getName() {
		return name;
	}
	
	public int getType() {
		return type;
	}
	
	public int getKeyType() {
		return keyType;
	}

	public String getPreparedField() {
		return this.name + " = ?";
	}
}
