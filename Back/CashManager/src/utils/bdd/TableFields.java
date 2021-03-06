package utils.bdd;

public class TableFields {
	// key 
	public final static int KEY_NO = 0;
	public final static int KEY_PRIMARY = 1;
	public final static int KEY_FOREIGN = 2;
	public final static int KEY_AUTOGEN = 3;
	public final static int KEY_UNIQUE = 4;
	public final static int KEY_FOREIGN_ON_DELETE_CASCADE = 5;
	
	public final static int TYPE_INT = 0;
	public final static int TYPE_VARCHAR = 1;
	public final static int TYPE_BOOLEAN = 2;
	public final static int TYPE_FLOAT = 3;
	private final static String[] TYPE = {"int", "varchar", "tinyint", "float"};
	
	private String name;
	private int type;
	private int size;
	private int keyType;
	private String associatedTable;
	private boolean autoGenerated;

	public TableFields(String name, int type, int size) {
		this.name = name;
		this.type = type;
		this.size = size;
		this.keyType = TableFields.KEY_NO;
		this.associatedTable = "";
		this.autoGenerated = false;
	}
	
	public TableFields(String name, int type, int size, int keyType) {
		this(name, type, size);
		this.keyType = keyType;
	}
	
	public TableFields(String name, int type, int size, int keyType, String associatedTable) {
		this(name, type, size, keyType);
		this.associatedTable = associatedTable;
	}
	
	public TableFields(String name, int type, int size, int keyType, int autoGenerated) {
		this(name, type, size, keyType);
		this.autoGenerated = autoGenerated == KEY_AUTOGEN;
	}
	
	
	public boolean isAutoGenerated() {
		return autoGenerated;
	}
	
	public boolean isInt() {
		return type == TYPE_INT;
	}
	
	public boolean isUnique() {
		return keyType == TableFields.KEY_UNIQUE;
	}
	
	public boolean isPrimaryKey() {
		return 	keyType == TableFields.KEY_PRIMARY;
	}
	
	public boolean isForeignKey() {
		return 	keyType == TableFields.KEY_FOREIGN ||
				keyType == TableFields.KEY_FOREIGN_ON_DELETE_CASCADE;
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
		return TYPE[this.type] + (this.type == TableFields.TYPE_FLOAT ? "" : "(" + this.size + ")");
	}
}
