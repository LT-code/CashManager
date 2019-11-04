package dao;

import utils.DBConnector;
import utils.ErrorsHandler;

public class ArticleDao extends Dao {

	public ArticleDao(DBConnector db, ErrorsHandler errorHandler) {
		super(db, errorHandler);
	}
	
}
