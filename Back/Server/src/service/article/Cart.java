package service.article;

import service.payment.PaymentFactory;

public class Cart {
    // Associations
    private PaymentFactory use;
    private Article contain;
    // Attributes
    private String idMachine;
    
    // Operations
    private boolean isArticle(String code) {
		return false;
    }
    
    public String getArticleLibelle(String code) {
		return code;
    }
    
    public void addArticle (String code) {
    }
    
    public void removeArticle (String code) {
    }
    
    public float getBill () {
		return 0;
    }
}
