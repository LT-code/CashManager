package services;

import java.sql.SQLException;

import dao.CartDao;
import dao.Dao;
import entities.EntityClass;
import entities.Setting;
import exception.InvalidNumberReslut;
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
	
	public Setting getCurrentSetting(long idCart) throws SQLException, InvalidNumberReslut {
		Setting s = cartDao.getCurrentSetting(idCart);
		logsHandler.addInfo("Success getting current settings.");
		return s;
	}
	

	@Override
	public boolean validator(EntityClass entityClass) {
		return true;
	}
}
