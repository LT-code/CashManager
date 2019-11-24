package dao;

import utils.bdd.DBConnector;
import utils.LogsHandler;

public class CartDao extends Dao {
	public CartDao(DBConnector db, LogsHandler errorHandler) {
		super(db, errorHandler);
	}
}
