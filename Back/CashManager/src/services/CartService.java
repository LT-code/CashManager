package services;

import dao.CartDao;
import dao.Dao;
import entities.EntityClass;
import utils.LogsHandler;
import utils.bdd.DBConnector;

public class CartService extends Service {
	CartDao cartDao = new CartDao(db, this.getLogsHandler());
	
	public CartService(DBConnector db, LogsHandler errHandler) {
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
