package entities;

import tables.ArticleTable;
import utils.Table;

public class Article implements EntityClass{
    // Attributes
    private String code;
    private String name;
    
    public Article(String code, String name) {
    	this.code = code;
    	this.name = name;
    }
    
    @Override
	public void setId(Object id) {
		this.code = (String) id;
		
	}
    
    public void setName(String name) {
    	this.name = name;
    }
	
	@Override
	public Object getId() {
		return code;
	}
    
    public String getName() {
		return name;
    	
    }
    
    public void setCode(String code) {
    	this.code = code;
    }
    
	@Override
	public Object[] fieldsValues() {
		return  new Object[]{code, name};
	}
	
	@Override
	public Table table() {
		return ArticleTable.getTable();
	}
}
