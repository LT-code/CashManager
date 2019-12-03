package services;

import java.sql.SQLException;

import dao.CartDao;
import entities.EntityClass;
import entities.Setting;
import exception.InvalidNumberReslut;
import utils.LogsHandler;
import utils.bdd.DBConnector;

public class CartService extends Service {	
	public CartService(DBConnector db, LogsHandler errHandler) {
		super(new CartDao(db, errHandler));
	}
	
	public Setting getCurrentSetting(long idCart) throws SQLException, InvalidNumberReslut {
		Setting s = ((CartDao) this.getDao()).getCurrentSetting(idCart);
		this.getDao().getLogsHandler().addInfo("Success getting current settings.");
		return s;
	}
	
	@Override
	public boolean validator(EntityClass entityClass) {
		return true;
	}
}
