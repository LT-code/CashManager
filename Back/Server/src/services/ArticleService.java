package services;

import dao.ArticleDao;
import dao.Dao;
import entities.EntityClass;
import utils.DBConnector;
import utils.ErrorsHandler;

public class ArticleService extends Service {
	ArticleDao articleDao = new ArticleDao(db, this.getErrorsHandler());
	
	public ArticleService(DBConnector db, ErrorsHandler errHandler) {
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
