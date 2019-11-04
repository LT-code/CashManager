package utils;

public class TableFields {
	// key 
	public final static int KEY_NO = 0;
	public final static int KEY_PRIMARY = 1;
	public final static int KEY_FOREIGN = 2;
	public final static int KEY_AUTOGEN = 3;
	
	public final static int TYPE_INT = 0;
	public final static int TYPE_VARCHAR = 1;
	public final static int TYPE_BOOLEAN = 2;
	private final static String[] TYPE = {"INT", "VARCHAR", "BOOLEAN"};
	
	private String name;
	private int type;
	private int keyType;
	private int size;
	private String associatedTable;
	private boolean autoGenerated;
	
	public TableFields(String name, int type, int size) {
		this.name = name;
		this.type = type;
		this.size = size;
		this.keyType = TableFields.KEY_NO;
		this.autoGenerated = false;
		this.associatedTable = "";
	}
	
	public TableFields(String name, int type, int size, int keyType) {
		this.name = name;
		this.type = type;
		this.size = size;
		this.keyType = keyType;
		this.autoGenerated = false;
		this.associatedTable = "";
	}
	
	public TableFields(String name, int type, int size, int keyType, String associatedTable) {
		this.name = name;
		this.type = type;
		this.size = size;
		this.keyType = keyType;
		this.autoGenerated = false;
		this.associatedTable = associatedTable;
	}
	
	public TableFields(String name, int type, int size, int keyType, int autoGenerated) {
		this.name = name;
		this.type = type;
		this.size = size;
		this.keyType = keyType;
		this.autoGenerated = autoGenerated == KEY_AUTOGEN;
		this.associatedTable = "";
	}
	
	public boolean isAutoGenerated() {
		return autoGenerated;
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
	
	public String getAssociatedTable() {
		return associatedTable;
	}

	public String getPreparedField() {
		return this.name + " = ?";
	}
	
	public String getTypeString() {
		return TYPE[this.type] + (this.size > 0 ? "(" + this.size + ")" : "");
	}
}
