package dao;

import tables.PaymentTable;
import utils.LogsHandler;
import utils.bdd.DBConnector;

public class PaymentDao extends Dao {
	public PaymentDao(DBConnector db, LogsHandler errorHandler) {
		super(db, errorHandler, PaymentTable.getTable());
	}
}
