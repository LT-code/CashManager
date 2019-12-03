package services;

import java.sql.SQLException;
import java.util.Map;

import dao.CartArticlesDao;
import entities.CartArticles;
import entities.EntityClass;
import exception.InvalidNumberReslut;
import exception.NoResultException;
import utils.LogsHandler;
import utils.bdd.DBConnector;

public class CartArticlesService extends Service {
	public CartArticlesService(DBConnector db, LogsHandler errHandler) {
		super(new CartArticlesDao(db, errHandler));
	}
	
	public Map<String, Object> listArticles(long idCart) throws SQLException, InvalidNumberReslut {
		Map<String, Object> list = null;
		list =  ((CartArticlesDao) this.getDao()).listArticles(idCart);
		this.getDao().getLogsHandler().addInfo("Succès de la recuperation des articles du cart " + idCart + ".");
		return list;
	}
	
	public CartArticles get(long idCart, String codeArticle) throws SQLException, InvalidNumberReslut, NoResultException {
		CartArticles a = ((CartArticlesDao) this.getDao()).get(idCart, codeArticle);
		this.getDao().getLogsHandler().addInfo("Success getting CartArticles " + codeArticle + ".");
		return a;
	}

	@Override
	public boolean validator(EntityClass entityClass) {
		return true;
	}
}
