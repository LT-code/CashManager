package services;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import dao.Dao;
import dao.PaymentTypeDao;
import entities.EntityClass;
import utils.LogsHandler;
import utils.bdd.DBConnector;

public class PaymentTypeService extends Service {
	PaymentTypeDao dao = new PaymentTypeDao(db, this.getLogsHandler());
	
	public PaymentTypeService(DBConnector db, LogsHandler errHandler) {
		super(db, errHandler);
	}
	
	@Override
	public Dao getDao() {
		return this.dao;
	}

	@Override
	public boolean validator(EntityClass entityClass) {
		return true;
	}

	public List<Map<String, Object>> listTypes() throws SQLException {
		List<Map<String, Object>>  list = dao.listTypes();
		logsHandler.addInfo("Success getting PaymentTypes.");
		return list;
	}
}
