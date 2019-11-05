package dao;

import utils.DBConnector;
import utils.LogsHandler;

public class ArticleDao extends Dao {

	public ArticleDao(DBConnector db, LogsHandler errorHandler) {
		super(db, errorHandler);
	}
	
}
