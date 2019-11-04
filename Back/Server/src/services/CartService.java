package services;


import dao.CartDao;
import dao.Dao;
import entities.EntityClass;
import utils.DBConnector;
import utils.ErrorsHandler;

public class CartService extends Service {
	CartDao cartDao = new CartDao(db, this.getErrorsHandler());
	
	public CartService(DBConnector db, ErrorsHandler errHandler) {
		super(db, errHandler);
	}
	

	@Override
	public Dao getDao() {
		return this.cartDao;
	}

	@Override
	public boolean validator(EntityClass entityClass) {
		return true;
	}
}
