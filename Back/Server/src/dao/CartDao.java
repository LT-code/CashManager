package dao;

import utils.DBConnector;
import utils.ErrorsHandler;

public class CartDao extends Dao {
	public CartDao(DBConnector db, ErrorsHandler errorHandler) {
		super(db, errorHandler);
	}
}
