package services;

import java.sql.SQLException;

import dao.ArticleDao;
import dao.Dao;
import entities.Article;
import entities.EntityClass;
import exception.InvalidNumberReslut;
import utils.DBConnector;
import utils.LogsHandler;

public class ArticleService extends Service {
	ArticleDao articleDao = new ArticleDao(db, this.getLogsHandler());
	
	public ArticleService(DBConnector db, LogsHandler errHandler) {
		super(db, errHandler);
	}
	
	public Article get(String code) throws SQLException, InvalidNumberReslut {
		Article a = this.articleDao.get(code);
		logsHandler.addInfo("Success getting article " + code + ".");
		return a;
	}
	
	@Override
	public Dao getDao() {
		return this.articleDao;
	}

	@Override
	public boolean validator(EntityClass entityClass) {
		return true;
	}
}
