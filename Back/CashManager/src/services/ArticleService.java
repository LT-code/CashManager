package services;

import dao.ArticleDao;
import dao.Dao;
import entities.Article;
import entities.EntityClass;
import utils.DBConnector;
import utils.LogsHandler;

public class ArticleService extends Service {
	ArticleDao articleDao = new ArticleDao(db, this.getLogsHandler());
	
	public ArticleService(DBConnector db, LogsHandler errHandler) {
		super(db, errHandler);
	}
	
	public Article get(String code) {
		Article a = null;
		try {
			a =  this.articleDao.get(code);
			logsHandler.addInfo("Success getting article " + code + ".");
			return a;
		}
	    catch (Exception e) {
	    	this.getLogsHandler().addError(e); 
	    }
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
