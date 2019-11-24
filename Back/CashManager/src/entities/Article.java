package entities;

import tables.ArticleTable;
import utils.bdd.Table;

public class Article implements EntityClass{
    // Attributes
    private String code;
    private String name;
    private float price;
    
    public Article(String code, String name, float price) {
    	this.code = code;
    	this.name = name;
    	this.price = price;
    }
    
    @Override
	public void setId(Object id) {
		this.code = (String) id;
		
	}
    
    public void setName(String name) {
    	this.name = name;
    }
	
    public String getName() {
		return name;
    	
    }
    
    public float getPrice() {
		return price;
    }
    
    public void setCode(String code) {
    	this.code = code;
    }
    
	@Override
	public Object getId() {
		return code;
	}
    
	@Override
	public Object[] fieldsValues() {
		return  new Object[]{code, name, price};
	}
	
	@Override
	public Table table() {
		return ArticleTable.getTable();
	}
}
