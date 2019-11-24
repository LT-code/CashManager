package dao;

import utils.bdd.DBConnector;
import utils.LogsHandler;

public class PaymentTypeDao extends Dao {
	public PaymentTypeDao(DBConnector db, LogsHandler errorHandler) {
		super(db, errorHandler);
	}
}
