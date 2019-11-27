package dao;

import tables.ArticleTable;
import utils.LogsHandler;
import utils.bdd.DBConnector;

public class ArticleDao extends Dao {

	public ArticleDao(DBConnector db, LogsHandler errorHandler) {
		super(db, errorHandler, ArticleTable.getTable());
	}
}
