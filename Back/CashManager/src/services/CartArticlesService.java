package services;

import java.sql.SQLException;
import java.util.Map;

import dao.CartArticlesDao;
import dao.Dao;
import entities.CartArticles;
import entities.EntityClass;
import exception.InvalidNumberReslut;
import utils.LogsHandler;
import utils.bdd.DBConnector;

public class CartArticlesService extends Service {
	CartArticlesDao cartArticlesDao = new CartArticlesDao(db, this.getLogsHandler());
	
	public CartArticlesService(DBConnector db, LogsHandler errHandler) {
		super(db, errHandler);
	}
	
	@Override
	public Dao getDao() {
		return this.cartArticlesDao;
	}
	
	public Map<String, Object> listArticles(long idCart) throws SQLException, InvalidNumberReslut {
		Map<String, Object> list = null;
		list =  this.cartArticlesDao.listArticles(idCart);
		logsHandler.addInfo("Succès de la recuperation des articles du cart " + idCart + ".");
		return list;
	}
	
	public CartArticles get(long idCart, String codeArticle) throws SQLException, InvalidNumberReslut {
		CartArticles a = this.cartArticlesDao.get(idCart, codeArticle);
		logsHandler.addInfo("Success getting CartArticles " + codeArticle + ".");
		return a;
	}

	@Override
	public boolean validator(EntityClass entityClass) {
		return true;
	}
}
