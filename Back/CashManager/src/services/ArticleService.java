package services;

import dao.ArticleDao;
import entities.EntityClass;
import utils.LogsHandler;
import utils.bdd.DBConnector;

public class ArticleService extends Service {
	public ArticleService(DBConnector db, LogsHandler errHandler) {
		super(new ArticleDao(db, errHandler));
	}

	@Override
	public boolean validator(EntityClass entityClass) {
		return true;
	}
}
