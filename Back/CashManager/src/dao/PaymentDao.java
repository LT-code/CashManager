package dao;

import utils.DBConnector;
import utils.LogsHandler;

public class PaymentDao extends Dao {
	public PaymentDao(DBConnector db, LogsHandler errorHandler) {
		super(db, errorHandler);
	}
}
