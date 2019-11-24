package services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import dao.CartArticlesDao;
import dao.Dao;
import entities.EntityClass;
import exception.InvalidNumberReslut;
import utils.LogsHandler;
import utils.bdd.DBConnector;

public class CartService extends Service {
	CartArticlesDao cartArticlesDao = new CartArticlesDao(db, this.getLogsHandler());
	
	public CartService(DBConnector db, LogsHandler errHandler) {
		super(db, errHandler);
	}
	
	@Override
	public Dao getDao() {
		return this.cartArticlesDao;
	}
	
	public ArrayList<Map<String, Object>> listArticles(long idCart) throws SQLException, InvalidNumberReslut {
		ArrayList<Map<String, Object>> list = null;
		list =  this.cartArticlesDao.listArticles(idCart);
		logsHandler.addInfo("Succès de la recuperation des settings.");
		return list;
	}

	@Override
	public boolean validator(EntityClass entityClass) {
		return true;
	}
}
