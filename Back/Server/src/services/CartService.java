package services;


import dao.CartDao;
import dao.Dao;
import tables.TableClass;
import utils.DBConnector;

public class CartService extends Service {
	CartDao cartDao = new CartDao(db, this.getErrorsHandler());
	
	public CartService(DBConnector db) {
		super(db);
	}
	

	@Override
	public Dao getDao() {
		return this.cartDao;
	}

	@Override
	public boolean validator(TableClass entityClass) {
		return true;
	}
}
