package utils;


public class Table {
	private String name;
	private TableFields[] fields;
	
	public Table(String name, TableFields[] fields) {
		this.name = name;
		this.fields = fields;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getFieldsPrepared() {
		String res = "";
		for(int i = 0; i < fields.length; i++)
			if(fields[i].getKeyType() != TableFields.KEY_PRIMARY)
				res += (res == "" ? "" : ", ") + "?";
		return res;
	}
	
	public String getFields() {
		String res = "";
		for(int i = 0; i < fields.length; i++)
			if(fields[i].getKeyType() != TableFields.KEY_PRIMARY)
				res += (res == "" ? "" : ", ") + fields[i].getName();
		return res;
	}
	
	public String getSetFields() {
		String res = "";
		for(int i = 0; i < fields.length; i++)
			if(fields[i].getKeyType() != TableFields.KEY_PRIMARY)
				res += (res == "" ? "" : ", ") + fields[i].getPreparedField();
		return res;
	}
	
	public String getSetID() {
		String res = "";
		for(int i = 0; i < fields.length; i++)
			if(fields[i].getKeyType() == TableFields.KEY_PRIMARY)
				res += (res == "" ? "" : ", ") + fields[i].getPreparedField();
		return res;
	}
	
	public String getTableToSQL() {
		String res = "create table " + name + "(";
		for(int i = 0; i < fields.length; i++)
			res += (res == "" ? "" : ", ") + 	fields[i].getPreparedField() + " " + 
												fields[i].getType() + " " +
												(fields[i].getType() == TableFields.TYPE_INT ? "AUTO_INCREMENT " : " ") +
												(fields[i].getKeyType() == TableFields.KEY_PRIMARY ? "PRIMARY KEY " : " ") +
												"not null";
		
		res += ")";
		
		return res;
	}
}