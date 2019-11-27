package services;

import dao.ArticleDao;
import dao.Dao;
import entities.EntityClass;
import utils.LogsHandler;
import utils.bdd.DBConnector;

public class ArticleService extends Service {
	ArticleDao articleDao = new ArticleDao(db, this.getLogsHandler());
	
	public ArticleService(DBConnector db, LogsHandler errHandler) {
		super(db, errHandler);
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
