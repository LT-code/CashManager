package services;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import dao.PaymentTypeDao;
import entities.EntityClass;
import utils.LogsHandler;
import utils.bdd.DBConnector;

public class PaymentTypeService extends Service {
	public PaymentTypeService(DBConnector db, LogsHandler errHandler) {
		super(new PaymentTypeDao(db, errHandler));
	}

	public List<Map<String, Object>> listTypes() throws SQLException {
		List<Map<String, Object>>  list = ((PaymentTypeDao) this.getDao()).listTypes();
		this.getDao().getLogsHandler().addInfo("Success getting PaymentTypes.");
		return list;
	}
	
	@Override
	public boolean validator(EntityClass entityClass) {
		return true;
	}
}
