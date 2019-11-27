package dao;

import tables.PaymentTypeTable;
import utils.LogsHandler;
import utils.bdd.DBConnector;

public class PaymentTypeDao extends Dao {
	public PaymentTypeDao(DBConnector db, LogsHandler errorHandler) {
		super(db, errorHandler, PaymentTypeTable.getTable());
	}
}
