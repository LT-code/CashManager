package dao;

import utils.DBConnector;
import utils.LogsHandler;

public class CartDao extends Dao {
	public CartDao(DBConnector db, LogsHandler errorHandler) {
		super(db, errorHandler);
	}
}
