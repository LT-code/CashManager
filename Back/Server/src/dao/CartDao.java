package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import utils.DBConnector;
import utils.ErrorsHandler;

public class CartDao extends Dao {
	public CartDao(DBConnector db, ErrorsHandler errorHandler) {
		super(db, errorHandler);
	}
}
