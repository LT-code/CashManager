package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import tables.PaymentTypeTable;
import utils.LogsHandler;
import utils.bdd.DBConnector;

public class PaymentTypeDao extends Dao {
	public PaymentTypeDao(DBConnector db, LogsHandler errorHandler) {
		super(db, errorHandler, PaymentTypeTable.getTable());
	}
	
	public List<Map<String, Object>> listTypes() throws SQLException {
		return queryList("Select * from " + this.getTable().getName() + ";");
	}
}
