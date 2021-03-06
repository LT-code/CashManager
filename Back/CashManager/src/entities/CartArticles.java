package entities;

public class CartArticles implements EntityClass{
    // Attributes
	private long idCartArticles;
	private long idCart;
    private String codeArticle;
    
    public CartArticles(long idCart, String codeArticle) {
    	this.idCartArticles = 0;
    	this.idCart = idCart;
    	this.codeArticle = codeArticle;
    }
       
    public CartArticles(int idCartArticles, int idCart, String codeArticle) {
		this(idCart, codeArticle);
		this.idCartArticles = idCartArticles;
	}

	public long getIdCart() {
		return idCart;
	}

	public void setIdCart(long idCart) {
		this.idCart = idCart;
	}

	public String getCodeArticle() {
		return codeArticle;
	}

	public void setCodeArticle(String codeArticle) {
		this.codeArticle = codeArticle;
	}

	@Override
	public void setId(Object id) {
		this.idCartArticles = (long) id;
		
	}
 
	@Override
	public Object getId() {
		return idCartArticles;
	}
    
	@Override
	public Object[] fieldsValues() {
		return  new Object[]{idCart, codeArticle};
	}
}
