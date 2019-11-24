package dao;

import utils.bdd.DBConnector;
import utils.LogsHandler;

public class PaymentDao extends Dao {
	public PaymentDao(DBConnector db, LogsHandler errorHandler) {
		super(db, errorHandler);
	}
}
